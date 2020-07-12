package com.chowx;

/**
 * Created by zcqshine on 2017/2/20.
 */
public class FunctionTest {
    public static void main(String[] args) {
        Function func1 = new Function();
        func1.setId("1");
        func1.setFunctionReal("func1");

        Function func2 = new Function();
        func2.setId("2");
        func2.setFunctionReal("func2");

        Function func3 = new Function();
        func3.setId("3");
        func3.setFunctionReal("func3");

        Function func4 = new Function();
        func4.setId("4");
        func4.setFunctionReal("func1+func2");

        Function func5 = new Function();
        func5.setId("5");
        func5.setFunctionReal("func1+func4");

        Function func6 = new Function();
        func6.setId("6");
        func6.setFunctionReal("func4+fun3");

        Function func7 = new Function();
        func7.setId("7");
        func7.setFunctionReal("func5+func3");


    }
}
