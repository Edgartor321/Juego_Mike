package com.unam.aragon.modelo;

public class Colisiones {

    public static boolean detectarColision(ComponentesJuego a, ComponentesJuego b) {
        if (a == null || b == null) return false;

        if (a instanceof PersonajePrueba && b instanceof Obstaculo) {
            return ((PersonajePrueba) a).getBounds().intersects(((Obstaculo) b).getBounds());
        } else if (a instanceof Obstaculo && b instanceof PersonajePrueba) {
            return ((Obstaculo) a).getBounds().intersects(((PersonajePrueba) b).getBounds());
        }

        return false;
    }
}
