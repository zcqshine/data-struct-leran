package lambda;

/**
 * @author zcqshine
 * @date 2017/11/9
 */
public class A {
    private int i;
    public static A a = new A();

    public void test(){
        System.out.println("test");
    }

    public static void main(String[] args) {
        a.test();
        a.i = 10;
        System.out.println(a.i);
    }
}
