package com.zen.reflect;

import com.zen.annoation.AutoWired;
import com.zen.annoation.Bean;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 我们知道ioc有一个ioc容器，把全部的对象放入ioc容器之中，
 * 这样我们就不需要自己去手动创建对象了，那么我们是怎么把对象放入ioc容器之中的呢？很简单，在这里我们使用了Map
 * <p>
 * 这里我们创建了一个bean工厂，在ApplicationContext被实例化后，就会去自动读取配置类文件，
 * 把全部的需要放入ioc的容器放入bean工厂，那么如何把配置类文件放入bean工厂呢？我们需要新建一个类
 *
 * @param <T>
 */
public class ApplicationContext<T> {

    private HashMap<Class, Object> beanFactory = new HashMap<>();
    private static String filePath;

    public T getBean(Class clazz) {
        return (T) beanFactory.get(clazz);
    }

    public void initContext() throws Exception {
        InputStream resource = ApplicationContext.class.getClassLoader().getResourceAsStream("config/bean.config");
        Properties properties = new Properties();
        properties.load(resource);
        Set<Object> keys = properties.keySet();
        System.out.println(properties);
        System.out.println(keys);
        for (Object key : keys) {
//             System.out.println(key.toString());
            beanFactory.put(Class.forName(key.toString()), Class.forName(properties.getProperty(key.toString())).newInstance());
        }
        System.out.println(beanFactory.keySet());
        System.out.println(beanFactory);
        // 我们通过配置文件把类放入ioc容器，但是这样似乎很麻烦，每次需要放入ioc容器的时候，
        // 我们都需要自己去写一次配置文件，那么我们也没有什么方法简化这个步骤呢？
    }
    public void initContextByAnnotation() throws Exception {
        //扫描包
        filePath = ApplicationContext.class.getClassLoader().getResource("").getFile();
        loadOne(new File(filePath));
        assembleObject();

    }

    private  void loadOne(File fileParent) {
        if (fileParent.isDirectory()) {
            File[] childrenFiles = fileParent.listFiles();
            if(childrenFiles == null || childrenFiles.length == 0){
                return;
            }
            for (File child : childrenFiles) {
                if (child.isDirectory()) {
                    //如果是个文件夹就继续调用该方法,使用了递归
                    loadOne(child);
                } else {
                    //通过文件路径转变成全类名,第一步把绝对路径部分去掉

                    String pathWithClass = child.getAbsolutePath().substring(filePath.length() - 1);
                    //选中class文件
                    if (pathWithClass.contains(".class")) {
                        //    com.xinzhi.dao.UserDao
                        //去掉.class后缀，并且把 \ 替换成 .
                        String fullName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");
                        try {
                            Class<?> aClass = Class.forName(fullName);


                            //把非接口的类实例化放在map中
                            if(!aClass.isInterface()){
                                Bean annotation = aClass.getAnnotation(Bean.class);
                                if(annotation != null){
                                    Object instance = aClass.newInstance();
                                    //判断一下有没有接口
                                    if(aClass.getInterfaces().length > 0) {
                                        //如果有接口把接口的class当成key，实例对象当成value
                                        System.out.println("正在加载【"+ aClass.getInterfaces()[0] +"】,实例对象是：" + instance.getClass().getName());
                                        beanFactory.put(aClass.getInterfaces()[0], instance);
                                    }else{
                                        //如果有接口把自己的class当成key，实例对象当成value
                                        System.out.println("正在加载【"+ aClass.getName() +"】,实例对象是：" + instance.getClass().getName());
                                        beanFactory.put(aClass, instance);
                                    }
                                }
                            }
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void assembleObject() {
        for(Map.Entry<Class,Object> entry : beanFactory.entrySet()){
            //就是咱们放在容器的对象
            Object obj = entry.getValue();
            Class<?> aClass = obj.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields){
                AutoWired annotation = field.getAnnotation(AutoWired.class);
                if( annotation != null ){
                    field.setAccessible(true);
                    try {
                        System.out.println("正在给【"+obj.getClass().getName()+"】属性【" + field.getName() + "】注入值【"+ beanFactory.get(field.getType()).getClass().getName() +"】");
                        field.set(obj,beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }







}