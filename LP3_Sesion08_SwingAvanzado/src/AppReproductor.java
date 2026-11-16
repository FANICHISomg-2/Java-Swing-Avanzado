import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppReproductor {
    private static Clip clip;
    private static long posicion = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reproductor de Música");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 120);
        frame.setLayout(new FlowLayout());

        JButton bPlay = new JButton("Reproducir");
        JButton bPausa = new JButton("Pausar");
        JButton bReanudar = new JButton("Reanudar");

        bPlay.addActionListener(e -> reproducir("musica.wav"));
        bPausa.addActionListener(e -> pausar());
        bReanudar.addActionListener(e -> reanudar());

        frame.add(bPlay); frame.add(bPausa); frame.add(bReanudar);
        frame.setVisible(true);
    }

    static void reproducir(String ruta) {
        try {
            if (clip != null) { clip.stop(); clip.close(); }
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(ruta));
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.setMicrosecondPosition(0);   // desde el inicio
            clip.start();
            posicion = 0;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el audio: " + ex.getMessage());
        }
    }

    static void pausar() {
        if (clip != null && clip.isRunning()) {
            posicion = clip.getMicrosecondPosition();  // guarda dónde se detuvo
            clip.stop();
        }
    }

    static void reanudar() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(posicion);     // continúa desde ahí
            clip.start();
        }
    }
}
