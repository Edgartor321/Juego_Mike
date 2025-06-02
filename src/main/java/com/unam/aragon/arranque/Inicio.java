package com.unam.aragon.arranque;

import com.unam.aragon.extras.EfectosMusica;
import com.unam.aragon.modelo.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Inicio extends Application {
    private GraphicsContext graficos;
    private Group root;
    private Scene escena;
    private Canvas hoja;
    private Fondo fondo;
    private Mapa mapa;
    private Marcadores marcador;
    private EfectosMusica efectosMusica;
    private Pantallas pantallas;


    //Establecer configuraciones de ventana.
    public static final int tamano_cuadro_default = 32;
    public static final int cuadros_en_ancho = 20;
    public static final int cuadros_en_largo = 10;
    public static final float escala = 1f;
    public static final int tamano_cuadro = (int) (tamano_cuadro_default * escala);
    public static final int anchura_panel = tamano_cuadro * cuadros_en_ancho;
    public static final int altura_panel = tamano_cuadro * cuadros_en_largo;
    //Teclado variables
    private boolean arriba_presionada=false;
    private boolean abajo_presionada=false;
    private boolean pausa_presionada=false;
    private PersonajePrueba personajePrueba;
    private static final int tope_fps=120;
    private ArrayList<Obstaculo> objeto;
    private float velocidad_abs=0;


    ;

    //Constantes para posicionar elestado de juego
    private static final int MENU =0;
    private static final int JUGANDO= 1;
    private static final int PAUSA=2;
    private static final int GAME_OVER= 3;
    private int status =MENU;

    //Sitio de arranque
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {
        arrancador();
        graficar();
        stage.setTitle("Mike VS Microsoft");
        stage.setScene(escena);
        stage.show();
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        ciclo();
        playMusica(0);
    }

    private void arrancador() throws URISyntaxException{
        root = new Group();
        escena = new Scene(root, anchura_panel, altura_panel);
        hoja = new Canvas(anchura_panel, altura_panel);
        root.getChildren().add(hoja);
        graficos = hoja.getGraphicsContext2D();
        fondo = new Fondo(0, 0, "fondo0.png", 1);
        personajePrueba =new PersonajePrueba(150,100,"Mike.png",1,3);
        teclado();
        mapa=new Mapa(anchura_panel,tamano_cuadro,4);
        objeto=mapa.getObst();
        marcador=new Marcadores(15,15,"corazon.png",1);
        efectosMusica = new EfectosMusica();
        pantallas=new Pantallas(0,0,"pantallas.png",1);
    }


    private void teclado() {
        escena.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()){
                case SPACE -> arriba_presionada=true;
                case W -> arriba_presionada=true;
                case S -> abajo_presionada=true;
                case UP -> arriba_presionada=true;
                case DOWN -> abajo_presionada=true;
                case CONTROL -> abajo_presionada=true;
                case ENTER -> pausa_presionada=true;
            }});

        escena.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case SPACE -> arriba_presionada = false;
                case W -> arriba_presionada=false;
                case UP -> arriba_presionada = false;
                case DOWN -> abajo_presionada = false;
                case S -> abajo_presionada = false;
                case CONTROL -> abajo_presionada=false;
                case ENTER -> pausa_presionada=false;
            }});
    }

    private void graficar(){
        if(status!=MENU){
            fondo.graficar(graficos);
            personajePrueba.graficar(graficos);
            for (Obstaculo obstaculo:objeto){
                obstaculo.graficar(graficos);
            }
            marcador.graficar(graficos);
            if (personajePrueba.isMuerto()){
                status=GAME_OVER;
                stopMusica();
            }
        }
        switch (status){
            case MENU:
                dibujarMenu();
                break;
            case PAUSA:
                dibujarPausa();
                break;
            case GAME_OVER:
                dibujarFinal();
        }
    }
    private void logicaObjeto(){
        if (status!=JUGANDO){
            return;
        }
        this.fondo.logicaObjeto();
        this.personajePrueba.logicaObjeto();
        marcador.logicaObjeto();
        personajePrueba.verificarColisiones(objeto,marcador);
        for (Obstaculo movil:objeto){
            movil.logicaObjeto();
        }
        mapa.logica();
    }
    private void actualizar(){
        if (status==JUGANDO){
            this.personajePrueba.movimiento(arriba_presionada,abajo_presionada);
        }
        if (status==GAME_OVER&&arriba_presionada){
            reiniciar();
        }
        if (status==MENU&&arriba_presionada){
            status=JUGANDO;
        }
        if (status==JUGANDO&&pausa_presionada){
            status=PAUSA;
            stopMusica();
        }
        if (status==PAUSA&&arriba_presionada){
            status=JUGANDO;
            playMusica(0);
        }
    }
    private void reiniciar(){
        personajePrueba.setX(150);
        personajePrueba.setY(100);
        PersonajePrueba.vidas=3;
        objeto.clear();
        mapa.reiniciar();
        objeto=mapa.getObst();
        personajePrueba.setMuerto(false);
        personajePrueba.setToca_suelo(false);
        marcador.setPuntuacion(0);
        marcador.setGuardado(false);

        status=JUGANDO;
        playMusica(0);
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
                if (status==JUGANDO){
                    logicaObjeto();
                }
                graficar();
                actualizar();


                //Contador de FPS, comentar a posterioridad, solo para comprobar rendimeitos y diversas utilidades.

//                if (tiempoActual - fps_timer >= 1000000000) {
//                    System.out.println("FPS: " + fps_counter);
//                    fps_counter = 0;
//                    fps_timer = tiempoActual;
//                    //fps_animacion=fps_counter;

//                }
            }
        };
        tiempo.start();
    }
    public void playMusica(int i){
        efectosMusica.archivo(i);
        efectosMusica.playMusica();
        efectosMusica.bucle();
    }
    public void stopMusica(){
        efectosMusica.stopMusica();
    }
    private void dibujarMenu() {
        graficos.save();
        fondo.graficar(graficos);
        graficos.setFill(Color.WHITE);
        graficos.setFont(Font.font("Arial", 20));
        //graficos.drawImage();
        //Colocar la imagen del Juego
        pantallas.graficar(graficos);
        graficos.fillText("Presiona Espacio para jugar",190,(altura_panel/2)+125);
        graficos.setFont(Font.font("Arial",13));
        graficos.fillText("Hecho con ♥\uFE0F por:\n" +
                "Edith\nAlma\nEdgar\nDerek ",10,250);
        graficos.setFont(Font.font("DepartureMono Nerd Font",25));
        graficos.fillText("Mike vs las BigTechs",anchura_panel/2-150,(altura_panel/2)-100);
        graficos.restore();
    }
    private void dibujarPausa() {
        graficos.save();
        graficos.setFill(Color.WHITE);
        graficos.setFont(Font.font("Arial",18));
        graficos.fillText("Pausa",anchura_panel/2-40,altura_panel/2);
        graficos.fillText("Presiona ESPACIO para reanudar",anchura_panel/2-135,altura_panel/2+20);
        graficos.restore();
    }
    private void dibujarFinal() {
        graficos.save();
        graficos.setFill(Color.RED);
        graficos.setFont(Font.font("Arial",18));
        graficos.fillText("GAME OVER",anchura_panel/2-40,altura_panel/2-10);
//        graficos.drawImage(pantallas.getPanelmap(),1000,0,200,113,anchura_panel/2-100,50,200*2,113*2);
        graficos.setFill(Color.WHITE);
        graficos.fillText("Presiona ESPACIO para reintentar",anchura_panel/2-135,altura_panel/2+100);
        graficos.restore();
    }
}