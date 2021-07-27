package ru.spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * todo Document type TestSpring
 */
public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        test Spring
//        TestBean testBean = context.getBean("testBean", TestBean.class);
//        System.out.println(testBean.getName());

//        Inversion of Control
//        Music music = context.getBean("musicBean", Music.class);
//        MusicPlayer musicPlayer = new MusicPlayer(music);

//        Dependency Injection
        MusicPlayer musicPlayer = context.getBean("musicPlayer",MusicPlayer.class);
        musicPlayer.playMusic();

//        Scope
        MusicPlayer musicPlayer2 = context.getBean("musicPlayer",MusicPlayer.class);
        System.out.println(musicPlayer == musicPlayer2);

        context.close();
    }
}
