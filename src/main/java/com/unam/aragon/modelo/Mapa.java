package com.unam.aragon.modelo;

import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.util.ArrayList;

public class Mapa extends ComponentesJuego{
    private int desplazamiento_fondo=0;
    private ArrayList<Rectangle> obstaculos = new ArrayList<>();
    private int ancho,alto;
    private double random;

    public Mapa(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        this.ancho=Inicio.anchura_panel;
        this.alto=Inicio.altura_panel;
        System.out.println(random);
    }

    @Override
    public void logicaObjeto() {

    }

    @Override
    public void graficar(GraphicsContext g) {

    }
}
