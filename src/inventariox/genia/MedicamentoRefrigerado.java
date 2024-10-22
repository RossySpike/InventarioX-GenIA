/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia;

/**
 *
 * @author honey
 */
public class MedicamentoRefrigerado extends Medicamento {
  /*
   * Cadena de frio ->
   * Certificacion de cadena de frio.
   * Termometros (?
   * Tiempo sin refrigeracion
   * Implementar un sistema de puntaje para decidir si es apto
   */
  private Boolean certifCadenaFrio;
  private double tempMin, tempMax;// Celsius
  private double tiempAbierto;
  private double tiempSinRefri;

  public MedicamentoRefrigerado() {
    this.certifCadenaFrio = false;
    this.tempMin = 0;
    this.tempMax = 0;
    this.tiempAbierto = 0;
    this.tiempSinRefri = 0;
  }

  public MedicamentoRefrigerado(String codigo, int unidVendidas, int unidDispo, int lote, int vigencia,
      double costoCompra, String nombre, String fechaVencimiento, Boolean certifCadenaFrio, double tempMin,
      double tempMax, double tiempAbierto, double tiempSinRefri) {
    super(codigo, unidVendidas, unidDispo, lote, vigencia, costoCompra, nombre, fechaVencimiento);
    this.certifCadenaFrio = certifCadenaFrio;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
    this.tiempAbierto = tiempAbierto;
    this.tiempSinRefri = tiempSinRefri;
  }

  public Boolean getCertifCadenaFrio() {
    return certifCadenaFrio;
  }

  public void setCertifCadenaFrio(Boolean certifCadenaFrio) {
    this.certifCadenaFrio = certifCadenaFrio;
  }

  public double getTiempSinRefri() {
    return tiempSinRefri;
  }

  public void setTiempSinRefri(double tiempSinRefri) {
    this.tiempSinRefri = tiempSinRefri;
  }

  public double getTempMin() {
    return tempMin;
  }

  public void setTempMin(double tempMin) {
    this.tempMin = tempMin;
  }

  public double getTempMax() {
    return tempMax;
  }

  public void setTempMax(double tempMax) {
    this.tempMax = tempMax;
  }

  public double getTiempAbierto() {
    return tiempAbierto;
  }

  public void setTiempAbierto(double tiempAbierto) {
    this.tiempAbierto = tiempAbierto;
  }

  public void precioAPagar() {
    super.precioAPagar(25);
  }

  @Override
  public void colocarOferta() {
    if ((this.getMesVencimiento() - Utility.getMesActual()) >= 3)
      super.precioAPagar(-5);// 25%-30% (?jajaj
  }

  public int calcularPuntajeRefrigeracion() {
    int puntos = 100;
    if (!this.getCertifCadenaFrio())
      puntos -= 50;
    if (this.getTiempSinRefri() != 0)
      puntos -= this.getTiempSinRefri() * 6.25;
    return puntos;
  }

  public void mostrarEstadoRefrigeracion() {
    int puntos = this.calcularPuntajeRefrigeracion();
    System.out.println("Tiempo sin refrigeracion: " + this.getTiempSinRefri()
        + "\nCertificado de cadena de frio: " + this.getCertifCadenaFrio() + "\nEstado: ");
    if (puntos == 100)
      System.out.println("Excelente.");
    if ((puntos < 100) && puntos > 85)
      System.out.println("Bueno.");
    if ((puntos < 85) && (puntos > 60))
      System.out.println("Regular.");
    else
      System.out.println("Malo.");

  }

  @Override
  public String getInformacion() {
    String info = super.getInformacion();
    int puntos = this.calcularPuntajeRefrigeracion();
    info += "Tiempo sin refrigeracion: " + Double.toString(this.getTiempSinRefri())
        + "h\nCertificado de cadena de frio: " + Boolean.toString(this.getCertifCadenaFrio()) + "\nEstado: ";
    if (puntos == 100)
      info += "Excelente.";
    if ((puntos < 100) && puntos > 85)
      info += "Bueno.";
    if ((puntos < 85) && (puntos > 60))
      info += "Regular.";
    else
      info += "Malo.";
    info += "\n";
    return info;

  }

  @Override
  public void leerDatos() {
    int datoInt;
    double datoDouble;
    super.leerDatos();
    this.colocarOferta();
    do {
      datoInt = Utility.pedirInt("Ingrese 1 si el medicamento tiene certificado de cadena de frio, y 0 si no");
      if (Utility.validarRango(0, 1, datoInt))
        break;
      System.out.println("Error! Por favor ingrese un dato en el rango especificado (0-1)!");

    } while (true);
    this.setCertifCadenaFrio(datoInt == 1);
    this.setTempMin(Utility.pedirDouble("Ingrese la temperatura Minima"));
    do {
      datoDouble = Utility.pedirDouble("Ingrese la temperatura Maxima");
      if (datoDouble > this.getTempMin())
        break;
      System.out.println("Error! la temperatura maxima no puede ser menor que la temperatura minima!");
    } while (true);
    this.setTempMax(datoDouble);

    do {
      datoDouble = Utility.pedirDouble("ingresa la duracion en horas del medicamento una vez abierto");
      if (Utility.validarMayorIgualCero(datoDouble))
        break;
      System.out.println("Error! El valor no puede ser menor que 0!");

    } while (true);
    this.setTiempAbierto(datoDouble);
    do {
      datoDouble = Utility.pedirDouble("Ingrese el tiempo en horas que ha pasado sin refrigeracion el medicamento");
      if (Utility.validarMayorIgualCero(datoDouble))
        break;
      System.out.println("Error! No puede ser menor que 0!");
    } while (true);
    this.setTiempSinRefri(datoDouble);
  }

}
