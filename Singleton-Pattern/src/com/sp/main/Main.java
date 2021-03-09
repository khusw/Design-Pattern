package com.sp.main;

public class Main {
    public static void main(String[] args) {
        SystemSpeaker speaker1 = SystemSpeaker.getInstance();
        SystemSpeaker speaker2 = SystemSpeaker.getInstance();

        // 동일한 주소값 출력
        System.out.println(speaker1);
        System.out.println(speaker2);

        // 출력 : 5, 5
        System.out.println(speaker1.getVolume());
        System.out.println(speaker2.getVolume());

        // 출력 : 11, 11
        speaker1.setVolume(11);
        System.out.println(speaker1.getVolume());
        System.out.println(speaker2.getVolume());

        // 출력 : 22, 22
        speaker2.setVolume(22);
        System.out.println(speaker1.getVolume());
        System.out.println(speaker2.getVolume());
    }
}
