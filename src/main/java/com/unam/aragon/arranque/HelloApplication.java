package com.unam.aragon.arranque;

import com.unam.aragon.modelo.PersonajePrueba;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import com.unam.aragon.modelo.Fondo;

public class HelloApplication extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    //Establecer configuraciones de ventana.
    public static final int tamano_cuadro_default = 32;
    public static final int cuadros_en_ancho = 25;
    public static final int cuadros_en_largo = 13;
    public static final float escala = 1.0f;
    public static final int tamano_cuadro = (int) (tamano_cuadro_default * escala);
    public static final int anchura_panel = tamano_cuadro * cuadros_en_ancho;
    public static final int altura_panel = tamano_cuadro * cuadros_en_largo;
    //Input Loger variables
    private boolean arriba_presionada=false;
    private boolean abajo_presionada=false;
    private boolean der_presionada=false;
    private boolean izq_presionada=false;
    private PersonajePrueba personajePrueba;
    private static final int tope_fps=120;

    //Sitio de arranque
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        arrancador();
        graficar();
        stage.setTitle("GOTY del Año");
        stage.setScene(escena);
        stage.show();
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
//        inicia_ciclo();
        ciclo();
    }

    private void arrancador() throws URISyntaxException{
        root = new Group();
        escena = new Scene(root, anchura_panel, altura_panel);
        hoja = new Canvas(anchura_panel, altura_panel);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();
        fondo = new Fondo(0, 0, "fondo.jpg", 1,1f);
        personajePrueba =new PersonajePrueba(300,300,"Mike.png",3,1);
        teclado();
    }

    private void teclado() {
        escena.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()){
                case SPACE -> arriba_presionada=true;
                //case S -> abajo_presionada=true;
                case A -> izq_presionada=true;
                case D -> der_presionada=true;
                //case UP -> arriba_presionada=true;
                //case DOWN -> abajo_presionada=true;
                case LEFT -> izq_presionada=true;
                case RIGHT -> der_presionada=true;
            }});

        escena.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case SPACE -> arriba_presionada = false;
                //case S -> abajo_presionada = false;
                case A -> izq_presionada = false;
                case D -> der_presionada = false;
                //case UP -> arriba_presionada = false;
                //case DOWN -> abajo_presionada = false;
                case LEFT -> izq_presionada = false;
                case RIGHT -> der_presionada = false;
            }});
    }

    private void graficar(){
        fondo.graficar(graficos);
        personajePrueba.graficar(graficos);
    }
    private void logicaObjeto(){
        this.fondo.logicaObjeto();
        this.personajePrueba.logicaObjeto();
    }
    private void update(){
        this.personajePrueba.movimiento(arriba_presionada,abajo_presionada,izq_presionada,der_presionada);
    }

    //ciclo mejorado, ahora limita la cantidad de cuadros por segundo
    private void ciclo() {
        final long fps_duracion =1000000000/tope_fps;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ;
        long tiempoInicio = System.nanoTime();
        AnimationTimer tiempo = new AnimationTimer() {
            private int fps_counter;
            private long fps_timer;
            private long uf;
            @Override
            public void handle(long tiempoActual) {
                if(uf==0){
                    uf=tiempoActual;
                    fps_timer=tiempoActual;
                }if (tiempoActual - uf < fps_duracion){
                    return;
                }
                uf=tiempoActual;
                fps_counter++;
                logicaObjeto();
                graficar();
                update();


                //Contador de FPS, comentar a posterioridad, solo para comprobar rendimeitos y diversas utilidades.

                if (tiempoActual - fps_timer >= 1000000000) {
                    System.out.println("FPS: " + fps_counter);
                    fps_counter = 0;
                    fps_timer = tiempoActual;
            }}
        };
        tiempo.start();
    }
    private void inicia_ciclo(){
    }

    public static void main(String[] args) {
        launch();
    }
}