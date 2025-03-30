package com.nox.tales.models;

public class UserPreferences {
    private String defaultNarrator;
    private double playbackSpeed;
    private boolean showTranscription;
    private boolean darkMode;
    private int fontSize;
    private boolean autoSync;
    private boolean autoDownload;
    private String audioQuality;
    
    // Construtor com valores padrão
    public UserPreferences() {
        this.defaultNarrator = "Narrador 1";
        this.playbackSpeed = 1.0;
        this.showTranscription = true;
        this.darkMode = true;
        this.fontSize = 16;
        this.autoSync = true;
        this.autoDownload = false;
        this.audioQuality = "Média";
    }
    
    // Construtor completo
    public UserPreferences(String defaultNarrator, double playbackSpeed, boolean showTranscription,
                          boolean darkMode, int fontSize, boolean autoSync, boolean autoDownload,
                          String audioQuality) {
        this.defaultNarrator = defaultNarrator;
        this.playbackSpeed = playbackSpeed;
        this.showTranscription = showTranscription;
        this.darkMode = darkMode;
        this.fontSize = fontSize;
        this.autoSync = autoSync;
        this.autoDownload = autoDownload;
        this.audioQuality = audioQuality;
    }
    
    // Getters e Setters
    public String getDefaultNarrator() {
        return defaultNarrator;
    }
    
    public void setDefaultNarrator(String defaultNarrator) {
        this.defaultNarrator = defaultNarrator;
    }
    
    public double getPlaybackSpeed() {
        return playbackSpeed;
    }
    
    public void setPlaybackSpeed(double playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
    }
    
    public boolean isShowTranscription() {
        return showTranscription;
    }
    
    public void setShowTranscription(boolean showTranscription) {
        this.showTranscription = showTranscription;
    }
    
    public boolean isDarkMode() {
        return darkMode;
    }
    
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }
    
    public int getFontSize() {
        return fontSize;
    }
    
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    
    public boolean isAutoSync() {
        return autoSync;
    }
    
    public void setAutoSync(boolean autoSync) {
        this.autoSync = autoSync;
    }
    
    public boolean isAutoDownload() {
        return autoDownload;
    }
    
    public void setAutoDownload(boolean autoDownload) {
        this.autoDownload = autoDownload;
    }
    
    public String getAudioQuality() {
        return audioQuality;
    }
    
    public void setAudioQuality(String audioQuality) {
        this.audioQuality = audioQuality;
    }
    
    // Método para converter a velocidade de reprodução para o formato de exibição
    public String getFormattedPlaybackSpeed() {
        return String.format("%.1fx", playbackSpeed);
    }
    
    // Método para definir a velocidade de reprodução a partir do formato de exibição
    public void setPlaybackSpeedFromFormatted(String formatted) {
        String value = formatted.replace("x", "");
        try {
            this.playbackSpeed = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // Em caso de erro, definir para o valor padrão
            this.playbackSpeed = 1.0;
        }
    }
}