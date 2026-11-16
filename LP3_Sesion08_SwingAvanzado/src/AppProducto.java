import javax.swing.*;
import java.awt.*;

class Producto {
    private String nombre;
    private double precio;
    private int cantidadStock;
    private String categoria;

    public Producto(String nombre, double precio, int cantidadStock, String categoria) {
        this.nombre = nombre; this.precio = precio;
        this.cantidadStock = cantidadStock; this.categoria = categoria;
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}

public class AppProducto {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Producto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 330);

        Producto producto = new Producto("Laptop", 2500.0, 10, "Tecnología");

        JTextField txtNombre = new JTextField(producto.getNombre(), 15);
        JTextField txtPrecio = new JTextField(String.valueOf(producto.getPrecio()), 15);
        JTextField txtStock = new JTextField(String.valueOf(producto.getCantidadStock()), 15);
        JTextField txtCategoria = new JTextField(producto.getCategoria(), 15);
        JButton btn = new JButton("Actualizar Producto");
        JLabel lblInfo = new JLabel("Información del producto aparecerá aquí");

        btn.addActionListener(e -> {
            try {
                producto.setNombre(txtNombre.getText().trim());
                producto.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                producto.setCantidadStock(Integer.parseInt(txtStock.getText().trim()));
                producto.setCategoria(txtCategoria.getText().trim());
                lblInfo.setText("<html><b>Producto actualizado</b><br>"
                    + "Nombre: " + producto.getNombre() + "<br>"
                    + "Precio: S/ " + producto.getPrecio() + "<br>"
                    + "Stock: " + producto.getCantidadStock() + "<br>"
                    + "Categoría: " + producto.getCategoria() + "</html>");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Precio o stock inválido. Ingrese números.");
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("Nombre:"));    frame.add(txtNombre);
        frame.add(new JLabel("Precio:"));    frame.add(txtPrecio);
        frame.add(new JLabel("Stock:"));     frame.add(txtStock);
        frame.add(new JLabel("Categoría:")); frame.add(txtCategoria);
        frame.add(btn);
        frame.add(lblInfo);
        frame.setVisible(true);
    }
}
