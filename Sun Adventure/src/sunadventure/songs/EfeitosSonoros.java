package sunadventure.songs;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EfeitosSonoros extends JPanel {

    private ExecutorService threadPool;

    public EfeitosSonoros() {
        setDoubleBuffered(true);
        threadPool = Executors.newFixedThreadPool(5);  // Cria um pool de threads com 5 threads disponíveis
    }

    // Reproduz o efeito sonoro da fase
    public void tocarFase() {
        reproduzirSom("TrilhaSonora.wav");
    }

    // Reproduz o efeito sonoro do chefe (boss)
    public void tocarBoss() {
        reproduzirSom("SomBoss.wav");
    }

    // Reproduz o efeito sonoro do tiro
    public void tocarTiro() {
        reproduzirSom("SomTiro.wav");
    }

    // Reproduz o efeito sonoro da explosão
    public void tocarSomExplosao() {
        reproduzirSom("SomExplosao.wav");
    }

    // Reproduz o efeito sonoro do turbo
    public void tocarSomTurbo() {
        reproduzirSom("SomTurbo.wav");
    }

    // Reproduz um efeito sonoro usando uma thread do pool de threads
    private void reproduzirSom(String fileName) {
        threadPool.execute(() -> {
            try {
                URL soundFile = getClass().getResource(fileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
                DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(sound);
                clip.start();
            } catch (Exception e) {
                JOptionPane.showInputDialog(this, e);  // Exibe uma caixa de diálogo em caso de erro
            }
        });
    }
}

