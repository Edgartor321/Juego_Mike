package com.unam.aragon.arranque;

import com.unam.aragon.modelo.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class MotorJuego {
    private PersonajePrueba mike;
    private List<ComponentesJuego> objetos;

    public MotorJuego() {
        mike = new PersonajePrueba(50, 300, "", 1);

        objetos = new ArrayList<>();
        // Ejemplo: añadir varios obstáculos con imagen y velocidad
        objetos.add(new Obstaculo(600, 320, "", 1));
        objetos.add(new Obstaculo(900, 320, "", 1));
    }

    public void actualizar() {
        mike.logicaObjeto();

        for (ComponentesJuego obj : objetos) {
            obj.logicaObjeto();

            if (Colisiones.detectarColision(mike, obj)) {
                mike.restarVida();  // quita vida a mike

                }
            }
        }


    public void graficarTodos(GraphicsContext g) {
        mike.graficar(g);

        for (ComponentesJuego obj : objetos) {
            obj.graficar(g);
        }
    }

    public PersonajePrueba getMike() {
        return mike;
    }
}

