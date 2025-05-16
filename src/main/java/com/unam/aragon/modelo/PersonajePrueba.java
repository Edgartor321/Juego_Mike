package com.unam.aragon.modelo;
import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.InputStream;

public class PersonajePrueba extends ComponentesJuego{
    private Image sprite_Map_jugador;
    private int personaje_caminando;
    private final int velocidad_animacion = 10;
    private int columna_sprite;
    private int cuenta ;
    private int gravedad =1;
    private boolean toca_suelo =false;
    private int fuerza_salto =12;
    private int velocidad_y=0;
    private int selector_horizontal=0;


    public PersonajePrueba(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta=Fondo.class.getResourceAsStream(imagen);
        this.sprite_Map_jugador =new Image(ruta);
    }
    public void movimiento(boolean arriba, boolean abajo){
        if (arriba && toca_suelo) {
            velocidad_y = -fuerza_salto;
            toca_suelo = false;
            selector_horizontal=1;

        }if (abajo && toca_suelo) {
            selector_horizontal=2;
        }else{
            selector_horizontal=0;
        }
        //System.out.println(this.getX()+this.getY());
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
                selector_horizontal=0;
            }

        }
    }

    private void recorteImagenes() {
        cuenta++;
        if (cuenta >= velocidad_animacion && columna_sprite<=4) {
            cuenta = 0;
            columna_sprite++;
        } else if (columna_sprite>4) {
            columna_sprite=0;
        }
        personaje_caminando = columna_sprite*32;
    }
    @Override
    public void graficar(GraphicsContext g) {
        g.drawImage(sprite_Map_jugador, personaje_caminando, 32*selector_horizontal, 32, 32, x, y, 64 * Inicio.escala , 64 * Inicio.escala );
        recorteImagenes();
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 32, 32);
    }

    public void restarVida() {
    }
}




