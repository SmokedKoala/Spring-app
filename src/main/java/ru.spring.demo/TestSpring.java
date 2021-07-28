package ru.spring.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * todo Document type TestSpring
 */
public class TestSpring {
    public static void main(String[] args) {
//        XML Configuration
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        Java class Configuration
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

//        test Spring
//        TestBean testBean = context.getBean("testBean", TestBean.class);
//        System.out.println(testBean.getName());

//        Inversion of Control
//        Music music = context.getBean("musicBean", Music.class);
//        MusicPlayer musicPlayer = new MusicPlayer(music);

//        Dependency Injection
//        MusicPlayer musicPlayer = context.getBean("musicPlayer",MusicPlayer.class);
//        musicPlayer.playMusic();

//        Scope
//        MusicPlayer musicPlayer2 = context.getBean("musicPlayer",MusicPlayer.class);
//        System.out.println(musicPlayer == musicPlayer2);

//        Аннотации
        Computer computer = context.getBean("computer", Computer.class);
        computer.openMusicPlayer();

        context.close();
    }
}
