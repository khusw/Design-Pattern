package com.sp.strategy;

public class Main {
    public static void main(String[] args) {
        Character character = new Character();
        character.useWeapon();

        character.setWeapon(new Axe());
        character.useWeapon();

        character.setWeapon(new Sword());
        character.useWeapon();

        character.setWeapon(new Bow());
        character.useWeapon();
    }
}
