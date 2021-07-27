package ru.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * todo Document type MusicPlayer
 */
@Component
public class MusicPlayer {

    private Music music1;
    private Music music2;
    @Value("${musicPlayer.volume}")
    private int volume;

    @Autowired
    public MusicPlayer(@Qualifier("rockMusic")Music music1, @Qualifier("classicalMusic")Music music2) {
        this.music1 = music1;
        this.music2 = music2;
    }




    public MusicPlayer() {
    }

    public void setMusic(Music music1) {
        this.music1 = music1;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void playMusic(){
        System.out.println("Now playing:\n"+this.music1.getSong()+"\n"+this.music2.getSong()+"\nwith volume: "+this.getVolume());
    }
}
