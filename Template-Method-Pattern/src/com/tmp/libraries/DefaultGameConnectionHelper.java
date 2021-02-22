package com.tmp.libraries;

public class DefaultGameConnectionHelper extends GameConnectionHelper {
    @Override
    protected String doSecurity(String str) {
        System.out.println("디코딩 작업을 하는 부분");
        System.out.println("추가된 강화작업");
        return str;
    }

    @Override
    protected boolean authentication(String id, String password) {
        System.out.println("DB 에 접속하여 ID, PW 일치하는지 확인하는 부분");
        return true;
    }

    @Override
    protected int authorization(String userName) {
        System.out.println("DB 에 접속해서 유저 타입이 무엇인지 확인하는 부분");
        System.out.println("유저의 나이를 확인하여 셧다운제에 걸리는지 파악");
        return 0;
    }

    @Override
    protected String connection(String info) {
        System.out.println("마지막으로 접속을 완료하는 부분");
        return info;
    }
}
