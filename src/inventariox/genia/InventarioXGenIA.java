/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventariox.genia;
import inventariox.genia.controladora.Controladora;
import inventariox.genia.vista.PantallaCarga;
import inventariox.genia.vista.PantallaPrincipal;
import java.util.ArrayList;

/**
 *
 * @author honey
 */
public class InventarioXGenIA {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
          ListaMedicamentos listaMed = new ListaMedicamentos();

          ArrayList<Venta> listaVenta = new ArrayList<>();
    listaMed.aggUltimo(new Medicamento("AB12345678", 10, 50, 12345, 1, 25.99, "Paracetamol", "12/2024"));
    listaMed.aggUltimo(new Medicamento("CD87654321", 20, 60, 12346, 0, 15.50, "Ibuprofeno", "11/2023"));
    listaMed.aggUltimo(new Medicamento("EF11223344", 5, 30, 12347, 2, 45.75, "Amoxicilina", "05/2025"));
    listaMed.aggUltimo(new Medicamento("GH55667788", 12, 40, 12348, 1, 10.25, "Aspirina", "08/2024"));
    listaMed.aggUltimo(new Medicamento("IJ99887766", 18, 70, 12349, 0, 35.00, "Loratadina", "02/2026"));
    listaMed.aggUltimo(new MedicamentoAmbiente("KL33445566", 8, 50, 12350, 1, 20.00, "Cetirizina", "09/2023",
        new String[] { "Luz directa del sol", "Alta humedad", "Temperaturas extremas" }));
    listaMed.aggUltimo(new MedicamentoAmbiente("MN44556677", 6, 40, 12351, 2, 22.50, "Metformina", "03/2024",
        new String[] { "Cerca de radiadores", "En baños", "Cerca de ventanas" }));
    listaMed.aggUltimo(new MedicamentoRefrigerado("OP55667788", 9, 20, 12352, 1, 12.30, "Insulina", "06/2025", true,
        2.0, 8.0, 24.0, 12.0));
    listaMed.aggUltimo(new MedicamentoRefrigerado("QR66778899", 4, 10, 12353, 0, 18.75, "Epinefrina", "04/2025", true,
        2.0, 8.0, 10.0, 6.0));
    listaMed.aggUltimo(new MedicamentoRefrigerado("ST77889900", 7, 30, 12354, 2, 30.50, "Hormona de crecimiento humana",
        "11/2024", true, 2.0, 8.0, 36.0, 18.0));
    listaMed.aggUltimo(new Medicamento("UV12345678", 15, 25, 12355, 1, 50.00, "Testosterona", "07/2025"));
    listaMed.aggUltimo(new Medicamento("WX87654321", 10, 20, 12356, 2, 45.00, "Nandrolona", "03/2024"));
    listaMed.aggUltimo(new Medicamento("YZ11223344", 5, 15, 12357, 0, 55.75, "Oxandrolona", "10/2026"));
    listaMed.aggUltimo(new Medicamento("AB99887766", 8, 18, 12358, 1, 60.00, "Estanozolol", "12/2025"));
    listaMed.aggUltimo(new Medicamento("CD33445566", 12, 22, 12359, 2, 48.50, "Trembolona", "05/2024"));
      /*
    Runnable mRun = () ->{
            PantallaCarga pCarga= new PantallaCarga();
            pCarga.setVisible(true);


            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(InventarioXGenIA.class.getName()).log(Level.SEVERE, null, ex);
            }
            pCarga.dispose();
            PantallaPrincipal pPal = new PantallaPrincipal();

            pPal.setVisible(true);
                };


        Thread pSplash = new Thread(mRun);
        pSplash.start();  
    */
    Controladora c = new Controladora(listaMed);
    c.iniciarPantallaCarga(new PantallaCarga());
    c.iniciarPantallaPrincipal(new PantallaPrincipal(listaMed),"InventarioX-GenIA/src/inventariox/genia/vista/pill.png");
    String datoStr;
    double total;
    int opcion;

