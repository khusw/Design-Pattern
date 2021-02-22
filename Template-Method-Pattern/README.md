### Template Method Pattern

> 정의 <br>
- 알고리즘의 구조를 메소드에 정의하고 하위 클래스에서 알고리즘 구조의 변경 없이 알고리즘을 재정의 하는 패턴

- 알고리즘이 단계별로 나누어 지거나, 같은 역할을 하는 메소드이지만 여러곳에서 다른형태로 사용이 필요한 경우 유용한 패턴이다.


<br>

> 사전지식 <br>
- 추상 클래스와 인터페이스 <br>
: [추상 클래스 vs 인터페이스](https://myjamong.tistory.com/150)
- protected 키워드 <br>
: [접근 제어자](http://www.tcpschool.com/java/java_modifier_accessModifier)

<br>  

> 코드 예제 <br>
- 예를들어, 다음과 같은 요구 사항을 개발해야 된다고 가정해보자
1) 신작 게임에 유저가 접속할 수 있는 함수를 구현해야한다
   - 이때 함수 이름은 아래와 같이 한다
    <br> String requestConnection(String str)
     
2) 유저가 접속하는 동안의 알고리즘을 구현하기 위한 절차는 다음의 4단계로 나뉜다
   - 보안 과정을 처리하는 부분
    <br> String doSecurity(String endcodedStr)
   - 유저의 아이디와 비밀번호가 일치하는지 확인하는 인증 과정 부분
    <br> boolean authentication(String id, String pw) 
   - 접속한 유저의 유형을 확인하는 부분
    <br> int authorization(String userName) 
   - 최종적으로 접속을 시도해서 커넥션 정보를 넘기는 부분
    <br> String connection(String info)
     

```java
public abstract class GameConnectionHelper {
    protected abstract String doSecurity(String str);
    protected abstract boolean authentication(String id, String password);
    protected abstract int authorization(String userName);
    protected abstract String connection(String info);

    // template method
    public String requestConnection(String encodedStr) {
        
        String decodedStr = doSecurity(encodedStr);
        System.out.println(decodedStr);

        String id = "aaa";
        String pw = "bbb";

        if (!authentication(id, pw)) {
            throw new Error("일치하는 정보가 없음");
        }

        String userName = "?";
        int userIdx = authorization(userName);

        switch (userIdx) {
            case -1:
                System.out.println("셧다운제에 걸려 종료함");
                throw new Error("Shutdown because goverment's policy");
            case 1:
                System.out.println("이 유저는 게임 매니저임");
                break;
            case 2:
                System.out.println("이 유저는 유료 회원임");
                break;
            case 3:
                System.out.println("이 유저는 무료 회원임");
                break;
            default:
                System.out.println("권한이 없는 사용자임");
                break;
        }

        return connection(decodedStr);
    }
}
```
-> 유저의 접속 과정에서 거치는 하나의 공통적인 알고리즘 절차를 하나의 추상 클래스에 정의해둬서
이를 상속 받는 하위 클래스에서 알고리즘의 구현부분만 재정의 할 수 있게 하면,
코드의 유지보수성을 높이고 가독성을 높이는 장점이 있다.

<br>

```java
public class DefaultGameConnectionHelper extends GameConnectionHelper {
    @Override
    protected String doSecurity(String str) {
        System.out.println("디코딩 작업을 하는 부분");
        System.out.println("추가된 강화작업");
        return str;
    }

    @Override
    protected boolean authentication(String id, String password) {
        System.out.println("DB 에 접속하여 ID, PW 일치하는지 확인하는 부분");
        return true;
    }

    @Override
    protected int authorization(String userName) {
        System.out.println("DB 에 접속해서 유저 타입이 무엇인지 확인하는 부분");
        System.out.println("유저의 나이를 확인하여 셧다운제에 걸리는지 파악");
        return 0;
    }

    @Override
    protected String connection(String info) {
        System.out.println("마지막으로 접속을 완료하는 부분");
        return info;
    }
}
```
-> 추상 클래스에서 선언한 알고리즘 절차를, 하위 클래스에서 재정의 하게 함으로써
알고리즘의 세부사항이 변경되면, '구조' 는 변경시키지 않고 '구현'만 변경시키게 해줌


<br>

```java
import com.tmp.libraries.DefaultGameConnectionHelper;
import com.tmp.libraries.GameConnectionHelper;

public class Main {
    public static void main(String[] args) {
        GameConnectionHelper gameConnectionHelper = new DefaultGameConnectionHelper();

        gameConnectionHelper.requestConnection("어떤 정보를 넣음");
    }
}
```
-> 메인 함수

<br>

_정리하면, 템플릿 메소드 패턴은 어떤 알고리즘을 구현할때, 전체적인 알고리즘의 틀은 살려놓되,
일부분 구현적인 측면에서 변경이 일어나는 경우 사용하기에 적합한 디자인 패턴이다._