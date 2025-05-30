package com.unam.aragon.modelo;
import com.unam.aragon.arranque.Inicio;
import com.unam.aragon.extras.EfectosMusica;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

public class PersonajePrueba extends ComponentesJuego{
    private Image sprite_Map_jugador;
    private int personaje_caminando;
    private final int velocidad_animacion = 10;
    private int columna_sprite;
    private int cuenta ;
    private int gravedad =1;
    private boolean toca_suelo =false;
    private int fuerza_salto =18;
    private int velocidad_y=0;
    private int selector_horizontal=0;
    public static int vidas=0;
    private long ultimoTiempoColision = 0;
    private EfectosMusica efectosMusica = new EfectosMusica();
    private boolean muerto=false;
    private int alto=0,ancho=0,reduc=0;

    public PersonajePrueba(int x, int y, String imagen, int velocidad, int vidas) {
        super(x, y, imagen, velocidad);
        InputStream ruta=Fondo.class.getResourceAsStream(imagen);
        this.sprite_Map_jugador =new Image(ruta);
        this.vidas=vidas;
        this.alto=56;
        this.ancho=48;
        this.reduc=0;
    }
    public void movimiento(boolean arriba, boolean abajo){
        if(!muerto){
            if (arriba && toca_suelo) {
                velocidad_y = -fuerza_salto;
                toca_suelo = false;
                selector_horizontal=1;
                efectosMusica.archivo(1);
                efectosMusica.playMusica();

            }if (abajo && toca_suelo) {
                selector_horizontal=3;
                alto=32;
                reduc=32;
            }else{
                selector_horizontal=0;
                alto=55;
                reduc = 0;
            }
        }

        //System.out.println(this.getX()+this.getY());
    }

    @Override
    public void logicaObjeto() {
        //recorteImagenes();
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
        if (cuenta >= velocidad_animacion && columna_sprite<=4&&vidas>0) {
            cuenta = 0;
            columna_sprite++;
        } else if (columna_sprite>4) {
            columna_sprite=0;
    }else if (vidas<=0&&cuenta>=velocidad_animacion&&columna_sprite<=4){
            columna_sprite++;
            if (columna_sprite>4){
                columna_sprite=4;
            }
        } else if ( alto==32 && toca_suelo &&vidas>0&&columna_sprite<=4) {
            columna_sprite++;
            if ( columna_sprite>4 ){
                columna_sprite=4;
            }
        }
        personaje_caminando = columna_sprite*32;
    }
    @Override
    public void graficar(GraphicsContext g) {
        g.drawImage(sprite_Map_jugador, personaje_caminando, 32*selector_horizontal, 32, 32, x, y, 64 * Inicio.escala , 64 * Inicio.escala );
        g.strokeRect(getX()+5,y+reduc,46,alto);
        g.setStroke(Color.RED);
        if (vidas<=0){
            selector_horizontal=2;

        }
        recorteImagenes();
    }

    public Rectangle getBounds() {
        return new Rectangle(getX()+5, getY()+reduc, 46, alto);
    }

    public void restarVida() {
        System.out.println("Quitando vida");
        vidas--;
        if(vidas<=0){
            terminarJuego();
        }
    }

    private void terminarJuego() {
        //System.out.println("Juego terminado");
        this.muerto=true;
        efectosMusica.archivo(3);
        efectosMusica.playMusica();
    }

    public void verificarColisiones(ArrayList<Obstaculo> objetos, Marcadores marcador) {
            for (ComponentesJuego obj:objetos){
                if (Colisiones.detectarColision(this,obj)&&obj.tangible==true){
                    restarVida();
                    obj.tangible=false;
                    break;
            }
        }
    }

    public int getVidas() {
        return vidas;
    }

    public boolean isMuerto() {
        return muerto;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    public void setToca_suelo(boolean toca_suelo) {
        this.toca_suelo = toca_suelo;
    }

    public Image getSprite_Map_jugador() {
        return sprite_Map_jugador;
    }
}





