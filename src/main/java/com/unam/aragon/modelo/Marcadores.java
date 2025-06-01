package com.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;

public class Marcadores extends ComponentesJuego {
    private int puntuacion = 0;
    private int cuenta = 0;
    private int vidas = 0;
    private ArrayList<Image> corazones = new ArrayList<>();
    private Image corazon;
    private int mejorPuntuacion = 0;
    private String ruta_absoluta = "src/main/resources/com/unam/aragon/extras/mp.txt";

    public Marcadores(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta = Marcadores.class.getResourceAsStream(imagen);
        this.corazon = new Image(ruta);

        this.vidas = 3;
        leerTxt();
        actualizarCorazones();
    }

    @Override
    public void logicaObjeto() {
        cuenta++;
        if (cuenta >= 6) {
            puntuacion++;
            cuenta = 0;
        }
        if (puntuacion > mejorPuntuacion) {
            mejorPuntuacion = puntuacion;
        }
    }

    public int getPuntuacion() {
        return puntuacion;
    }


    //revisar Ed
    //segun dibuja los corazones
    @Override
    public void graficar(GraphicsContext g) {
        int espacioEntreCorazones = 5;
        int anchoFijo = 35;
        int altoFijo = 35;

        for (int i = 0; i < vidas; i++) {
            int posX = x + i * (anchoFijo + espacioEntreCorazones);
            g.drawImage(
                    corazon,
                    0, 0, (int) corazon.getWidth(), (int) corazon.getHeight(),
                    posX, y, anchoFijo, altoFijo);
        }

        g.setFill(Color.WHITE);
        g.setFont(Font.font("Arial", 18));

        String textoPuntos = "Puntuación: " + puntuacion;

        Text tempText = new Text(textoPuntos);
        tempText.setFont(g.getFont());
        double anchoTexto = tempText.getLayoutBounds().getWidth();

        double anchoCanvas = g.getCanvas().getWidth();
        double xTexto = anchoCanvas - anchoTexto - 20;
        double yTexto = 30;

        g.fillText(textoPuntos, xTexto, yTexto);

        String textoRecord = "Mejor: " + mejorPuntuacion;
        Text tempRecord = new Text(textoRecord);
        tempRecord.setFont(g.getFont());
        double anchoTextoRecord = tempRecord.getLayoutBounds().getWidth();
        g.fillText(textoRecord, g.getCanvas().getWidth() - anchoTextoRecord - 20, 60);


        actualizarCorazones();
        if (PersonajePrueba.vidas<=0){
            guardarPuntaje();
            System.out.println("Escibiendo");
        }
    }


    //codigo para restar vidas a el jugador
    private void actualizarCorazones() {
        vidas = PersonajePrueba.vidas;
        corazones.clear();
        for (int i = 0; i < vidas; i++) {
            corazones.add(corazon);
        }
    }
    private void leerTxt(){
        try (BufferedReader lector=new BufferedReader(new FileReader(this.ruta_absoluta))){
            String mp=lector.readLine();
            mejorPuntuacion=Integer.parseInt(mp);
        }catch (IOException | NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
    private void guardarPuntaje(){
        try (BufferedWriter modificar=new BufferedWriter(new FileWriter(ruta_absoluta))){
            modificar.write(puntuacion+"");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

}




