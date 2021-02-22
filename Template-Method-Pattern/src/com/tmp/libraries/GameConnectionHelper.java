package com.tmp.libraries;

public abstract class GameConnectionHelper {
    protected abstract String doSecurity(String str);
    protected abstract boolean authentication(String id, String password);
    protected abstract int authorization(String userName);
    protected abstract String connection(String info);

    // template method
    public String requestConnection(String encodedStr) {

        String decodedStr = doSecurity(encodedStr);
        System.out.println(decodedStr);

        String id = "aaa";
        String pw = "bbb";

        if (!authentication(id, pw)) {
            throw new Error("일치하는 정보가 없음");
        }

        String userName = "?";
        int userIdx = authorization(userName);

        switch (userIdx) {
            case -1:
                System.out.println("셧다운제에 걸려 종료함");
                throw new Error("Shutdown because goverment's policy");
            case 1:
                System.out.println("이 유저는 게임 매니저임");
                break;
            case 2:
                System.out.println("이 유저는 유료 회원임");
                break;
            case 3:
                System.out.println("이 유저는 무료 회원임");
                break;
            default:
                System.out.println("권한이 없는 사용자임");
                break;
        }

        return connection(decodedStr);
    }
}
