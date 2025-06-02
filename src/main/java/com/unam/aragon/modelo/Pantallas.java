package com.unam.aragon.modelo;

import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;

public class Pantallas extends ComponentesJuego{
    private Image panelmap;
    private int cuenta=0;
    private int columna_sprite;
    private final int velocidad_animacion = 15;
    private int selector_horizontal;

    public Pantallas(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta=Pantallas.class.getResourceAsStream(imagen);
        this.panelmap=new Image(ruta);
    }

    @Override
    public void logicaObjeto() {

    }

    @Override
    public void graficar(GraphicsContext g) {
        cuenta++;
        if (cuenta >= velocidad_animacion && columna_sprite<=4) {
            cuenta = 0;
            columna_sprite++;
        }else if (columna_sprite>4) {
            columna_sprite = 0;
        }
        selector_horizontal=columna_sprite*200;
        g.drawImage(panelmap,selector_horizontal,0,200,113, 0,0,Inicio.anchura_panel,Inicio.altura_panel);
    }

    public Image getPanelmap() {
        return panelmap;
    }
}
