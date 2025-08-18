package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

//מחלקה האחראית על השמעת הסאונד
public class SoundManager {
    private Clip clip;
    public SoundManager(String file_name) {
        try{
            AudioInputStream audio_input = AudioSystem.getAudioInputStream(Objects.requireNonNull(SoundManager.class.getResourceAsStream("/sounds/" + file_name)));
            Clip clip = AudioSystem.getClip();
            this.clip=clip;
            clip.open(audio_input);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }    }
    public boolean clip_is_runnig(){
        return clip.isRunning();
    }
    public void stop_music() {
        if (clip.isRunning()) {
            this.clip.stop();
        }
    }
        public void start_music () {
            if (!clip.isRunning()) {
                this.clip.start();
            }
        }
        public SoundManager(SoundManager sound){
        this.clip=sound.getClip();
        }

    public Clip getClip() {
        return clip;
    }

    public void switch_status() {
            if (clip.isRunning()) {
                this.clip.stop();
            }else {
                this.clip.start();
            }
        }
}
