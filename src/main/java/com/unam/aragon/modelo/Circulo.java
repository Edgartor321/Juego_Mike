package com.unam.aragon.modelo;
import com.unam.aragon.arranque.HelloApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Circulo extends ComponentesJuego{

    public Circulo(int x, int y, String imagen, int velocidad, int gravedad) {
        super(x,y,imagen,velocidad,gravedad);
    }
    public void movement(boolean arriba, boolean abajo, boolean izq, boolean der){
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
    }

    @Override
    public void logicaObjeto() {

    }

    @Override
    public void graficar(GraphicsContext g) {
        g.fillRect(x,y,40,40);
    }

}


