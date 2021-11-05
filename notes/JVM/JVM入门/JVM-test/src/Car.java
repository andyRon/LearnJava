/**
 * @author Andy Ron
 */
public class Car {

    public static void main(String[] args) {
        // 类是模板，抽象的，对象时具体的
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

//        System.out.println(car1.hashCode());
//        System.out.println(car2.hashCode());
//        System.out.println(car3.hashCode());

        Class<? extends Car> aClass1 = car1.getClass();
        Class<? extends Car> aClass2 = car2.getClass();
        Class<? extends Car> aClass3 = car3.getClass();

//        System.out.println(aClass1.hashCode());
//        System.out.println(aClass2.hashCode());
//        System.out.println(aClass3.hashCode());

        ClassLoader classLoader = aClass1.getClassLoader();
        System.out.println(classLoader);                // AppClassLoader
        System.out.println(classLoader.getParent());    // ExtClassLoader
        System.out.println(classLoader.getParent().getParent()); // null 1.不存在 2.java程序获取不到（因为时C/C++）



    }
}
