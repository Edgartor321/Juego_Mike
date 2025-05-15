package com.unam.aragon.modelo;

import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.InputStream;

public class PersonajePrueba extends ComponentesJuego {
    private Image sprite_Map_jugador;
    private int personaje_caminando;
    private final int velocidad_animacion = 15;
    private int columna_sprite;
    private int cuenta;
    private int vida;

    public PersonajePrueba(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta = Fondo.class.getResourceAsStream(imagen);
        this.sprite_Map_jugador = new Image(ruta);
        this.vida = 3;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void restarVida() {
        if (vida > 0) {
            vida--;
            System.out.println("Vidas: " + vida);
        }
    }

    public void movimiento(boolean arriba, boolean abajo, boolean izq, boolean der) {
        if (arriba && this.getY() > 0) {
            this.setY(this.getY() - velocidad);
        }
        if (abajo && this.getY() < Inicio.altura_panel) {
            this.setY(this.getY() + velocidad);
        }
    }

    @Override
    public void logicaObjeto() {
        recorteImagenes();
        //logica pendiente 
    }

    private void recorteImagenes() {
        cuenta++;
        if (cuenta >= velocidad_animacion) {
            cuenta = 0;
            columna_sprite++;
            if (columna_sprite > 2) {
                columna_sprite = 0;
            }
        }
        personaje_caminando = columna_sprite * 32;
    }

    @Override
    public void graficar(GraphicsContext g) {
        g.drawImage(sprite_Map_jugador, personaje_caminando, 0, 32, 32, x, y, 64 * Inicio.escala * 1.2, 64 * Inicio.escala * 1.2);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 32, 32);
    }
}




