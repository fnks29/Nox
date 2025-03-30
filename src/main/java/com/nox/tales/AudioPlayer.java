package com.nox.tales;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AudioPlayer {
    private MediaPlayer mediaPlayer;
    
    public void playAudioFromBytes(byte[] audioData) throws IOException {
        Path tempFile = Files.createTempFile("nox-audio", ".mp3");
        try (FileOutputStream fos = new FileOutputStream(tempFile.toFile())) {
            fos.write(audioData);
        }
        
        Media media = new Media(tempFile.toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        
        mediaPlayer.setOnEndOfMedia(() -> {
            try {
                Files.deleteIfExists(tempFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}