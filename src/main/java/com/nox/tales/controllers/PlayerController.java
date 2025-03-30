package com.nox.tales.controllers;

import com.nox.tales.AudioPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class PlayerController {
    @FXML
    private ImageView bookCover;
    
    @FXML
    private Rectangle reflection;
    
    @FXML
    private Label bookTitle;
    
    @FXML
    private Label bookAuthor;
    
    @FXML
    private ScrollPane transcriptionScroll;
    
    @FXML
    private Label transcriptionText;
    
    @FXML
    private Label currentTime;
    
    @FXML
    private Slider progressBar;
    
    @FXML
    private Label totalTime;
    
    @FXML
    private Button rewindButton;
    
    @FXML
    private Button playPauseButton;
    
    @FXML
    private Button forwardButton;
    
    @FXML
    private ComboBox<String> speedComboBox;
    
    private AudioPlayer audioPlayer;
    private boolean isPlaying = false;
    
    @FXML
    private void initialize() {
        // Inicializar o ComboBox de velocidade
        speedComboBox.setItems(FXCollections.observableArrayList("0.5x", "1.0x", "1.5x", "2.0x"));
        speedComboBox.setValue("1.0x");
        
        // Inicializar o player de áudio
        audioPlayer = new AudioPlayer();
        
        // Configurar o listener para o slider de progresso
        progressBar.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Implementar a lógica para atualizar a posição da reprodução
        });
        
        // Configurar o listener para a velocidade
        speedComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Implementar a lógica para alterar a velocidade de reprodução
        });
    }
    
    @FXML
    private void handlePlayPause() {
        if (isPlaying) {
            // Pausar a reprodução
            playPauseButton.setText("▶️");
            // audioPlayer.pause();
        } else {
            // Iniciar a reprodução
            playPauseButton.setText("⏸️");
            // audioPlayer.play();
        }
        isPlaying = !isPlaying;
    }
    
    @FXML
    private void handleRewind() {
        // Retroceder 10 segundos
        // audioPlayer.rewind(10);
    }
    
    @FXML
    private void handleForward() {
        // Avançar 10 segundos
        // audioPlayer.forward(10);
    }
    
    @FXML
    private void handleBackButton() {
        try {
            // Voltar para a tela principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nox/tales/main.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) bookTitle.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void navigateToLibrary() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nox/tales/main.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) bookTitle.getScene().getWindow();
            stage.setScene(scene);
            
            // Chamar o método para navegar para a biblioteca quando implementado
            // Por enquanto, apenas voltamos para a tela principal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void navigateToExplore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nox/tales/explore.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) bookTitle.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void navigateToNarrators() {
        try {
            // Por enquanto, vamos apenas exibir uma mensagem
            // Em uma implementação completa, carregaríamos uma tela específica para narradores
            handleBackButton(); // Voltar para a tela principal
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void navigateToSettings() {
        try {
            // Navegar para a tela de perfil que contém as configurações
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nox/tales/profile.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) bookTitle.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para atualizar a transcrição
    public void updateTranscription(String text) {
        transcriptionText.setText(text);
    }
    
    // Método para carregar um audiobook
    public void loadAudiobook(String title, String author, String coverUrl) {
        bookTitle.setText(title);
        bookAuthor.setText(author);
        // Carregar a imagem da capa
        // bookCover.setImage(new Image(coverUrl));
    }
}