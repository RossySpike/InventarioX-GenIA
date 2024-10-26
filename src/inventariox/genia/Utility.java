/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author honey
 */
public class Utility {
  // validar fecha

  public static int pedirInt(String mensaje) {
    int dato;
    Scanner input = new Scanner(System.in);
    try {
      System.out.print(mensaje + "\n->: ");
      dato = input.nextInt();

    } catch (java.util.InputMismatchException excep) {
      System.out.println("Error! Por Favor Introduce un numero!");
      dato = Utility.pedirInt(mensaje);
    }

    return dato;
  }

  public static double pedirDouble(String mensaje) {
    double dato;
    Scanner input = new Scanner(System.in);
    try {
      System.out.print(mensaje + "\n->: ");
      dato = input.nextInt();

    } catch (java.util.InputMismatchException excep) {
      System.out.println("Error! Por Favor Introduce un numero!");
      dato = Utility.pedirInt(mensaje);
    }

    return dato;
  }

  public static String pedirStr(String mensaje) {
    Scanner input = new Scanner(System.in);
    System.out.print(mensaje + "\n->: ");
    String recibido = input.nextLine().trim();
    if (recibido.equals("")) {
      System.out.println("Por favor ingresa un mensaje no vacio.");
      recibido = Utility.pedirStr(mensaje);
    }
    return recibido;

  }

  public static Boolean validarRango(int min, int max, int valor) {
    return ((min <= valor) && (max >= valor));
  }

  public static Boolean validarMayorIgualCero(int valor) {
    return (valor >= 0);
  }

  public static Boolean validarMayorIgualCero(double valor) {
    return (valor >= 0);
  }

  public static int getAnioActual() {
    return java.time.LocalDate.now().getYear();
  }

  public static int getMesActual() {
    return java.time.LocalDate.now().getMonthValue();
  }
  public static String getSolo2Decimales(double dato){
      DecimalFormat f = new DecimalFormat("#.##");
      return f.format(dato);
  }
  public static String traducirBoolean(boolean bool){ return bool ? "Si":"No";}
  public static Boolean validarFechaVencimiento(String fecha) {
    Pattern p = Pattern.compile("^\\d{2,2}+/\\d{4,4}$");
    Matcher m = p.matcher(fecha);
    return m.find();
  }
    public static int getMesVencimiento(String fecha) {
    Pattern p = Pattern.compile("\\d{2}");
    Matcher m = p.matcher(fecha);
    m.find();
    return Integer.parseInt(m.group());

  }

  public static int getAnioVencimiento(String fecha) {
    Pattern p = Pattern.compile("\\d{4}");
    Matcher m = p.matcher(fecha);
    m.find();
    return Integer.parseInt(m.group());

  }

  public static Boolean validarCodigo(String codigo) {
    Pattern p = Pattern.compile("^[A-Z]{2,2}+\\d{8,8}$");
    Matcher m = p.matcher(codigo);
    return m.find();

  }

}
