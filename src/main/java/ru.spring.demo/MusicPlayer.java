package ru.spring.demo;

/**
 * todo Document type MusicPlayer
 */
public class MusicPlayer {
    private Music music;

    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic(){
        System.out.println(this.music.getSong());
    }
}
