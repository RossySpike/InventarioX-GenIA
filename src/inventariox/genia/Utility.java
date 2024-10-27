/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

  public static String getSolo2Decimales(double dato) {
    DecimalFormat f = new DecimalFormat("#.##");
    return f.format(dato);
  }

  public static String traducirBoolean(boolean bool) {
    return bool ? "Si" : "No";
  }

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

  public static Boolean validarNatural(String fecha) {
    Pattern p = Pattern.compile("^\\d{1,8}$");// Sirve para tanto validar naturales y naturales muy grandes
    Matcher m = p.matcher(fecha);
    return m.find();
  }

  public static Boolean validarEntero(String fecha) {
    Pattern p = Pattern.compile("^-?\\d{1,8}$");// Sirve para tanto validar enteros y enteros muy grandes
    Matcher m = p.matcher(fecha);
    return m.find();
  }

  public static Boolean validarDouble(String dato) {

    Pattern p = Pattern.compile("^-?\\d{1,5}.?\\d{0,3}$");// positivos y negativos,
    Matcher m = p.matcher(dato);
    return m.find();
  }

  public static Boolean validarDoublePositivo(String dato) {

    Pattern p = Pattern.compile("^\\d{1,5}.?\\d{0,3}$");// positivos y negativos,
    Matcher m = p.matcher(dato);
    return m.find();
  }

  public static Boolean validarAlmacenamiento(String dato) {
    Pattern p = Pattern.compile("(?:\\b[\\w\\s]+\\b,\\s*){2,}\\b[\\w\\s]+\\b");// agarra al menos 3 palabras separadas
                                                                               // por coma y la ultima sin coma
    Matcher m = p.matcher(dato);
    
    return m.find();//
  }

  public static ArrayList<String> getLugares(String dato) {
    // Dividir la cadena en partes utilizando la coma como delimitador
    String[] partes = dato.split(",\\s*");
    ArrayList<String> lugares = new ArrayList<>();

    // Agregar cada parte a la lista, eliminando espacios en blanco adicionales
    for (String parte : partes) {
        lugares.add(parte.trim());
    }

    return lugares;
    }


  public static Boolean validarCodigo(String codigo) {
    Pattern p = Pattern.compile("^[A-Z]{2,2}+\\d{8,8}$");
    Matcher m = p.matcher(codigo);
    return m.find();

  }

}
