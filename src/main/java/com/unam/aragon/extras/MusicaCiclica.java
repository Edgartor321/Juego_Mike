package com.unam.aragon.extras;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class MusicaCiclica implements Runnable {

        private BufferedInputStream buffer = null;
        private Player player = null;
        private InputStream archivo;
        private String nombreArchivo;

        public MusicaCiclica(String archivo) {
            this.nombreArchivo = archivo;
            InputStream ruta = MusicaCiclica.class.getResourceAsStream(archivo + ".mp3");

            this.archivo = ruta;

        }

    @Override
    public void run() {
        try{
            buffer = new BufferedInputStream(archivo);
            player = new Player(buffer);
        }
    }
}
