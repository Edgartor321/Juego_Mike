package com.unam.aragon.arranque;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import com.unam.aragon.modelo.Fondo;
import com.unam.aragon.modelo.ComponentesJuego;
public class HelloApplication extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        componentsStart();
        graficar();
        stage.setTitle("GOTY del Año");
        stage.setScene(escena);
        stage.show();
        ciclo();
    }

    private void componentsStart() throws URISyntaxException{
        root = new Group();
        escena = new Scene(root, 600, 600);
        hoja = new Canvas(600, 600);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();
        fondo = new Fondo(0, 0, "fondo2.jpg", 12,9.8f);
    }
    private void graficar(){
        fondo.graficar(graficos);
    }
    private void logicaObjeto(){
        this.fondo.logicaObjeto();
    }
    private void ciclo() {
        long tiempoInicio = System.nanoTime();
        AnimationTimer tiempo = new AnimationTimer() {
            @Override
            public void handle(long tiempoActual) {
                double t = (tiempoActual - tiempoInicio) / 1000000000.0;
                //personajeA.setTiempo(t);
                logicaObjeto();
                graficar();

            }
        };
        tiempo.start();
    }

    public static void main(String[] args) {
        launch();
    }
}