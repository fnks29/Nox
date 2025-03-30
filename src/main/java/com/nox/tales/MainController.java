package com.nox.tales;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;

public class MainController {
    @FXML
    private Button startButton;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleExploreButton() {
        try {
            Main.loadScene("/com/nox/tales/explore.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            label.setText("Erro ao carregar a tela de exploração: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleLibraryButton() {
        try {
            // Aqui carregaríamos a tela da biblioteca, mas como ainda não temos o arquivo FXML,
            // exibimos uma mensagem temporária
            label.setText("Biblioteca em desenvolvimento!");
        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Erro ao carregar a biblioteca: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleProfileButton() {
        try {
            Main.loadScene("/com/nox/tales/profile.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            label.setText("Erro ao carregar o perfil: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleNarratorsButton() {
        try {
            // Aqui carregaríamos a tela de narradores, mas como ainda não temos o arquivo FXML,
            // exibimos uma mensagem temporária
            label.setText("Narradores em desenvolvimento!");
        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Erro ao carregar narradores: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleSettingsButton() {
        try {
            // Aqui carregaríamos a tela de configurações, mas como ainda não temos o arquivo FXML,
            // exibimos uma mensagem temporária
            label.setText("Configurações em desenvolvimento!");
        } catch (Exception e) {
            e.printStackTrace();
            label.setText("Erro ao carregar configurações: " + e.getMessage());
        }
    }
}