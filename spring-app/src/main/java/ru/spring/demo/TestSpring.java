package ru.spring.demo;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * todo Document type TestSpring
 */
public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TestBean testBean = context.getBean("testBean", TestBean.class);
        System.out.println(testBean.getName());

        context.close();
    }
}
