/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia;

/**
 *
 * @author honey
 */
public class Venta {
  // Proceso de pago?
  private String nombreProducto;
  private double costo;
  int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Venta(String nombreProducto, double costo, int cantidad) {
        this.nombreProducto = nombreProducto;
        this.costo = costo;
        this.cantidad = cantidad;
    }

  public Venta() {
    nombreProducto = "";
    costo = 0;
  }



  public String getNombreProducto() {
    return nombreProducto;
  }

  public void setNombreProducto(String NombreProducto) {
    this.nombreProducto = NombreProducto;
  }

  public double getCosto() {
    return costo;
  }

  public void setCosto(double costo) {
    this.costo = costo;
  }

  public void mostrarDatos() {
    System.out.println(
        "\nDatos del producto\nNombre del Producto: " + this.getNombreProducto() + "\nCosto; " + this.getCosto());
  }
}
