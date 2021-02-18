### Strategy Pattern

> 정의 <br>
- 객체들이 할 수 있는 행위 각각에 대해 전략 클래스를 생성하고, 
유사한 행위들을 캡슐화 하는 인터페이스를 정의하여, 
객체의 행위를 동적으로 바꾸고 싶은 경우, 
직접행위를 수정하지 않고 전략을 바꿔주기만 함으로써 
행위를 유연하게 확장시키는 방법.

- 즉, 서로 다른 객체가 할 수 있는 행위를 '전략' 이라는 단위로 만들어서,
행위의 수정이 필요할때, 전략만 바꿔서 행위를 수정하는 방식.


> 사전 지식 <br>
1. Interface
2. Delegation

> Interface <br>

- 자바에서 인터페이스란 첫번째로, 어떤 기능에 대해서 선언과 구현부를 분리해 놓는 기능을 말하고
다른 클래스에서 해당 기능을 사용하기 위한 통로로 활용되는것을 의미한다. 

```java
// 선언
public interface AInterface {
    void funcA();
}
```

```java
// 구현
public class AInterfaceImpl {
    @Override
    public void funcA() {
        System.out.println("Function A's Implementation");
    }
}
```

```java
public class Main {
    // 구현과 선언을 따로 함과 동시에 기능을 실행하기 위한 통로 역할을 수행
    public static void main(String[] args) {
       AInterface aInterface = new AInterfaceImpl();
       aInterface.funcA();
   }
}
```

> Delegation
- 위임(Delegation) 이란 어떤 기능에 대해서, 책임소재를 다른 클래스로 떠넘기는 것을 말한다.
 아래의 예제에서, print() 함수의 기능에 대한 책임소재가 AObj 에게 부여하는게 아닌, BObj 에게 전적으로 
 위임한것을 볼 수 있다.
 
 ```java
public interface BObj {
    void print();
}
```

```java
public class BObjImpl implements BObj {
    @Override
    public void print() {
        System.out.println("This is B Object");
    }
}
```

```java
public class AObj {
    // print 함수에 대한 책임소재를 BObj 에게 위임한 형태.
    private BObj bObj;

    public AObj() {
        bObj = new BObjImpl();
    }

    public void callBObj() {
        bObj.print();
        System.out.println("This is A Object");
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        AObj aObj = new AObj();
        aObj.callBObj();
    }
}
```

> Strategy Pattern
- 만약 예를들어, 프로그램 요구사항으로, 게임 캐릭터와 무기를 구현해달라는 요구를 받았다고 치면,
캐릭터 클래스안에 일일이 무기를 구현하기 보다는, 캐릭터가 여러가지 무기들 중 하나를 선택 하게 만드는것이 더 유지 보수가 쉬울 것이다.

- 즉, 여러개의 무기들 중에서 캐릭터가 자신이 원하는 특정한 무기를 선택하는것은, 자신의 상황에 맞는 어떤 전략을 선택한것으로 볼 수 있다.
아래는 이에 대한 코드 예제이다.

```java
public interface Weapon {
    void attack();
}
```

```java
public class Sword implements Weapon{
    @Override
    public void attack() {
        System.out.println("Attack with a sword");
    }
}
```

```java
public class Bow implements Weapon {
    @Override
    public void attack() {
        System.out.println("Attack with a bow");
    }
}
```

```java
public class Axe implements Weapon {
    @Override
    public void attack() {
        System.out.println("Attack with a Axe");
    }
}
```

```java
public class Character {
    // 인터페이스를 통한 접근점(통로) 부여
    private Weapon weapon;

    public Character() {
        this.weapon = null;
    }

    // 캐릭터가 여러개의 무기중 하나의 전략을 선택하도록 부여
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
```

```java
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
```

이를 다이어그램으로 표현한다면 다음과 같을 것이다.
![strategy pattern diagram](https://user-images.githubusercontent.com/47293759/108305842-3f1dba00-71ee-11eb-80d3-f3aaeaa3be89.JPG)