package com.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

public class Fondo extends ComponentesJuego{
    private int bgy=-600;
    private Image imagen;
    private Image imagenDos;

    public Fondo(int x, int y, String imagen, int velocidad, float gravedad) {
        super(x, y, imagen, velocidad, gravedad);
        InputStream ruta=Fondo.class.getResourceAsStream(imagen);
        this.imagen =new Image(ruta);
        ruta=Fondo.class.getResourceAsStream("fondo2.jpg");
        this.imagenDos =new Image(ruta);
    }
    public void graficar(GraphicsContext g){
        g.drawImage(imagen,x,y);
        g.drawImage(imagenDos,x,bgy);
    }
    public void logicaObjeto(){
        y+=velocidad;
        bgy += velocidad;
        if (y == 600) {
            y = -600;
        }
        if (bgy == 600) {
            bgy = -600;
        }
    }
}
