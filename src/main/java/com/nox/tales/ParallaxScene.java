package com.nox.tales;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ParallaxScene {
    private Pane root;
    private Scene scene;
    private ImageView[] layers;
    private double[] parallaxFactors;
    
    public ParallaxScene(String[] layerUrls, double[] factors, int width, int height) {
        root = new Pane();
        scene = new Scene(root, width, height);
        layers = new ImageView[layerUrls.length];
        parallaxFactors = factors;
        
        for (int i = 0; i < layerUrls.length; i++) {
            layers[i] = new ImageView(new Image(layerUrls[i]));
            root.getChildren().add(layers[i]);
        }
        
        scene.setOnMouseMoved(e -> {
            for (int i = 0; i < layers.length; i++) {
                layers[i].setLayoutX(-e.getX() * parallaxFactors[i]);
                layers[i].setLayoutY(-e.getY() * parallaxFactors[i]);
            }
        });
    }
    
    public Scene getScene() {
        return scene;
    }
}