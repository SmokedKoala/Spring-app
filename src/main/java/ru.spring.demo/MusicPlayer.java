package ru.spring.demo;

/**
 * todo Document type MusicPlayer
 */
public class MusicPlayer {
    private Music music;
    private int volume;

    public MusicPlayer(Music music) {
        this.music = music;
    }

    public MusicPlayer() {
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void playMusic(){
        System.out.println("Now playing: "+this.music.getSong()+" with volume: "+this.getVolume());
    }
}
