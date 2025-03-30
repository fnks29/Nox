package com.nox.tales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        loadMainScene();
    }
    
    public static void loadMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/nox/tales/main.fxml"));
        Parent root = loader.load();
        
        // Criar uma cena com efeito parallax
        String[] layerUrls = {
            "/images/background_layer1.png",
            "/images/background_layer2.png"
        };
        double[] factors = {0.1, 0.05};
        
        ParallaxScene parallaxScene = new ParallaxScene(layerUrls, factors, 800, 600);
        Scene scene = parallaxScene.getScene();
        scene.setRoot(root);
        
        primaryStage.setTitle("NoxTales - Audiobook Inovador");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void loadScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}