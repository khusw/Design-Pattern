package com.fmp.concrete;

import com.fmp.framework.Item;
import com.fmp.framework.ItemCreator;

import java.util.Date;

public class HpCreator extends ItemCreator {

    @Override
    protected void requestItemsInfo() {
        System.out.println("DB 에서 체력 회복 물약 정보를 가져온다");
    }

    @Override
    protected Item createItem() {
        return new HpPotion();
    }

    @Override
    protected void createItemLog() {
        System.out.println("체력 회복 물약을 새로 생성하였다 " + new Date());
    }
}
