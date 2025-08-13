package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
    private Clip clip;
    public SoundManager(String file_name) {
        try{
            AudioInputStream audio_input = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream("/sounds/"+file_name));
            Clip clip = AudioSystem.getClip();
            this.clip=clip;
            clip.open(audio_input);
            clip.start();
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }    }
    public void stop_music() {
        if(clip.isRunning()) {
            this.clip.stop();
        }

    }
}
