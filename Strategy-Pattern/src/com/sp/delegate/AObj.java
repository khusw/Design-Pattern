package com.sp.delegate;

public class AObj {
    // print 함수에 대한 책임소재를 BObj 에게 위임한 형태.
    private BObj bObj;

    public AObj() {
        bObj = new BObjImpl();
    }

    public void callBObj() {
        bObj.print();
        System.out.println("This is A Object");
    }
}
