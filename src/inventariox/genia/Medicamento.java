/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia;



/**
 *
 * @author honey
 */
public class Medicamento {
  private int unidVendidas, unidDispo, lote, vigencia;
  private double costo, costoVenta;
  // Codigo de medicamento:
  // Comienza por 2 letras seguida de 8 numeros
  private String codigo;
  private String nombre;
  private String fechaVencimiento;

  public Medicamento() {
    this.codigo = "";
    this.unidVendidas = 0;
    this.unidDispo = 0;
    this.lote = 0;
    this.vigencia = 0;
    this.costo = 0;
    this.costoVenta = 0;
    this.nombre = "N/A";
    this.fechaVencimiento = "00/0000";
  }

  public Medicamento(String codigo, int unidVendidas, int unidDispo,
      int lote, int vigencia, double costoCompra,
      String nombre, String fechaVencimiento) {
    this.codigo = codigo;
    this.unidVendidas = unidVendidas;
    this.unidDispo = unidDispo;
    this.lote = lote;
    this.vigencia = vigencia;
    this.costo = costoCompra;
    this.precioAPagar(20);
    this.nombre = nombre;
    this.fechaVencimiento = fechaVencimiento;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public int getUnidVendidas() {
    return unidVendidas;
  }

  public void setUnidVendidas(int unidVendidas) {
    this.unidVendidas = unidVendidas;
  }

  public int getUnidDispo() {
    return unidDispo;
  }

  public void setUnidDispo(int unidDispo) {
    this.unidDispo = unidDispo;
  }

  public int getLote() {
    return lote;
  }

  public void setLote(int lote) {
    this.lote = lote;
  }

  public int getVigencia() {
    return vigencia;
  }

  public void setVigencia(int vigencia) {
    this.vigencia = vigencia;
  }

  public double getCosto() {
    return costo;
  }

  public void setCosto(double costoCompra) {
    this.costo = costoCompra;
  }

  public double getCostoVenta() {
    return costoVenta;
  }

  public void setCostoVenta(double costoVenta) {
    this.costoVenta = costoVenta;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(String fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }




  public void precioAPagar(int porcentaje) {
    this.costoVenta = getCosto() + (getCosto() * porcentaje / 100);
  }

  public void determinarVencido() {
    if (Utility.getAnioVencimiento(fechaVencimiento) < Utility.getAnioActual())
      return;
    if (Utility.getMesVencimiento(fechaVencimiento) < Utility.getMesActual())
      return;
    System.out.println("***********\nProducto Vencido!\nFecha Vencimiento: " +
        this.getFechaVencimiento() + "\nCodigo: " +
        this.getCodigo() + "\nNombre: " + this.getNombre());
  }

  public void retirarLote(int lote) {
    if (this.getLote() == lote)
      this.setVigencia(2);
  }

  // public void colocarOferta(){}
  public void reponerInventario() {
    if (this.getUnidDispo() < 5)
      System.out.println("************\nAviso!!\nEl Producto: "
          + this.getNombre() + "\nSe debe reponer\n************");

  }

  public void venderUnidad() {
    unidDispo -= 1;
    unidVendidas += 1;
  }

  public boolean venderUnidad(int cantidad) {
    if (!(Utility.validarRango(0, unidDispo, cantidad)) || !(unidDispo > 0))
      return false;
    unidDispo -= cantidad;
    unidVendidas += cantidad;
    return true;
  }

  public String getInformacion() {
    String info = "************\nDatos del producto\nNombre: " + this.getNombre() +
        "\nCosto: " + Double.toString(this.getCosto()) + "\nPrecio de venta: " +
        Double.toString(this.getCostoVenta()) + "\nUnidades vendidas: " +
        Integer.toString(this.getUnidVendidas()) + "\nUnidades disponibles: " +
        Integer.toString(this.getUnidDispo()) + "\nFecha caducidad: " + this.getFechaVencimiento() +
        "\nNumero de lote: " + Integer.toString(this.getLote()) + "\nVigencia: " + Integer.toString(this.getVigencia())
        + "\n";
    switch (this.getVigencia()) {
      case 0 -> {
        info += " No disponible en el mercado";
      }
      case 1 -> {
        info += "Disponible";
      }
      case 2 -> {
        info += "Retirado";
      }
    }
    info += "\n";

    return info;

  }

  public boolean estaVigente() {
    return vigencia == 1;
  }

  public void mostrarInformacion() {
    System.out.println(
        "************\nDatos del producto\nNombre: " + this.getNombre() +
            "\nCosto: " + this.getCosto() + "\nPrecio de venta: " +
            Utility.getSolo2Decimales(this.getCostoVenta()) + "\nUnidades vendidas: " +
            this.getUnidVendidas() + "\nUnidades disponibles: " +
            this.getUnidDispo() + "\nFecha caducidad: " + this.getFechaVencimiento() +
            "\nNumero de lote: " + this.getLote() + "\nVigencia: " + this.getVigencia()+"\n");
    switch (this.getVigencia()) {
      case 0 -> {
        System.out.println("No disponible en el mercado");
      }
      case 1 -> {
        System.out.println("Disponible");
      }
      case 2 -> {
        System.out.println("Retirado");
      }

    }
  }

  public void colocarOferta() {
    if ((Utility.getMesVencimiento(fechaVencimiento) - Utility.getMesActual()) >= 3)
      this.precioAPagar(-30);
  }

  public void leerDatos() {
    String input;
    double datoDecimal;
    int datoInt;
    do {
      input = Utility.pedirStr("Ingrese el codigo del medicamento (Ej: AB12345678)");
      if (Utility.validarCodigo(input))
        break;
      System.out.println("Error! Ingrese el codigo con el formato correcto! (Ej: AB12345678)");
    } while (true);
    this.setCodigo(input);
    do {
      datoDecimal = Utility.pedirDouble("Ingrese el costo del medicamento");
      if (Utility.validarMayorIgualCero(datoDecimal))
        break;
      System.out.println("Error! El codigo no puede ser menor que 0!");
    } while (true);
    this.setCosto(datoDecimal);
    this.precioAPagar(20);
    do {
      input = Utility.pedirStr("Ingrese la fecha de vencimiento (mm/yyyy)");

      if (!Utility.validarFechaVencimiento(input)) {
        System.out.println("Error! Asegurese de utilizar un formato correcto! (mm/yyyy)");
        continue;
      }
      this.setFechaVencimiento(input);
      if (!Utility.validarRango(1, 12, Utility.getMesVencimiento(fechaVencimiento))) {
        System.out.println("Error! El valor del mes no puede ser menor que 1 ni mayor que 12!");
        continue;
      }
      if (Utility.getAnioVencimiento(fechaVencimiento) == 0) {
        System.out.println("Error! El año no puede ser 0!");
      } else
        break;
    } while (true);
    do {
      datoInt = Utility.pedirInt("Ingrese el numero de lote");
      if (Utility.validarMayorIgualCero(datoInt))
        break;
      System.out.println("Error! El lote no puede ser menor que 0!");
    } while (true);
    this.setLote(datoInt);
    this.setNombre(Utility.pedirStr("Ingrese el nombre del medicamento"));
    do {
      datoInt = Utility.pedirInt("Ingrese la cantidad de unidades disponibles");
      if (Utility.validarMayorIgualCero(datoInt))
        break;
      System.out.println("Error! El numero de unidades no puede ser menor que 0!");

    } while (true);
    this.setUnidDispo(datoInt);
    do {
      datoInt = Utility.pedirInt("Ingrese la cantidad de unidades vendidas");
      if (!Utility.validarMayorIgualCero(datoInt)) {
        System.out.println("Error! Las unidades vendidas no pueden ser menores que 0!");
        continue;
      }
      if (datoInt >= this.getUnidDispo()) {
        System.out.println("Error! Las unidades vendidas no pueden ser mayores a las disponibles!");
      } else
        break;
    } while (true);
    this.setUnidVendidas(datoInt);
    do {
      datoInt = Utility.pedirInt("Ingrese la vigencia del producto (0-2)");
      if (Utility.validarRango(0, 2, datoInt))
        break;
      System.out.println("Error! El valor debe ser mayor o igual a 0 o menos o igual a 2!");
    } while (true);
    this.setVigencia(datoInt);
    this.colocarOferta();
  }
}
