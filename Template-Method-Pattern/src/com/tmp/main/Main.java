package com.tmp.main;

import com.tmp.libraries.DefaultGameConnectionHelper;
import com.tmp.libraries.GameConnectionHelper;

public class Main {
    public static void main(String[] args) {
        GameConnectionHelper gameConnectionHelper = new DefaultGameConnectionHelper();

        gameConnectionHelper.requestConnection("어떤 정보를 넣음");
    }
}
