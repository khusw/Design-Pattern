package com.fmp.main;

import com.fmp.concrete.HpCreator;
import com.fmp.concrete.MpCreator;
import com.fmp.framework.Item;
import com.fmp.framework.ItemCreator;

public class Main {
    public static void main(String[] args) {
        ItemCreator hpCreator = new HpCreator();
        Item hpPotion, mpPotion;

        hpPotion = hpCreator.create();
        hpPotion.use();

        ItemCreator mpCreator = new MpCreator();
        mpPotion = mpCreator.create();

        mpPotion.use();
    }
}
