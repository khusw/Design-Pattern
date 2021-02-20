### Adapter Pattern
> 정의 <br>
- 어댑터 라는 것은, 사전적 으로는 "기계나 기계 따위를 다목적 으로 사용 하기 위한 보조 기구 또는 그것을 부착 하기 위한 보조 기구" 로 해석 된다
일상 생활 에서, 해외여행시에 챙겨가는 볼트 변환기 같은 것을 어댑터라고 표현하기도 한다. 
  즉, 어댑터라는것은 호환이 되지 않는 무언가를 호환이 되게 만들어주는 보조장치라고 볼 수 있다.
  어댑터 패턴도 어떤 클래스 에서 제공하지 않는 사용자가 원하는 기능을 인터페이스로 만들어서 호환성을 제공하는 것을 말한다.
  

> 사전 지식 <br>
- 상속
- 합성 <br>
[참고 : 상속과 합성](http://www.darkkaiser.com/2007/07/16/%EC%83%81%EC%86%8D%EA%B3%BC-%ED%95%A9%EC%84%B1/)
  
> Adapter Pattern 코드 예제 분석
- 정의 부분에서 언급한 볼트 변환기 개념을 기반으로한 예제를 살펴보자
<br><br>
```java
public class Volt {
    private int volts;

    public Volt(int volts) {
        this.volts = volts;
    }

    public int getVolts() {
        return volts;
    }

    public void setVolts(int volts) {
        this.volts = volts;
    }
}
```
-> Volt 클래스는 단순히 volts 값을 멤버 변수로 하는 POJO 이다.
<br><br>

```java
public class Socket {
    public Volt getVolt() {
        return new Volt(120);
    }
}
```
-> Socket 클래스는 120 볼트 규격만 제공하는 클래스이다. 따라서 다른 볼트 값을 가지려면 볼트 변환기가 필요하다
<br><br>

```java
public interface SocketAdapter {
    Volt get120Volt();
    Volt get12Volt();
    Volt get3Volt();
}
```
-> 볼트 변환기 역할을 하는 SocketAdapter 인터페이스를 선언했다
<br><br>

_어댑터 패턴을 구현하는 방식은 Class Adapter (Inheritance, 상속), Object Adapter (Composition, 합성) 두가지 방법으로 나뉜다_
1. Class Adapter (Inheritance)
```java
// Class Adapter (Inheritance)
public class SocketClassAdapterImpl extends Socket implements SocketAdapter {

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts() / i);
    }

    @Override
    public Volt get120Volt() {
        return getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt v = getVolt();
        return convertVolt(v, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = getVolt();
        return convertVolt(v, 40);
    }
}
```
<br><br>
2. Object Adapter (Composition)

```java
// Object Adapter (Composition)
public class SocketObjectAdapterImpl implements SocketAdapter{

    // Composition for adapter pattern
    private Socket socket = new Socket();

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts() / i);
    }

    @Override
    public Volt get120Volt() {
        return socket.getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt v = socket.getVolt();
        return convertVolt(v, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = socket.getVolt();
        return convertVolt(v, 40);
    }
}
```
-> 첫번째 방식은, Socket 클래스를 상속 받아서, 어댑터 역할을 하는 인터페이스에 정의된 함수들에 대해서
오버라이드를 수행하지만, 두번째 방식은, 상속이 아닌 합성 방식으로 필드 변수 Socket 을 선언하고,
이를 기반으로 오버라이드를 수행하는것을 볼 수 있다.
<br><br>

_위 예제에 대한 다이어그램을 나타내면 다음과 같다_
![adapter pattern diagram](https://user-images.githubusercontent.com/47293759/108597661-7ac2aa80-73cd-11eb-9aa8-cfdd45822a00.JPG)