    boolean salir = false;
    do {
      opcion = Utility.pedirInt(
          "Elige una opcion.\n1) Para agregar un medicamento.\n2) Mostrar todos los medicamentos.\n3) Buscar un producto.\n4) Para ver lista de compra.\n5) Mostrar total a pagar.\n9) Para salir.");
      switch (opcion) {
        case 1 -> {
          do {
            opcion = Utility.pedirInt(
                "Que tipo de medicamento deseas agregar?\n1) Para un medicamento generico\n2) Para un medicamento no refrigerado\n3) Para un medicamento refrigerado");
            if (Utility.validarRango(1, 3, opcion))
              break;
          } while (true);
          switch (opcion) {
            case 1 -> {
              listaMed.aggUltimo(new Medicamento());
              listaMed.getUltimo().leerDatos();
            }
            case 2 -> {
              listaMed.aggUltimo(new MedicamentoAmbiente());
              listaMed.getUltimo().leerDatos();

            }
            case 3 -> {
              listaMed.aggUltimo(new MedicamentoRefrigerado());
              listaMed.getUltimo().leerDatos();

            }
          }
        }
        case 2 -> {
          listaMed.mostrarLista();
        }
        case 3 -> {
          boolean continuarCiclo = true;
          Medicamento temp = new Medicamento();
          ListaMedicamentos encontrados = new ListaMedicamentos();
          do {
            opcion = Utility
                .pedirInt("Como deseas buscar el producto?\n1) Para buscar por codigo.\n2) Para buscar por nombre.");
            if (Utility.validarRango(1, 2, opcion))
              break;
            System.out.println("Error! Ingrese un valor dentro del rango!");
          } while (true);

          switch (opcion) {

            case 1 -> {

              do {
                datoStr = Utility.pedirStr("Ingrese el codigo del medicamento (Ej: AB12345678)");
                if (temp.validarCodigo(datoStr))
                  break;
                System.out.println("Error! Ingrese el codigo con el formato correcto! (Ej: AB12345678)");
              } while (true);

              encontrados = listaMed.buscarCodigoMed(datoStr);
            }
            case 2 -> {
              datoStr = Utility.pedirStr("Ingrese el nombre del producto que desea buscar");
              encontrados = listaMed.buscarNombreMed(datoStr);
            }

          }
          if (encontrados.estaVacia()) {
            System.out.println("******************\nNo se ha encontrado el producto!\n******************");
            break;
          }

          // Reducir unid dispo y aument uni vendidaas.
          temp = encontrados.selecMed();
          while (continuarCiclo) {
            opcion = Utility.pedirInt(
                "Que quieres hacer?\nIntroduzca: 1) Para comprarlo. 2) Para mostrar sus datos. 3) para eliminarlo. 4) Para salir.");
            switch (opcion) {
              case 4 -> {
                continuarCiclo = false;
              }
              case 1 -> {
                if (temp.getVigencia() == 1 && temp.getUnidDispo()>0) {
                  temp.venderUnidad();
                  listaVenta.addLast(new Venta(temp.getNombre(), temp.getCostoVenta()));

                } else
                  System.out.println(
                      "*******************\nLo sentimos, el producto no se encuentra disponible!\n*******************");
                continuarCiclo = false;

              }
              case 2 -> {
                temp.mostrarInformacion();

              }
              case 3 -> {
                listaMed.elimMed(temp);
                continuarCiclo = false;

              }

              default -> System.out.println("Error! Ingrese un valor dentro del rango!");
            }
          }
        }
        // comparar para la busqueda por codigo en regex por codigo y nombre, comprar y
        // eliminar
        // buscar y que deseas hacer
        case 4 -> {
          if (listaVenta.isEmpty()) {
            System.out.println("***********\nNo hay productos en el carrito\n***********");

          } else {
            for (Venta venta : listaVenta) {
              venta.mostrarDatos();
            }
          }
        }
        case 5 -> {
          if (listaVenta.isEmpty()) {
            System.out.println("***********\nNo hay productos en el carrito\n***********");

          } else {
            total = 0;
            for (Venta venta : listaVenta) {
              total += venta.getCosto();
            }
            System.out.println("El total a pagar es: " + total + "$");
          }

        }
        case 9 -> salir = true;
        default -> System.out.println("Ingrese un numero valido!");
      }

    } while (!salir);

  }

}
