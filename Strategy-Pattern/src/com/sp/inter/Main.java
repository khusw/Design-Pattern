package com.sp.inter;

public class Main {
    // 구현과 선언을 따로 함과 동시에 기능을 실행하기 위한 통로 역할을 수행
    public static void main(String[] args) {
        AInterface aInterface = new AInterfaceImpl();
        aInterface.funcA();
    }
}
