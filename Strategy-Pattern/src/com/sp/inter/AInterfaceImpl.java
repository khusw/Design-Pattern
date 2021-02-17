package com.sp.inter;

public class AInterfaceImpl implements AInterface {
    // 인터페이스는 구현부와 선언부를 따로 표현함
    @Override
    public void funcA() {
        System.out.println("Function A's Implementation");
    }
}
