package com.nox.tales.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String id;
    private String username;
    private String email;
    private String photoUrl;
    private List<UserProgress> audiobookProgress;
    private List<String> favoriteAudiobooks;
    private UserPreferences preferences;
    
    // Construtor
    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.audiobookProgress = new ArrayList<>();
        this.favoriteAudiobooks = new ArrayList<>();
        this.preferences = new UserPreferences();
    }
    
    // Getters e Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhotoUrl() {
        return photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
    public List<UserProgress> getAudiobookProgress() {
        return audiobookProgress;
    }
    
    public void setAudiobookProgress(List<UserProgress> audiobookProgress) {
        this.audiobookProgress = audiobookProgress;
    }
    
    public List<String> getFavoriteAudiobooks() {
        return favoriteAudiobooks;
    }
    
    public void setFavoriteAudiobooks(List<String> favoriteAudiobooks) {
        this.favoriteAudiobooks = favoriteAudiobooks;
    }
    
    public UserPreferences getPreferences() {
        return preferences;
    }
    
    public void setPreferences(UserPreferences preferences) {
        this.preferences = preferences;
    }
    
    // Métodos para gerenciar o progresso dos audiobooks
    public void updateProgress(String audiobookId, long position, double percentage) {
        // Procurar se já existe um progresso para este audiobook
        for (UserProgress progress : audiobookProgress) {
            if (progress.getAudiobookId().equals(audiobookId)) {
                progress.setPosition(position);
                progress.setPercentage(percentage);
                progress.setLastUpdated(System.currentTimeMillis());
                return;
            }
        }
        
        // Se não existir, criar um novo
        UserProgress newProgress = new UserProgress(audiobookId, position, percentage);
        audiobookProgress.add(newProgress);
    }
    
    // Método para adicionar um audiobook aos favoritos
    public void addFavorite(String audiobookId) {
        if (!favoriteAudiobooks.contains(audiobookId)) {
            favoriteAudiobooks.add(audiobookId);
        }
    }
    
    // Método para remover um audiobook dos favoritos
    public void removeFavorite(String audiobookId) {
        favoriteAudiobooks.remove(audiobookId);
    }
    
    // Método para verificar se um audiobook é favorito
    public boolean isFavorite(String audiobookId) {
        return favoriteAudiobooks.contains(audiobookId);
    }
    
    // Método para obter o progresso de um audiobook específico
    public UserProgress getProgressForAudiobook(String audiobookId) {
        for (UserProgress progress : audiobookProgress) {
            if (progress.getAudiobookId().equals(audiobookId)) {
                return progress;
            }
        }
        return null;
    }
    
    // Método para obter os audiobooks recentes (ordenados por data de atualização)
    public List<UserProgress> getRecentAudiobooks(int limit) {
        // Ordenar por data de atualização (do mais recente para o mais antigo)
        audiobookProgress.sort((p1, p2) -> Long.compare(p2.getLastUpdated(), p1.getLastUpdated()));
        
        // Retornar os primeiros 'limit' elementos ou todos se houver menos que o limite
        int size = Math.min(limit, audiobookProgress.size());
        return audiobookProgress.subList(0, size);
    }
    
    // Classe interna para representar o progresso do usuário em um audiobook
    public static class UserProgress {
        private String audiobookId;
        private long position; // Posição em milissegundos
        private double percentage; // Porcentagem concluída (0-100)
        private long lastUpdated; // Timestamp da última atualização
        
        public UserProgress(String audiobookId, long position, double percentage) {
            this.audiobookId = audiobookId;
            this.position = position;
            this.percentage = percentage;
            this.lastUpdated = System.currentTimeMillis();
        }
        
        public String getAudiobookId() {
            return audiobookId;
        }
        
        public void setAudiobookId(String audiobookId) {
            this.audiobookId = audiobookId;
        }
        
        public long getPosition() {
            return position;
        }
        
        public void setPosition(long position) {
            this.position = position;
        }
        
        public double getPercentage() {
            return percentage;
        }
        
        public void setPercentage(double percentage) {
            this.percentage = percentage;
        }
        
        public long getLastUpdated() {
            return lastUpdated;
        }
        
        public void setLastUpdated(long lastUpdated) {
            this.lastUpdated = lastUpdated;
        }
    }
}