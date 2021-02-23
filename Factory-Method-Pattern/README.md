### Factory Method Pattern

> 정의
- Factory 는 사전적으로 공장 이라는 뜻이다. 그래서 Factory Method Pattern 도 마치 공장 처럼
무언가를 만드는 패턴으로 생각할 수 있다. 
- GoF 의 디자인 패턴에서는 Factory Method Pattern 을 다음과 같이 정의하고 있다.
"객체를 생성하기 위해 인터페이스를 정의하지만, 어떤 클래스의 인스턴스를 생성할지에 대한 결정은 서브클래스가 내리게 하는 방식."
- 즉, 인스턴스의 타입에 대한 결정권을 서브 클래스에게 떠넘기는것을 말한다

> 코드 예제
- 예를들어, 다음과 같은 프로그래밍 요구사항이 들어왔다고 가정해보자
1) 게임 아이템과 그 아이템을 생성하는 코드를 작성해라
    - 아이템 생성 전에, DB 에서 아이템 정보를 가져와라
    - 아이템 생성 이후, 로그 기록을 남겨라
    
2) 아이템 생성의 주체를 ItemCreator 라고 정의한다

3) Item Interface 에는 use 함수를 기본으로 넣는다

4) 현재 가용한 아이템은 체력회복물약, 마력회복물약 이렇게 2가지만 존재한다.

<br>

```java
public interface Item {
    void use();
}
```

<br>

```java
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
```
: ItemCreator 라는 추상 클래스에는 "아이템을 생성" 하는 알고리즘이 정해져있다

DB 에 아이템 정보를 요청하고, 아이템을 생성하고, 생성했다는 로그를 남기는 일련의 과정이 정해져있으니
템플릿 메소드 패턴을 사용해서 코드의 틀을 잡았다.

<br>

```java
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
```
: ItemCreator 라는 추상클래스를 상속 받아서 구체적인 구현을 하는 부분

(이 부분은 템플릿 메소드 패턴과 양상이 같다 (선언과 구현의 분리))

<br>

```java
public class HpPotion implements Item {
    @Override
    public void use() {
        System.out.println("체력회복 물약을 사용");
    }
}
```
: Potion 부분도 마찬가지로, 선언과 구현이 분리된 형태이다.

<br>

```java
public class MpCreator extends ItemCreator {

    @Override
    protected void requestItemsInfo() {
        System.out.println("DB 에서 마력 회복 물약 정보를 가져온다");
    }

    @Override
    protected Item createItem() {
        return new MpPotion();
    }

    @Override
    protected void createItemLog() {
        System.out.println("마력 회복 물약을 새로 생성하였다 " + new Date());
    }
}
```

<br>

```java
public class MpPotion implements Item {
    @Override
    public void use() {
        System.out.println("마력회복 물약을 사용");
    }
}
```

<br>

```java
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
```
: 메인 함수를 통해서 알 수 있는것은 서로 다른 두개의 아이템 (체력회복물약, 마력회복물약) 을 
인스턴스화 하는 부분이 서브 클래스인 HpPortion, MpPortion 에 의해 결정된다는것,
그리고 아이템 생성자인 HpCreator, MpCreator 라는 서브 클래스에 의해서 인스턴스가 결정된다는것
이게 바로 _Factory Method Pattern_ 이다.

<br>



> Factory Method Pattern vs Template Method Pattern
- 언뜻 보면, 두 패턴은 같은 것 처럼 보이는데
위 코드 예제에서도 알 수 있듯이, 
  전자는 하위 클래스에 의해서 인스턴스가 결정된다는것과
  후자는 정해진 알고리즘 틀을 작성할때 사용되는 패턴이라는점에서
  둘은 서로 다른 디자인 패턴임을 알 수 있다.