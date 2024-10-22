/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia.controladora;

import inventariox.genia.InventarioXGenIA;
import inventariox.genia.ListaMedicamentos;
import inventariox.genia.vista.PantallaCliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author honey
 */
public class Controladora {
    ListaMedicamentos lista;
    public Controladora(){}
  public Controladora(ListaMedicamentos lista) {
      this.lista = lista;
  }

  public void iniciaVentana(JFrame wd, String rutaIcon) {
    wd.setLocationRelativeTo(null);
    wd.setIconImage(new ImageIcon(rutaIcon).getImage());
    wd.setTitle("Inventario XGenIA");
  }

  public void iniciaVentana(JFrame wd) {
    wd.setLocationRelativeTo(null);
  }

  public void iniciarPantallaCarga(JFrame wd) {
    Runnable run = () -> {
      iniciaVentana(wd);
      wd.setVisible(true);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        Logger.getLogger(InventarioXGenIA.class.getName()).log(Level.SEVERE, null, e);
      }
      wd.dispose();
    };
    Thread t = new Thread(run);
    t.start();
    try {
      t.join();
    } catch (InterruptedException e) {
      Logger.getLogger(InventarioXGenIA.class.getName()).log(Level.SEVERE, null, e);
    }

  }

  public void iniciarPantallaPrincipal(JFrame wd, String rutaIcon) {
    iniciaVentana(wd, rutaIcon);
    wd.setVisible(true);

  }

  public void cerrarPrograma() {
    System.exit(0);
  }
  public void agregarNombMed(JComboBox listaMed){
      listaMed.setModel(new javax.swing.DefaultComboBoxModel<>(lista.getNombreMedicAsArray()));
      
  }
  public void obtExistenciaMed(String nombreMed,JComboBox listaEx){
      mostrarExistencia(listaEx,lista.buscarNombreMed(nombreMed).getPrimero().getUnidDispo());
  }
  public void mostrarExistencia(JComboBox listaEx, int existencia){
      String arr[] = new String[existencia];
      for (int i=0; i<existencia;i++){
          arr[i]=Integer.toString(i+1);
      }
      listaEx.setModel(new javax.swing.DefaultComboBoxModel<>(arr));
      listaEx.repaint();
  }
  public void iniciarPantallaCliente(PantallaCliente wd){
      
      iniciaVentana(wd,"InventarioX-GenIA/src/inventariox/genia/vista/pill.png");
      agregarNombMed(wd.getjComboBox1());
      mostrarExistencia(wd.getjComboBox2(),lista.getPrimero().getUnidDispo());
      wd.setVisible(true);
      
      
  }
  public ListaMedicamentos getLista(){return this.lista;}

/*
  
    for (String i: listaMed.getNombreMedicAsArray()){
        System.out.println(i);
    }
  */


}
