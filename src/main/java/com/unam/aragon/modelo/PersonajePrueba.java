package com.unam.aragon.modelo;
import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

public class PersonajePrueba extends ComponentesJuego{
    private Image sprite_Map_jugador;
    private int personaje_caminando;
    private final int velocidad_animacion=15;
    private int columna_sprite;
    private int cuenta ;
    private int gravedad =1;
    private boolean toca_suelo =false;
    private int fuerza_salto =12;
    private int velocidad_y=0;


    public PersonajePrueba(int x, int y, String imagen, int velocidad) {
        super(x,y,imagen,velocidad);
        InputStream ruta=Fondo.class.getResourceAsStream(imagen);
        this.sprite_Map_jugador =new Image(ruta);
    }
    public void movimiento(boolean arriba, boolean abajo){
        //camine de izquierda a derecha
//        if (izq) {
//            this.setX(this.getX() - velocidad);
//        }
//        if (der) {
//            this.setX(this.getX() + velocidad);
//        }

        // Salto
        if (arriba && toca_suelo) {
            velocidad_y = -fuerza_salto;
            toca_suelo = false;
        }
    }

    @Override
    public void logicaObjeto() {
        if(!toca_suelo){
           velocidad_y+= gravedad;
            y += velocidad_y;
            int altura_personaje = 100;
            int suelo_y =Inicio.altura_panel - altura_personaje;

            if (y>= suelo_y){
                y=suelo_y;
                velocidad_y=0;
                toca_suelo =true;
            }

        }
    }

    private void recorteImagenes() {
        cuenta++;
        if (cuenta >= velocidad_animacion && columna_sprite<=2) {
            cuenta = 0;
            columna_sprite++;
        } else if (columna_sprite>2) {
            columna_sprite=0;
        }
        personaje_caminando = columna_sprite*32;
    }
    @Override
    public void graficar(GraphicsContext g) {
        //Uso de DrawImage
//        img - the image to be drawn or null.
//        sx - the source rectangle's X coordinate position.
//        sy - the source rectangle's Y coordinate position.
//        sw - the source rectangle's width.
//        sh - the source rectangle's height.
//        dx - the destination rectangle's X coordinate position.
//        dy - the destination rectangle's Y coordinate position.
//        dw - the destination rectangle's width.
//        dh - the destination rectangle's height.
        recorteImagenes();
        //g.scale(-1,1);
        g.drawImage(sprite_Map_jugador,personaje_caminando,0,32,32,x,y,64* Inicio.escala,64* Inicio.escala);

    }

}


