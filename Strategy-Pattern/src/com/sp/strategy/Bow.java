package com.sp.strategy;

public class Bow implements Weapon {

    @Override
    public void attack() {
        System.out.println("Attack with a bow");
    }
}
