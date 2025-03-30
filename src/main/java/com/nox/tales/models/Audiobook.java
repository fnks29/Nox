package com.nox.tales.models;

import java.time.Duration;

public class Audiobook {
    private String id;
    private String title;
    private String author;
    private String narrator;
    private String coverUrl;
    private String description;
    private String category;
    private Duration duration;
    private String audioUrl;
    private String transcriptionText;
    
    // Construtor
    public Audiobook(String id, String title, String author, String narrator, String coverUrl, 
                    String description, String category, Duration duration, String audioUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.narrator = narrator;
        this.coverUrl = coverUrl;
        this.description = description;
        this.category = category;
        this.duration = duration;
        this.audioUrl = audioUrl;
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getNarrator() {
        return narrator;
    }
    
    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }
    
    public String getCoverUrl() {
        return coverUrl;
    }
    
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Duration getDuration() {
        return duration;
    }
    
    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    
    public String getAudioUrl() {
        return audioUrl;
    }
    
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }
    
    public String getTranscriptionText() {
        return transcriptionText;
    }
    
    public void setTranscriptionText(String transcriptionText) {
        this.transcriptionText = transcriptionText;
    }
    
    // Método para formatar a duração em formato legível
    public String getFormattedDuration() {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        
        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%d:%02d", minutes, seconds);
        }
    }
}