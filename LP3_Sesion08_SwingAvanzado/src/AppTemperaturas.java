import javax.swing.*;
import java.awt.*;

class PanelGrafico extends JPanel {
    private double[] temps = new double[7];
    private final String[] dias = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};

    public void setTemperaturas(double[] t) { this.temps = t; repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int mI = 50, mD = 20, mS = 20, mInf = 40;
        int w = getWidth() - mI - mD, h = getHeight() - mS - mInf;

        double min = temps[0], max = temps[0];
        for (double t : temps) { min = Math.min(min, t); max = Math.max(max, t); }
        if (max == min) { max += 1; min -= 1; }

        // Ejes
        g2.setColor(Color.BLACK);
        g2.drawLine(mI, mS, mI, mS + h);
        g2.drawLine(mI, mS + h, mI + w, mS + h);
        g2.drawString(String.format("%.1f", max), 8, mS + 5);
        g2.drawString(String.format("%.1f", min), 8, mS + h);
        g2.drawString("°C", 8, mS - 5 + 10);

        // Puntos y línea
        int[] xs = new int[7], ys = new int[7];
        for (int i = 0; i < 7; i++) {
            xs[i] = mI + 20 + i * (w - 40) / 6;
            ys[i] = (int) (mS + h - (temps[i] - min) / (max - min) * h);
        }
        g2.setColor(new Color(30, 90, 200));
        g2.setStroke(new BasicStroke(2f));
        for (int i = 0; i < 6; i++) g2.drawLine(xs[i], ys[i], xs[i + 1], ys[i + 1]);

        for (int i = 0; i < 7; i++) {
            g2.setColor(Color.RED);
            g2.fillOval(xs[i] - 5, ys[i] - 5, 10, 10);
            g2.setColor(Color.BLACK);
            g2.drawString(String.valueOf(temps[i]), xs[i] - 10, ys[i] - 10);
            g2.drawString(dias[i], xs[i] - 10, mS + h + 20);
        }
    }
}

public class AppTemperaturas {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperaturas de la semana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 480);
        frame.setLayout(new BorderLayout());

        String[] dias = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
        JTextField[] campos = new JTextField[7];
        JPanel top = new JPanel();
        for (int i = 0; i < 7; i++) {
            campos[i] = new JTextField("0", 3);
            top.add(new JLabel(dias[i] + ":"));
            top.add(campos[i]);
        }
        JButton btn = new JButton("Mostrar Gráfico");
        top.add(btn);

        PanelGrafico panel = new PanelGrafico();
        btn.addActionListener(e -> {
            try {
                double[] t = new double[7];
                for (int i = 0; i < 7; i++) t[i] = Double.parseDouble(campos[i].getText().trim());
                panel.setTemperaturas(t);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Ingrese temperaturas numéricas válidas.");
            }
        });

        frame.add(top, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
