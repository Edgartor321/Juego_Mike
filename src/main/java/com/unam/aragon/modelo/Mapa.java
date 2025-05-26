package com.unam.aragon.modelo;

import com.unam.aragon.arranque.Inicio;

import java.util.ArrayList;
import java.util.Random;

public class Mapa {
    private int desplazamiento=0;
    private ArrayList<Obstaculo> obst=new ArrayList<>();
    private Random random=new Random();
    private int ancho,alto;
    private int cuenta;

    public Mapa(int ancho, int alto,int velocidad) {
        this.ancho = ancho;
        this.alto = alto;
        desplazamiento=velocidad;
    }

    private void crono() {
        this.cuenta++;
        if (this.cuenta >= 40) {
            this.cuenta = 0;
            crearObstaculo();

        }
    }

    private void moverObstaculo(){
        for (int i = 0; i < obst.size(); i++) {
            Obstaculo obstaculo = obst.get(i);
            int nueva_pos=obstaculo.getX()-this.desplazamiento;
            obstaculo.setX(nueva_pos);
            if (obstaculo.getX()+obstaculo.getANCHO()<0){
                obst.remove(i);
                i--;
            }
        }
    }

    private void crearObstaculo() {
        int valor=random.nextInt(100);
        if (valor>50){
            Obstaculo obstaculo =new Obstaculo(Inicio.anchura_panel+Inicio.tamano_cuadro,230,"Tileset.png",1);
            obst.add(obstaculo);
        }
    }
    public ArrayList <Obstaculo> getObst(){
        return obst;
    }
    public void logica(){
        crono();
        moverObstaculo();
    }

}





