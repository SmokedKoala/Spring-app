package ru.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * todo Document type Computer
 */
@Component
public class Computer {
    private int id;
    @Autowired
    private MusicPlayer player;

    public void openMusicPlayer(){
        System.out.println("Computer starts working...");
        player.playMusic();
    }
}
