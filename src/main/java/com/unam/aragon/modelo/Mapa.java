package com.unam.aragon.modelo;

import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;

public class Mapa extends ComponentesJuego{
    private int desplazamiento_fondo=1;
    private ArrayList<Rectangle> obstaculos = new ArrayList<>();
    private int ancho,alto;
    private double random;

    public Mapa(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        this.ancho=Inicio.anchura_panel;
        this.alto=Inicio.altura_panel;
        this.desplazamiento_fondo=velocidad;

    }

    @Override
    public void logicaObjeto() {
        generarObstaculos();

    }

    @Override
    public void graficar(GraphicsContext g) {


    }
    public double generadorNumero(){
        random=Math.random();
        //System.out.println(random);
        return random;
    }
    public void generarObstaculos(){
        double aleatorio= generadorNumero();
        if (aleatorio>0&&aleatorio<=0.60){ //uno
            Obstaculo uno= new Obstaculo(Inicio.anchura_panel-50,68,"Tileset.png",1);
        } else if (aleatorio>0.60&&aleatorio<=0.90) { //dos

        }else{//tres


        }
    }
}
