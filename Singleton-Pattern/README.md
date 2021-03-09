### Singleton-Pattern

> 정의    
- 전역 변수를 사용하지 않고 객체를 단 <b>하나</b>만 생성하여, 생성된 객체를 <b>어디에서든 참조</b>할 수 있게 만드는 패턴    

> 코드 예제
- 하나의 스피커를 가지고 다수의 사용자가 사용한다고 가정할때,    
사용자 모두가 동일한 스피커를 쓰고 있는지 확인해보기
  
    
- systemSpeaker.java
```java
public class SystemSpeaker {
    static private SystemSpeaker instance;
    private int volume;

    private SystemSpeaker() { // 외부에서 인스턴스 생성을 막기 위해 private 으로 선언
        volume = 5;
    }

    public static SystemSpeaker getInstance() {
        if (instance == null) {
            instance = new SystemSpeaker();
        }
        return instance;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
```
 
           
- main.java
```java
public class Main {
    public static void main(String[] args) {
        SystemSpeaker speaker1 = SystemSpeaker.getInstance();
        SystemSpeaker speaker2 = SystemSpeaker.getInstance();

        // 동일한 주소값 출력
        System.out.println(speaker1);
        System.out.println(speaker2);

        // 출력 : 5, 5
        System.out.println(speaker1.getVolume());
        System.out.println(speaker2.getVolume());

        // 출력 : 11, 11
        speaker1.setVolume(11);
        System.out.println(speaker1.getVolume());
        System.out.println(speaker2.getVolume());

        // 출력 : 22, 22
        speaker2.setVolume(22);
        System.out.println(speaker1.getVolume());
        System.out.println(speaker2.getVolume());
    }
}
```
    
--
> 실제 사용 예제    
- Spring Framework 의 경우 Spring Container 가 관리하는 객체들을    
Bean 이라 부르는데, 각 Bean 들은 서로 다른 Bean Scope 를 가진다.    
  Bean Scope 의 기본 설정 값은 Singleton Scope 이며,    
  위에서 설명한 Singleton Pattern 의 의미와 마찬가지로,    
  단 한번만 Bean 인스턴스가 생성되고 Spring Container 가 이를 관리한다.   
  
- 참조 링크 : [Go to Reference Link](https://sdy-study.tistory.com/176)