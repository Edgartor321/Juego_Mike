package com.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.ArrayList;

public class Marcadores extends ComponentesJuego {
    private int puntuacion = 0;
    private int cuenta =0;
    private int vidas = 0;
    private ArrayList<Image> corazones = new ArrayList<>();
    private Image corazon;
    private int mejorPuntuacion = 0;
    private final String ARCHIVO_PUNTUACION = "mejor_puntuacion.txt";

    public Marcadores(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta = Marcadores.class.getResourceAsStream(imagen);
        this.corazon = new Image(ruta);

         this.vidas = 3;
//         cargaMejorPuntuacion();
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
//            guardarMejorPuntuacion();
        }
        System.out.println("puntuacion actual: " +puntuacion);
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
                    posX, y, anchoFijo, altoFijo
            );
        }

        g.setFill(javafx.scene.paint.Color.WHITE);
        g.setFont(javafx.scene.text.Font.font("Arial", 24));

        String textoPuntos = "Puntuación: " + puntuacion;

        javafx.scene.text.Text tempText = new javafx.scene.text.Text(textoPuntos);
        tempText.setFont(g.getFont());
        double anchoTexto = tempText.getLayoutBounds().getWidth();

        double anchoCanvas = g.getCanvas().getWidth();
        double xTexto = anchoCanvas - anchoTexto - 20;
        double yTexto = 30;

        g.fillText(textoPuntos, xTexto, yTexto);

        String textoRecord = "Mejor: " + mejorPuntuacion;
        javafx.scene.text.Text tempRecord = new javafx.scene.text.Text(textoRecord);
        tempRecord.setFont(g.getFont());
        double anchoTextoRecord = tempRecord.getLayoutBounds().getWidth();
        g.fillText(textoRecord, g.getCanvas().getWidth() - anchoTextoRecord - 20, 60);


        actualizarCorazones();
    }


    //codigo para restar vidas segun el jugador
    private void actualizarCorazones() {
        vidas=PersonajePrueba.vidas;
        corazones.clear();
        for (int i = 0; i < vidas; i++) {
            corazones.add(corazon);
        }
    }

//    private void cargarMejorPuntuacion() {
//        try {
//            java.io.File archivo = new java.io.File(ARCHIVO_PUNTUACION);
//            if (archivo.exists()) {
//                java.util.Scanner lector = new java.util.Scanner(archivo);
//                if (lector.hasNextInt()) {
//                    mejorPuntuacion = lector.nextInt();
//                }
//                lector.close();
//            }
//        }catch (Exception e) {
//            System.out.println("Error al cargar mejor puntuación: " + e.getMessage());
//            e.printStackTrace();
//    }
//
//    private void guardarMejorPuntuacion() {
//        try {
//            java.io.PrintWriter escritor = new java.io.PrintWriter(ARCHIVO_PUNTUACION);
//            escritor.println(mejorPuntuacion);
//            escritor.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        }

    //pt. 2 del metodo de arrriba
//    public void perderVida() {
//        vidas--;
//            actualizarCorazones();
//        }

