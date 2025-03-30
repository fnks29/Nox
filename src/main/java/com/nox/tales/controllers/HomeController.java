package com.nox.tales.controllers;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Slider;

public class HomeController {
    @FXML
    private View view;
    
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private GridPane booksGrid;
    
    @FXML
    private StackPane parallaxBackground;
    
    @FXML
    private Slider progressSlider;
    
    @FXML
    private void initialize() {
        // Configurar efeito parallax
        scrollPane.vvalueProperty().addListener((obs, old, val) -> {
            double offset = val.doubleValue() * 0.3;
            parallaxBackground.setTranslateY(offset);
        });
        
        // Configurar AppBar
        AppBar appBar = view.getAppBar();
        appBar.setNavIcon(MaterialDesignIcon.MENU.graphic());
        appBar.setTitleText("NoxTales");
        
        // Inicializar player
        initializePlayer();
        
        // Carregar audiobooks
        loadAudiobooks();
    }
    
    private void initializePlayer() {
        progressSlider.valueProperty().addListener((obs, old, val) -> {
            // Atualizar posição do áudio
        });
    }
    
    private void loadAudiobooks() {
        // Carregar audiobooks da nuvem
        // Adicionar cards ao grid
    }
    
    @FXML
    private void playPause() {
        // Controlar reprodução
    }
    
    @FXML
    private void nextTrack() {
        // Próxima faixa
    }
    
    @FXML
    private void previousTrack() {
        // Faixa anterior
    }
}