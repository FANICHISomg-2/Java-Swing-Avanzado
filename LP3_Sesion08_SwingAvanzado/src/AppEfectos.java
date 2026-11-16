import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppEfectos {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Efectos de Sonido");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 120);
        frame.setLayout(new FlowLayout());

        JButton bAplausos = new JButton("Aplausos");
        JButton bCampana = new JButton("Campana");
        JButton bExplosion = new JButton("Explosión");

        bAplausos.addActionListener(e -> reproducir("aplausos.wav"));
        bCampana.addActionListener(e -> reproducir("campana.wav"));
        bExplosion.addActionListener(e -> reproducir("explosion.wav"));

        frame.add(bAplausos); frame.add(bCampana); frame.add(bExplosion);
        frame.setVisible(true);
    }

    public static void reproducir(String ruta) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo reproducir: " + ruta
                + "\n(" + ex.getMessage() + ")");
        }
    }
}
