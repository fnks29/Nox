package com.nox.tales.controllers;

import com.nox.tales.SupabaseConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.io.IOException;

public class ProfileController {
    @FXML
    private Circle profilePhotoCircle;
    
    @FXML
    private ImageView profilePhoto;
    
    @FXML
    private Label usernameLabel;
    
    @FXML
    private Label emailLabel;
    
    @FXML
    private Label booksCountLabel;
    
    @FXML
    private Label hoursLabel;
    
    @FXML
    private Label favoritesLabel;
    
    @FXML
    private VBox recentBooksContainer;
    
    @FXML
    private ComboBox<String> defaultSpeedComboBox;
    
    @FXML
    private ComboBox<String> defaultNarratorComboBox;
    
    @FXML
    private CheckBox showTranscriptionCheckbox;
    
    @FXML
    private ToggleButton darkModeToggle;
    
    @FXML
    private Slider fontSizeSlider;
    
    @FXML
    private CheckBox autoSyncCheckbox;
    
    @FXML
    private CheckBox autoDownloadCheckbox;
    
    @FXML
    private ComboBox<String> audioQualityComboBox;
    
    @FXML
    private void initialize() {
        // Inicializar os ComboBoxes
        defaultSpeedComboBox.setItems(FXCollections.observableArrayList(
            "0.5x", "1.0x", "1.5x", "2.0x"
        ));
        defaultSpeedComboBox.setValue("1.0x");
        
        defaultNarratorComboBox.setItems(FXCollections.observableArrayList(
            "Narrador 1", "Narrador 2", "Narrador 3", "Narrador 4", "Narrador 5"
        ));
        defaultNarratorComboBox.setValue("Narrador 1");
        
        audioQualityComboBox.setItems(FXCollections.observableArrayList(
            "Baixa", "Média", "Alta"
        ));
        audioQualityComboBox.setValue("Média");
        
        // Carregar dados do usuário
        loadUserData();
        
        // Carregar audiobooks recentes
        loadRecentBooks();
        
        // Carregar preferências salvas
        loadUserPreferences();
    }
    
    private void loadUserData() {
        // Em uma aplicação real, carregaríamos os dados do usuário do Supabase
        usernameLabel.setText("João Silva");
        emailLabel.setText("joao.silva@email.com");
        booksCountLabel.setText("12");
        hoursLabel.setText("24h");
        favoritesLabel.setText("5");
    }
    
    private void loadRecentBooks() {
        // Em uma aplicação real, carregaríamos os audiobooks recentes do Supabase
        String[] titles = {"O Último Desejo", "A Torre Negra", "Duna"};
        String[] authors = {"Andrzej Sapkowski", "Stephen King", "Frank Herbert"};
        String[] progress = {"75%", "30%", "10%"};
        
        recentBooksContainer.getChildren().clear();
        
        for (int i = 0; i < titles.length; i++) {
            HBox bookItem = createRecentBookItem(titles[i], authors[i], progress[i]);
            recentBooksContainer.getChildren().add(bookItem);
        }
    }
    
    private HBox createRecentBookItem(String title, String author, String progress) {
        HBox bookItem = new HBox(15);
        bookItem.setStyle("-fx-background-color: #ffffff10; -fx-background-radius: 5; -fx-padding: 10;");
        
        // Capa do livro
        ImageView coverView = new ImageView();
        coverView.setFitWidth(60);
        coverView.setFitHeight(80);
        coverView.setPreserveRatio(true);
        
        // Informações do livro
        VBox infoBox = new VBox(5);
        infoBox.setPrefWidth(200);
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label authorLabel = new Label(author);
        authorLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #cccccc;");
        
        // Barra de progresso
        ProgressBar progressBar = new ProgressBar(Double.parseDouble(progress.replace("%", "")) / 100);
        progressBar.setPrefWidth(180);
        
        Label progressLabel = new Label(progress);
        progressLabel.setStyle("-fx-text-fill: #4CAF50;");
        
        infoBox.getChildren().addAll(titleLabel, authorLabel, progressBar, progressLabel);
        
        // Botão para continuar ouvindo
        Button continueButton = new Button("Continuar");
        continueButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        continueButton.setOnAction(e -> openAudiobookPlayer(title, author));
        
        bookItem.getChildren().addAll(coverView, infoBox, continueButton);
        
        return bookItem;
    }
    
    private void loadUserPreferences() {
        // Em uma aplicação real, carregaríamos as preferências do usuário do Supabase
        // Por enquanto, vamos definir alguns valores padrão
        showTranscriptionCheckbox.setSelected(true);
        darkModeToggle.setSelected(true);
        fontSizeSlider.setValue(16);
        autoSyncCheckbox.setSelected(true);
        autoDownloadCheckbox.setSelected(false);
    }
    
    @FXML
    private void handleSavePreferences() {
        // Em uma aplicação real, salvaríamos as preferências no Supabase
        // Por enquanto, vamos apenas exibir uma mensagem
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Preferências Salvas");
        alert.setHeaderText(null);
        alert.setContentText("Suas preferências foram salvas com sucesso!");
        alert.showAndWait();
    }
    
    private void openAudiobookPlayer(String title, String author) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nox/tales/player.fxml"));
            Parent root = loader.load();
            
            // Configurar o controlador do player
            PlayerController playerController = loader.getController();
            playerController.loadAudiobook(title, author, "");
            
            // Abrir a tela do player
            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameLabel.getScene().getWindow();
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
            Stage stage = (Stage) usernameLabel.getScene().getWindow();
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
            Stage stage = (Stage) usernameLabel.getScene().getWindow();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Narradores");
            alert.setHeaderText(null);
            alert.setContentText("Tela de narradores em desenvolvimento!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void navigateToSettings() {
        // Já estamos na tela de perfil que contém as configurações
        // Podemos focar na aba de configurações se necessário
    }
    
    @FXML
    private void navigateToExplore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nox/tales/explore.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) usernameLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void navigateToNarrators() {
        // Implementar navegação para a tela de narradores
    }
    
    @FXML
    private void navigateToSettings() {
        // Já estamos na tela de configurações
    }
}