/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author honey
 */
public class MedicamentoAmbiente extends Medicamento {
  private ArrayList<String> noAlmacenar = new ArrayList<>();

  public MedicamentoAmbiente() {
    // Inicializar en valores vacios...
  }

  public MedicamentoAmbiente(String codigo, int unidVendidas, int unidDispo, int lote, int vigencia, double costoCompra,
      String nombre, String fechaVencimiento, String[] lugarAlmacenamiento) {
    super(codigo, unidVendidas, unidDispo, lote, vigencia, costoCompra, nombre, fechaVencimiento);
    this.noAlmacenar.addAll(Arrays.asList(lugarAlmacenamiento));
  }

  public ArrayList<String> getNoAlmacenar() {
    return noAlmacenar;
  }

  public void setNoAlmacenar(ArrayList<String> noAlmacenar) {
    this.noAlmacenar = noAlmacenar;
  }

  public void addToNoAlmacenar(String lugar) {
    this.noAlmacenar.add(lugar);
  }

  public String getPosNoAlmacenamiento(int posicion) {

    return this.noAlmacenar.get(posicion);// Verificar Fuera de indice, Vacio
  }

  public void pedirLugarNoAlmacenamiento() {// 3 lugares
    String input;
    System.out.println("Ingrese los valores del lugar donde NO se debe almacenar");
    for (int i = 0; i < 3; i++) {
      input = Utility.pedirStr("Lugar " + String.valueOf(i + 1) + ")");
      // Verificar que no sea numero.
      this.addToNoAlmacenar(input);
    }
  }

  public void pedirLugarNoAlmacenamiento(int tam) {
    String input;
    System.out.println("Ingrese los valores del lugar donde NO se debe almacenar");
    for (int i = 0; i < tam; i++) {
      input = Utility.pedirStr("Lugar " + String.valueOf(i + 1) + ")");
      // Verificar que no sea numero.
      this.addToNoAlmacenar(input);
    }
  }

  @Override
  public void leerDatos() {
    int datoInt;
    super.leerDatos();
    do {
      datoInt = Utility
          .pedirInt("En cuantos lugares no se debe almacenar el medicamento? (Recuerde que no debe ser menor a 3)");
      if (datoInt > 2)
        break;
    } while (true);
    this.pedirLugarNoAlmacenamiento(datoInt);
  }

  @Override
  public void mostrarInformacion() {
    super.mostrarInformacion();
    System.out.print("No se debe almacenar en: ");
    for (String lugar : this.noAlmacenar) {
      System.out.print(lugar + ". ");
    }
    System.out.println();
  }

  @Override
  public String getInformacion() {
    String info = super.getInformacion();
    info += "No se debe almacenar en: ";
    for (String lugar : this.noAlmacenar) {
      info += lugar + " ";
    }
    info += "\n";
    return info;
  }
}
