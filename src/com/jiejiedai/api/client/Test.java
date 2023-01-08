package com.jiejiedai.api.client;

public class Test {    // 常量
    public static final String MAN_SEX_TYPE = "man";    // 静态变量
    public static String WOMAN_SEX_TYPE = "woman";

    public static void main(String[] args) {
        People people = new People();
        people.setName("张三");
        people.setSex(MAN_SEX_TYPE);
//        people.setAge(18);
//        Test test = new Test();        // 调用静态方法
//        toString(people);        // 调用非静态方法
//        test.sport(people);
    }

    /**
     * 静态方法     * @param people
     */
    public static void toString(People peo) {
        System.out.println(String.format("name:%s, age:%s, sex:%s", peo.name, peo.age, peo.sex));
    }

    /**
     * 非静态方法     * @param people
     */
    public void sport(People peo) {
        System.out.println(String.format("%s正在运动", peo.name));
    }

    static class People {
        private String name;
        private int age;
        private String sex;

        public String getName() {
            return name == null ? "" : name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
