package com.unam.aragon.arranque;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import com.unam.aragon.modelo.Fondo;

public class HelloApplication extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    //Set window settings
    public final int tamano_cuadro_default = 32;
    public final float escala = 1f;
    public final int cuadros_en_ancho = 25;
    public final int cuadros_en_largo = 13;
    public final int tamano_cuadro = (int) (tamano_cuadro_default * escala);
    public final int anchura_panel = tamano_cuadro * cuadros_en_ancho;
    public final int altura_panel = tamano_cuadro * cuadros_en_largo;
    //Sitio de arranque
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        componentsStart();
        graficar();
        stage.setTitle("GOTY del Año");
        stage.setScene(escena);
        stage.show();
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        ciclo();
    }

    private void componentsStart() throws URISyntaxException{
        root = new Group();
        escena = new Scene(root, anchura_panel, altura_panel);
        hoja = new Canvas(anchura_panel, altura_panel);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();
        fondo = new Fondo(0, 0, "fondo.jpg", 1,1f);
//        escena.setOnKeyPressed(keyEvent -> {
//            KeyCode code = keyEvent.getCode();
//            System.out.println("Tecla presionada: " + code);
//        });
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
            private int fps_counter;
            private long fps_timer;
            @Override
            public void handle(long tiempoActual) {
                double t = (tiempoActual - tiempoInicio) / 1000000000.0;
                logicaObjeto();
                graficar();
                //Contador de FPS, comentar a posterioridad, solo para comprobar rendimeitos y diversas utilidades.
                fps_counter++;
                if (tiempoActual - fps_timer >= 1000000000) { // Un segundo en nanosegundos
                    //System.out.println("FPS: " + fps_counter);
                    fps_counter = 0;
                    fps_timer = tiempoActual;
            }}
        };
        tiempo.start();
    }

    public static void main(String[] args) {
        launch();
    }
}