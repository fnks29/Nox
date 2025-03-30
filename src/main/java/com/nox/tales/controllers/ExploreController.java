package com.nox.tales.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Arrays;

public class ExploreController {
    @FXML
    private TextField searchField;
    
    @FXML
    private ComboBox<String> categoryFilter;
    
    @FXML
    private ComboBox<String> authorFilter;
    
    @FXML
    private ComboBox<String> narratorFilter;
    
    @FXML
    private HBox featuredCarousel;
    
    @FXML
    private FlowPane recommendedGrid;
    
    @FXML
    private FlowPane popularGrid;
    
    @FXML
    private HBox narratorsCarousel;
    
    @FXML
    private void initialize() {
        // Inicializar os filtros
        categoryFilter.setItems(FXCollections.observableArrayList(
            "Fantasia", "Ficção Científica", "Romance", "Suspense", "Não-Ficção"
        ));
        
        authorFilter.setItems(FXCollections.observableArrayList(
            "J.R.R. Tolkien", "George R.R. Martin", "J.K. Rowling", "Stephen King", "Agatha Christie"
        ));
        
        narratorFilter.setItems(FXCollections.observableArrayList(
            "Narrador 1", "Narrador 2", "Narrador 3", "Narrador 4", "Narrador 5"
        ));
        
        // Carregar dados de exemplo
        loadFeaturedBooks();
        loadRecommendedBooks();
        loadPopularBooks();
        loadFeaturedNarrators();
    }
    
    private void loadFeaturedBooks() {
        // Aqui seria implementada a lógica para carregar os livros em destaque do Supabase
        // Por enquanto, vamos adicionar alguns exemplos estáticos
        String[] titles = {"O Senhor dos Anéis", "Harry Potter", "Game of Thrones", "O Hobbit"};
        String[] authors = {"J.R.R. Tolkien", "J.K. Rowling", "George R.R. Martin", "J.R.R. Tolkien"};
        
        // Limpar o carrossel existente (exceto o primeiro item de exemplo)
        if (featuredCarousel.getChildren().size() > 1) {
            featuredCarousel.getChildren().remove(1, featuredCarousel.getChildren().size());
        }
        
        // Adicionar novos itens
        for (int i = 0; i < titles.length; i++) {
            VBox bookItem = createBookItem(titles[i], authors[i], "/images/book" + (i+2) + ".png");
            featuredCarousel.getChildren().add(bookItem);
        }
    }
    
    private void loadRecommendedBooks() {
        // Aqui seria implementada a lógica para carregar recomendações do Supabase
        String[] titles = {"Duna", "Neuromancer", "Fundação", "1984", "Blade Runner"};
        String[] authors = {"Frank Herbert", "William Gibson", "Isaac Asimov", "George Orwell", "Philip K. Dick"};
        
        recommendedGrid.getChildren().clear();
        
        for (int i = 0; i < titles.length; i++) {
            VBox bookItem = createBookItem(titles[i], authors[i], "/images/rec" + (i+1) + ".png");
            recommendedGrid.getChildren().add(bookItem);
        }
    }
    
    private void loadPopularBooks() {
        // Aqui seria implementada a lógica para carregar livros populares do Supabase
        String[] titles = {"O Código Da Vinci", "A Menina que Roubava Livros", "As Crônicas de Nárnia"};
        String[] authors = {"Dan Brown", "Markus Zusak", "C.S. Lewis"};
        
        popularGrid.getChildren().clear();
        
        for (int i = 0; i < titles.length; i++) {
            VBox bookItem = createBookItem(titles[i], authors[i], "/images/pop" + (i+1) + ".png");
            popularGrid.getChildren().add(bookItem);
        }
    }
    
    private void loadFeaturedNarrators() {
        // Aqui seria implementada a lógica para carregar narradores em destaque
        String[] names = {"Narrador 1", "Narrador 2", "Narrador 3", "Narrador 4"};
        
        narratorsCarousel.getChildren().clear();
        
        for (int i = 0; i < names.length; i++) {
            VBox narratorItem = createNarratorItem(names[i], "/images/narrator" + (i+1) + ".png");
            narratorsCarousel.getChildren().add(narratorItem);
        }
    }
    
    private VBox createBookItem(String title, String author, String imageUrl) {
        VBox bookItem = new VBox(10);
        bookItem.setStyle("-fx-background-color: #ffffff10; -fx-background-radius: 10; -fx-padding: 10;");
        
        // Imagem da capa (em uma aplicação real, carregaríamos do URL)
        ImageView coverView = new ImageView();
        coverView.setFitWidth(150);
        coverView.setFitHeight(200);
        coverView.setPreserveRatio(true);
        
        // Título e autor
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label authorLabel = new Label(author);
        authorLabel.setStyle("-fx-font-size: 12; -fx-text-fill: #cccccc;");
        
        bookItem.getChildren().addAll(coverView, titleLabel, authorLabel);
        
        // Adicionar evento de clique para abrir o player
        bookItem.setOnMouseClicked(event -> openAudiobookPlayer(title, author, imageUrl));
        
        return bookItem;
    }
    
    private VBox createNarratorItem(String name, String imageUrl) {
        VBox narratorItem = new VBox(10);
        narratorItem.setAlignment(javafx.geometry.Pos.CENTER);
        narratorItem.setStyle("-fx-padding: 10;");
        
        // Imagem do narrador (circular)
        ImageView photoView = new ImageView();
        photoView.setFitWidth(80);
        photoView.setFitHeight(80);
        photoView.setPreserveRatio(true);
        // Em uma aplicação real, aplicaríamos um clip circular à imagem
        
        // Nome do narrador
        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        
        narratorItem.getChildren().addAll(photoView, nameLabel);
        
        return narratorItem;
    }
    
    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText();
        // Implementar a lógica de busca
        System.out.println("Buscando por: " + searchTerm);
    }
    
    private void openAudiobookPlayer(String title, String author, String coverUrl) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/nox/tales/player.fxml"));
            Parent root = loader.load();
            
            // Configurar o controlador do player
            PlayerController playerController = loader.getController();
            playerController.loadAudiobook(title, author, coverUrl);
            
            // Abrir a tela do player
            Scene scene = new Scene(root);
            Stage stage = (Stage) searchField.getScene().getWindow();
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
            Stage stage = (Stage) searchField.getScene().getWindow();
            stage.setScene(scene);
            
            // Chamar o método para navegar para a biblioteca quando implementado
            // Por enquanto, apenas voltamos para a tela principal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void navigateToExplore() {
        // Já estamos na tela de exploração, não precisamos navegar
        // Podemos atualizar os dados se necessário
        initialize();
    }
    
    @FXML
    private void navigateToNarrators() {
        try {
            // Por enquanto, vamos apenas exibir os narradores em destaque
            // Em uma implementação completa, carregaríamos uma tela específica para narradores
            loadFeaturedNarrators();
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
            Stage stage = (Stage) searchField.getScene().getWindow();
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
        // Implementar navegação para as configurações
    }
}