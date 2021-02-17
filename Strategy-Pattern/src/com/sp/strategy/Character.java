package com.sp.strategy;

public class Character {
    // 인터페이스를 통한 접근점 부여
    private Weapon weapon;

    public Character() {
        this.weapon = null;
    }

    // 교환가능하도록 선언
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void useWeapon() {
        if (weapon == null) {
            System.out.println("Attack with a hand");
        } else {
            weapon.attack();
        }
    }
}
