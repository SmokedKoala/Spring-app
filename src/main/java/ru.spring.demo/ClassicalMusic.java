package ru.spring.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * todo Document type ClassicalMusic
 */
@Component
public class ClassicalMusic implements Music {

//    Bean LifeStyle
    @PostConstruct
    public void myInit(){
        System.out.println("Testing initialisation method");
    }
    @PreDestroy
    public void myDestroy(){
        System.out.println("Testing destroy method");
    }
    private ClassicalMusic(){}
    public static ClassicalMusic getClassicalMusic(){
        return new ClassicalMusic();
    }

    @Override
    public String getSong() {
        return "Piano Sonata No. 14";
    }
}
