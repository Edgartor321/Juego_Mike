package com.unam.aragon.arranque;

import com.unam.aragon.modelo.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private PersonajePrueba mike;
    private List<ComponentesJuego> objetos;

    public MotorJuego() {
        mike = new PersonajePrueba(50, 50, "", 1);
        objetos = new ArrayList<>();
        objetos.add(new Obstaculo(100, 50, "", 1));
    }

    public void actualizar() {
        mike.logicaObjeto();

        for (ComponentesJuego obj : objetos) {
            obj.logicaObjeto();
        }
    }

    public void graficarTodos(GraphicsContext g) {
        mike.graficar(g);
        for (ComponentesJuego obj : objetos) {
            obj.graficar(g);
        }
    }
}


