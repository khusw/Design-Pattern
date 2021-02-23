package com.fmp.framework;

public abstract class ItemCreator {

    // 아이템 생성 이전에 DB 에 아이템 정보 요청
    abstract protected void requestItemsInfo();
    
    // 아이템 생성 알고리즘
    abstract protected Item createItem();

    // 아이템 생성 이후, 로그 정보 남김
    abstract protected void createItemLog();

    // 구체적인 생성 알고리즘
    public Item create() {

        requestItemsInfo();

        Item item = createItem();

        createItemLog();

        return item;
    }
}