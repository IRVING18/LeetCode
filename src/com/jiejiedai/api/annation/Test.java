package com.jiejiedai.api.annation;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) {

        UserVo userVo = TestVoFactory.create();

        System.out.println("========   "+userVo.getName());

    }

    static class TestVoFactory {

        public static UserVo create() {
            UserVo userVo = new UserVo();


            //获取所有属性值，包含private修饰的
            Field[] fields = userVo.getClass().getDeclaredFields();

            try {
                for (Field field : fields) {
                    //1、获取该成员是否被 @Init 注解修饰
                    if (field.isAnnotationPresent(Init.class)){
                        //2、获取该字段的注解
                        Init init = field.getAnnotation(Init.class);
                        //设置private属性可通过反射方式赋值
                        field.setAccessible(true);
                        //3、给字段设置注解里的值
                        field.set(userVo,init.set());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

            return userVo;
        }
    }
}
