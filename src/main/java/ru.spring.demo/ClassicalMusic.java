package ru.spring.demo;

/**
 * todo Document type ClassicalMusic
 */
public class ClassicalMusic implements Music {

//    Bean LifeStyle
    public void myInit(){
        System.out.println("Testing initialisation method");
    }
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
