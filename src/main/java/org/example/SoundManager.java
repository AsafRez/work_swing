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
    public void stop_music() {
        if(clip.isRunning()) {
            this.clip.stop();
        }

    }
}
