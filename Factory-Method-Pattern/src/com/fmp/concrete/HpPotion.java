package com.fmp.concrete;

import com.fmp.framework.Item;

public class HpPotion implements Item {
    @Override
    public void use() {
        System.out.println("체력회복 물약을 사용");
    }
}
