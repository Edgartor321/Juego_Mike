package com.unam.aragon.modelo;

import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

public class Fondo extends ComponentesJuego{
    private int bgx = Inicio.anchura_panel;
    private Image imagen;
    private Image imagenDos;
    private int puntuacion=0;


    public Fondo(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta=Fondo.class.getResourceAsStream(imagen);
        this.imagen =new Image(ruta);
//        ruta=Fondo.class.getResourceAsStream("Comprimida.jpg");
//        this.imagenDos =new Image(ruta);
    }

    public void graficar(GraphicsContext g){
//        img- la imagen a dibujar o nula.
//        sx- la posición de coordenadas X del rectángulo fuente.
//        sy- la posición de coordenadas Y del rectángulo fuente.
//        sw- el ancho del rectángulo de la fuente.
//        sh- la altura del rectángulo de la fuente.
//        dx- la posición de coordenadas X del rectángulo de destino.
//        dy- la posición de coordenadas Y del rectángulo de destino.
//        dw- el ancho del rectángulo de destino.
//        dh- la altura del rectángulo de destino.
        g.drawImage(imagen,0,0,800,450,x,y,Inicio.anchura_panel,Inicio.altura_panel);
        g.drawImage(imagen,0,0,800,450,bgx,y,Inicio.anchura_panel,Inicio.altura_panel);
        //g.fillText("Puntuación: ",30,30,100);
        //g.setFill(Color.INDIANRED);
        //g.scale(3,3);
    }
    public void logicaObjeto(){
        x-=velocidad;
        bgx -= velocidad;
        if (x <= (Inicio.anchura_panel*-1)) {
            x = (Inicio.anchura_panel);
        }
        if (bgx <= Inicio.anchura_panel*-1) {
            bgx = (Inicio.anchura_panel);
        }
    }

    @Override
    public void actualizarVelocidad() {
        this.velocidad=4;
    }
}
