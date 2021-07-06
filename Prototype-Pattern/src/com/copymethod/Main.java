package com.copymethod;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Cat navi = new Cat();
        navi.setName("navi");

        // shallow copy (기존 인스턴스(=navi)의 주소값을 그대로 복사하고 동일한 주소값을 가짐)
        Cat tom = navi;
        tom.setName("tom");

        // deep copy (기존 인스턴스와 다른 주소값을 가진채로 값만 복사함)
        Cat bucks = navi.copy();
        bucks.setName("bucks");

        System.out.println(navi.getName());
        System.out.println(tom.getName());
        System.out.println(bucks.getName());

        System.out.println(navi);
        System.out.println(tom);
        System.out.println(bucks);
    }
}
