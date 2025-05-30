package com.unam.aragon.extras;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class EfectosMusica {
    Clip clip;
    URL musicaURL [] = new URL[30];

    public EfectosMusica() {
        musicaURL[0] = getClass().getResource("/com/unam/aragon/extras/musica.wav");
        musicaURL[1] = getClass().getResource("/com/unam/aragon/extras/choque.wav");
        musicaURL[2] = getClass().getResource("/com/unam/aragon/extras/disparo.wav");
        musicaURL[3] = getClass().getResource("/com/unam/aragon/extras/aplastado.wav");
    }
    public void archivo(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicaURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playMusica(){
        clip.start();
    }
    public void bucle(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopMusica(){
        clip.stop();
    }

}