/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author honey
 */
public class ListaMedicamentos {
  ArrayList<Medicamento> listaMedicamentos;
  

  public ListaMedicamentos() {
    listaMedicamentos = new ArrayList<>();
  }

  public ArrayList<Medicamento> getListaMedicamentos() {
    return listaMedicamentos;
  }

  public void setListaMedicamentos(ArrayList<Medicamento> listaMedicamentos) {
    this.listaMedicamentos = listaMedicamentos;
  }

  public Boolean estaVacia() {
    return listaMedicamentos.isEmpty();
  }

  public Medicamento getUltimo() {
    return listaMedicamentos.getLast();
  }

  public Medicamento getPrimero() {
    return listaMedicamentos.getFirst();
  }

  public Medicamento getIndice(int indice) {
    return listaMedicamentos.get(indice);
  }

  public void aggUltimo(Medicamento med) {
    listaMedicamentos.addLast(med);
  }

  public void aggPrimero(Medicamento mend) {
    listaMedicamentos.addFirst(mend);
  }

  public void mostrarLista() {
    if (!this.estaVacia()) {
      for (Medicamento med : listaMedicamentos) {
        med.mostrarInformacion();
        System.out.println();
      }
    } else
      System.out.println("No hay medicamentos disponibles");
  }

  public void elimMed(Medicamento med) {
    listaMedicamentos.remove(med);
  }

  public void elimUltimo() {
    listaMedicamentos.removeFirst();
  }

  public void elimPrimero() {
    listaMedicamentos.removeFirst();
  }

  public void elimIndice(int indice) {
    listaMedicamentos.remove(indice);
  }

  public ListaMedicamentos buscarCodigoMed(String codigo) {
    ListaMedicamentos encontrados = new ListaMedicamentos();
    for (Medicamento med : listaMedicamentos) {
      if (codigo.equals(med.getCodigo()))
        encontrados.aggUltimo(med);
    }
    return encontrados;
  }

  public ListaMedicamentos buscarNombreMed(String nombre) {
    ListaMedicamentos encontrados = new ListaMedicamentos();
    Pattern p = Pattern.compile(nombre, Pattern.CASE_INSENSITIVE);
    Matcher m;
    for (Medicamento med : listaMedicamentos) {
      m = p.matcher(med.getNombre());
      if (m.find())
        encontrados.aggUltimo(med);

    }
    return encontrados;
  }

  public Medicamento selecMed() {
    int datoInt;
    do {
      System.out.println("Lista de Medicamentos disponibles:");
      for (int i = 0; i < listaMedicamentos.size(); i++) {
        System.out.println(i + 1 + ") Nombre: " + listaMedicamentos.get(i).getNombre() + "\nPrecio: "
            + listaMedicamentos.get(i).getCostoVenta() + "\nCodigo: " + listaMedicamentos.get(i).getCodigo());

      }
      datoInt = Utility.pedirInt("Ingrese el numero del medicamento que desea elegir") - 1;
      if (Utility.validarRango(0, listaMedicamentos.size(), datoInt))
        break;
      System.out.println("Error! Ingrese un valor dentro del rango!");
    } while (true);
    return listaMedicamentos.get(datoInt);
  }
  public String [] getNombreMedicAsArray(){
      ArrayList<String> listaNomb = new ArrayList<>();
      
      for (Medicamento med : listaMedicamentos){
          listaNomb.add(med.getNombre());
      }
      return listaNomb.stream().toArray(String[]::new);
  }
}
