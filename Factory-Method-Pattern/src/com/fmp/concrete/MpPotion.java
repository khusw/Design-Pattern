package com.fmp.concrete;

import com.fmp.framework.Item;

public class MpPotion implements Item {
    @Override
    public void use() {
        System.out.println("마력회복 물약을 사용");
    }
}
