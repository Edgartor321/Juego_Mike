package com.unam.aragon.modelo;

import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

public class Fondo extends ComponentesJuego{
    private int bgx = Inicio.anchura_panel;
    private Image imagen;
    private Image imagenDos;

    public Fondo(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta=Fondo.class.getResourceAsStream(imagen);
        this.imagen =new Image(ruta);
        ruta=Fondo.class.getResourceAsStream("fondo0.jpg");
        this.imagenDos =new Image(ruta);
    }
    public void graficar(GraphicsContext g){
        g.drawImage(imagen,x,y);
        g.drawImage(imagenDos,bgx, y);
    }
    public void logicaObjeto(){
        x-=velocidad;
        bgx -= velocidad;
        if (x == (Inicio.anchura_panel*-1)) {
            x = (Inicio.anchura_panel);
        }
        if (bgx == -Inicio.anchura_panel*-1) {
            bgx = (Inicio.anchura_panel);
        }
    }

}
