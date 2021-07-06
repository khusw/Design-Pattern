### Prototype-Pattern

> 정의    
- 프로토타입 패턴은 객체 생성시에 발생하는 비용이 많이들고, 비슷한 객체가 이미 있는 경우에 사용되는 생성패턴 중 하나이다.
- 프로토타입 패턴은 원래 객체를 새로운 객체에 복사해서 필요에 따라 수정하는 메커니즘을 제공한다
    
> 사전지식
- 얕은 복사 (Shallow Copy)  
: 복사할 객체의 주소값과 값도 복사하는 방식이다   
  동일한 주소를 가르키고 있으므로 인스턴스의 값 변화시 원본도 바뀐다
     

- 깊은 복사 (Deep Copy)   
: 복사할 객체의 값만 복사하는 방식이다    
  주소값은 서로 다른 주소값을 가지므로 값 변화시 원본값은 안바뀐다

   
복사 예제
- Cat Class
```java
public class Cat implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat copy() throws CloneNotSupportedException {
        return (Cat) clone();
    }
}
```

- Main Class
```java
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Cat navi = new Cat();
        navi.setName("navi");

        // shallow copy (기존 인스턴스(=navi)의 주소값을 그대로 복사하고 동일한 주소값을 가짐)
        Cat tom = navi;
        tom.setName("tom");

        // deep copy (기존 인스턴스와 다른 주소값을 가진채로 값만 복사함)
        Cat bucks = navi.copy();
        bucks.setName("bucks");

        System.out.println(navi.getName());
        System.out.println(tom.getName()); // navi 와 tom 은 동일한 값을 가짐
        System.out.println(bucks.getName()); // bucks 는 깊은 복사이므로 다른 값을 가짐

        System.out.println(navi); 
        System.out.println(tom); // 같은 주소값
        System.out.println(bucks); // 다른 주소값
    }
}
```

> 프로토타입 코드 예제
- 예를들어, DB 로 부터 값을 가져와서 프로그램 내에서 수정을 해야한다고 할때   
항상 모든 데이터를 DB 로 부터 읽어들여서 수정하는 방식은 별로 좋지 못한 방식이다   
  DB 에 접근해서 데이터를 가져오는데 드는 비용이 크기 때문이다   
  그래서 이런 방식대신, 한번만 DB 에 접근해서 데이터를 가져오고 필요에 따라 새 객체에 복사해서 수정하는게 나은 방법이다   
  DB 로 부터 직원들 리스트를 가져오고 추가하는 코드가 있다고 가정해보자


- Employee Class
```java
public class Employees implements Cloneable{
    private final List<String> employeeList;

    public Employees() {
        employeeList = new ArrayList<>();
    }

    public Employees(List<String> employeeList) {
        this.employeeList = employeeList;
    }

    public void loadData() {
        // DB 로 부터 데이터를 읽어와 리스트에 넣었다고 가정
        employeeList.add("Tom");
        employeeList.add("David");
        employeeList.add("Lisa");
    }

    public List<String> getEmployeeList() {
        return employeeList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<String> tempList = new ArrayList<>(this.employeeList);
        return new Employees(tempList);
    }
}
```

- Main Class
```java
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employees employees = new Employees();
        employees.loadData();

        Employees employees1 = (Employees) employees.clone();
        Employees employees2 = (Employees) employees.clone();

        List<String> list1 = employees1.getEmployeeList();
        list1.add("John");

        List<String> list2 = employees2.getEmployeeList();
        list2.add("Park");

        System.out.println("employees : " + employees.getEmployeeList());
        System.out.println("employees1 : " + employees1.getEmployeeList());
        System.out.println("employees2 : " + employees2.getEmployeeList());
    }
}
```

매번 DB 로 접근해서 모든 데이터를 불러오고 수정하는 것 보다는   
위처럼, 한번 불러온 이후 수정해주는 방식을 사용해서   
인스턴스의 생산 비용을 낮춘 것을 볼 수 있다.