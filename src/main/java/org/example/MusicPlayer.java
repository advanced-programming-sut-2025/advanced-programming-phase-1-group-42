package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer implements Runnable {

    private String filePath;

    public MusicPlayer(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            // صبر کنه تا موسیقی کامل پخش بشه، بدون اینکه برنامه اصلی رو متوقف کنه
            while (true) {
                Thread.sleep(1000);
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
