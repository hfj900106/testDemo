package com.example.demo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hfj
 * @date 2018/11/21
 */
public class TestModifier extends Parent {
    public static void testStatic () {
        System.out.println("test static");
    }

    private  int add (int a,int b ) {
        return a + b;
    }

    public void testException () throws IllegalAccessException {
        throw new IllegalAccessException("You have some problem.");
    }



//    public static void main(String[] args) throws Exception {
////        System.out.println("modifiers value:"+TestModifier.class.getModifiers());
////        System.out.println("modifiers :"+Modifier.toString(TestModifier.class.getModifiers()));
////        Field[] declaredFields = TestModifier.class.getDeclaredFields();
////        Field[] declaredFieldsP = Parent.class.getDeclaredFields();
////        Field[] fields = TestModifier.class.getFields();
////        Field[] fieldsP = Parent.class.getFields();
////        Method methodsP = Parent.class.getDeclaredMethod("func1",null);
////        System.out.println(JSON.toJSONString(declaredFields));
//
//
//
//
//
//    }

    public static void main(String[] args) {
        Class testCls = TestModifier.class;

        try {
            // 测试静态方法
            Method mStatic = testCls.getMethod("testStatic",null);
            mStatic.invoke(null, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        TestModifier t = new TestModifier();
        try {
            Method mAdd = testCls.getDeclaredMethod("add",int.class,int.class);
            // 通过这句代码才能访问 private 修饰的 Method
            mAdd.setAccessible(true);
            int result = (int) mAdd.invoke(t, 1,2);
            System.out.println("add method result:"+result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            Method testExcep = testCls.getMethod("testException",null);

            try {
                testExcep.invoke(t, null);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();

                // 通过 InvocationTargetException.getCause() 获取被包装的异常
                System.out.println("testException occur some error,Error type is :"+e.getCause().getClass().getName());
                System.out.println("Error message is :"+e.getCause().getMessage());
            }


        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

class Parent {
    public int p;
    public void func1(){
    }
}