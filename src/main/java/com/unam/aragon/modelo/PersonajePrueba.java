package com.unam.aragon.modelo;
import com.unam.aragon.arranque.HelloApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class PersonajePrueba extends ComponentesJuego{
    private Image sprite_Map_jugador;
    private int personaje_caminando;
    private final int velocidad_animacion=30;
    private int columna_sprite;
    private int cuenta ;


    public PersonajePrueba(int x, int y, String imagen, int velocidad, int gravedad) {
        super(x,y,imagen,velocidad,gravedad);
        InputStream ruta=Fondo.class.getResourceAsStream(imagen);
        this.sprite_Map_jugador =new Image(ruta);
    }
    public void movimiento(boolean arriba, boolean abajo, boolean izq, boolean der){
        if (arriba&&this.getY()>0){
            this.setY((int) (this.getY()-velocidad));
//            System.out.println("arriba");

        } if (abajo&&this.getY()<HelloApplication.altura_panel){
                this.setY((int)(this.getY()+velocidad));
//            System.out.println("abajo");
        }
                if (izq&&this.getX()>0){
                    this.setX((int)(this.getX()-velocidad));
//                    System.out.println("izquierda");
                }
                    if (der&&this.getX()<HelloApplication.anchura_panel){
                        this.setX((int)(this.getX()+velocidad));
                        //System.out.println("derecha");
                    }
        //System.out.println(this.getX()+this.getY());
    }

    @Override
    public void logicaObjeto() {

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
        g.drawImage(sprite_Map_jugador,personaje_caminando,0,32,32,x,y,64*HelloApplication.escala,64*HelloApplication.escala);

    }

}


