/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia.controladora;

import inventariox.genia.InventarioXGenIA;
import inventariox.genia.ListaMedicamentos;
import inventariox.genia.MedicamentoRefrigerado;
import inventariox.genia.Utility;
import inventariox.genia.Venta;
import inventariox.genia.vista.PantallaCliente;
import inventariox.genia.vista.PantallaEmpleado;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author honey
 */
public class Controladora {
  private ListaMedicamentos lista;
  private ArrayList<Venta> listaVenta;

  public Controladora() {
  }

  public Controladora(ListaMedicamentos lista) {
    this.lista = lista;
    listaVenta = new ArrayList<>();
  }

  public void iniciaVentana(JFrame wd) {
    wd.setLocationRelativeTo(null);
    wd.setTitle("Laboratoriox GenIA");
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

  public void iniciarPantallaPrincipal(JFrame wd) {
    iniciaVentana(wd);
    wd.setVisible(true);

  }

  public void cerrarPrograma() {
    System.exit(0);
  }

  public void agregarNombMed(JComboBox listaMed) {
    listaMed.setModel(new javax.swing.DefaultComboBoxModel<>(lista.getNombreMedicAsArray()));

  }

  public void obtExistenciaMed(String nombreMed, JComboBox listaEx) {
    mostrarExistencia(listaEx, lista.buscarNombreMed(nombreMed).getPrimero().getUnidDispo());
  }

  public void mostrarExistencia(JComboBox listaEx, int existencia) {
    String arr[] = new String[existencia];
    for (int i = 0; i < existencia; i++) {
      arr[i] = Integer.toString(i + 1);
    }
    listaEx.setModel(new javax.swing.DefaultComboBoxModel<>(arr));
    listaEx.repaint();
  }

  public void iniciarPantallaCliente(PantallaCliente wd) {

    iniciaVentana(wd);
    agregarNombMed(wd.getjComboBox1());
    mostrarExistencia(wd.getjComboBox2(), lista.getPrimero().getUnidDispo());
    wd.setVisible(true);

  }

  public void iniciarPantallaEmpleado(PantallaEmpleado wd) {
    iniciaVentana(wd);
    agregarNombMed(wd.getjComboBox1());

    wd.setVisible(true);

  }

  public boolean comprar(int indiceMed, int cantidad) {
    if (!lista.vender(indiceMed, cantidad))
      return false;
    registrarVenta(lista.getIndice(indiceMed).getNombre(), lista.getIndice(indiceMed).getCostoVenta(), cantidad);
    return true;
  }

  public ListaMedicamentos getLista() {
    return this.lista;
  }

  public void errorCompra(JDialog dialog, String errTxt) {
    ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/inventariox/genia/vista/pill.png"));
    JFrame frame = new JFrame();
    frame.setIconImage(icon.getImage());
    JOptionPane.showMessageDialog(frame, errTxt, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public boolean verificarEstado(int indiceMed) {
    return lista.getIndice(indiceMed).estaVigente();
  }

  public void mostrarInfo(JTextArea dest, int indiceMed) {
    dest.setText(lista.getIndice(indiceMed).getInformacion());
  }

  public void mostrarInfo(JTextArea dest, Venta vent) {
    String txt = "************";
    double costoTotal = 0;
    for (Venta venta : listaVenta) {
      txt += "\n" + venta.mostrarDatos() + "\n************";
      costoTotal += venta.getCosto();
    }
    dest.setText(txt + "\nCosto Total: " + Utility.getSolo2Decimales(costoTotal));
  }
  /*
   * 
   * for (String i: listaMed.getNombreMedicAsArray()){
   * System.out.println(i);
   * }
   */



  public void mostrarLbls(JLabel[] labels) {
    for (JLabel lbl : labels) {
      lbl.setVisible(true);
    }
  }

  public void mostrarTxts(JTextField[] txtField) {
    for (JTextField txt : txtField) {
      txt.setVisible(true);
    }
  }

  public void ocultarLbls(JLabel[] labels) {
    for (JLabel lbl : labels) {
      lbl.setVisible(false);
    }
  }

  public void ocultarTxts(JTextField[] txtField) {
    for (JTextField txt : txtField) {
      txt.setVisible(false);
      txt.setText(null);
    }
  }
  public void ocultarBotones(JButton[] btns) {
    for (JButton btn : btns) {
      btn.setVisible(false);
    }
  }

  public void mostrarBotones(JButton[] btns) {
    for (JButton btn : btns) {
      btn.setVisible(true);
    }
  }
  public void ocultarOpcMedRefri(JLabel[] lbls, JTextField[] txts, JRadioButton radB) {
      radB.setVisible(false);
      ocultarLbls(lbls);
      ocultarTxts(txts);
  }
  public void mostrarOpcMedRefri(JLabel[] lbls, JTextField[] txts, JRadioButton radB) {
      radB.setVisible(true);
      mostrarLbls(lbls);
      mostrarTxts(txts);

  }

  public void ocultarOpcMedNoRefri(JLabel lbl, JTextField txtArea, JLabel lbl2) {
    lbl.setVisible(false);
    txtArea.setVisible(false);
    lbl2.setVisible(false);
  }

  public void mostrarOpcMedNoRefri(JLabel lbl, JTextField txtArea, JLabel lbl2) {
    lbl.setVisible(true);
    txtArea.setVisible(true);
    lbl2.setVisible(true);
  }

  public void ocultarOpcTipoMed(JLabel[] lbls, JTextField[] txts, JRadioButton radB, JToggleButton tBtn) {
      ocultarLbls(lbls);
      ocultarTxts(txts);
      tBtn.setVisible(false);
      radB.setVisible(false);

  }

  public void ocultarOpcTipoMed(JLabel[] lbls, JTextField[] txts, JButton[] btns, JComboBox cmbB,JRadioButton radB, JToggleButton tBtn) {
      ocultarLbls(lbls);
      ocultarTxts(txts);
      ocultarBotones(btns);
      cmbB.setVisible(false);
      tBtn.setVisible(false);
      radB.setVisible(false);

  }
  public void ocultarOferta(JTextField txtArea, JLabel txt) {
    txtArea.setVisible(false);
    txt.setVisible(false);
  }

  public void mostrarOferta(JTextField txtArea, JLabel txt) {
    txtArea.setVisible(true);
    txt.setVisible(true);

  }

  public void ocultarOferta(JToggleButton btn, JTextField txt, JLabel consejo) {
    btn.setVisible(false);
    txt.setVisible(false);
    consejo.setVisible(false);

  }

  public void ocultarOpcModif(JButton[] btns, JLabel[] lbls,
      JTextField[] txts, JToggleButton tBtn, JRadioButton rButton, JComboBox cmbB) {
    ocultarBotones(btns);
    ocultarLbls(lbls);
    ocultarTxts(txts);
    tBtn.setVisible(false);
    rButton.setVisible(false);
    cmbB.setVisible(false);

  }

  public void mostrarOpcModif(JButton[] btns, JLabel[] lbls, JTextField[] txts,
      JToggleButton tBtn, JRadioButton rButton, JComboBox cmbB) {
    mostrarBotones(btns);
    mostrarLbls(lbls);
    mostrarTxts(txts);
    tBtn.setVisible(true);
    rButton.setVisible(true);
    cmbB.setVisible(true);

  }

  public void mostrarOpcModif(JButton[] btns, JLabel[] lbls, JTextField[] txts,
      JToggleButton tBtn, JComboBox cmbB) {
    mostrarBotones(btns);
    mostrarLbls(lbls);
    mostrarTxts(txts);
    tBtn.setVisible(true);
    cmbB.setVisible(true);

  }

  public int buscarVentaPorNombre(String nombre) {
    for (int i = 0; i < listaVenta.size(); i++) {
      if (listaVenta.get(i).getNombreProducto().equals(nombre))
        return i;
    }
    return -1;
  }

  public Venta obtVentaPorIndiceListaMed(int indice) {
    return listaVenta.get(buscarVentaPorNombre(lista.getIndice(indice).getNombre()));
  }

  public void addVenta(String nombre, double costo, int cantidad) {
    listaVenta.add(new Venta(nombre, costo * cantidad, cantidad));
  }

  public void registrarVenta(String nombre, double costo, int cantidad) {
    int pos;
    if (listaVenta.isEmpty()) {
      addVenta(nombre, costo, cantidad);
    } else if ((pos = buscarVentaPorNombre(nombre)) > -1) {
      listaVenta.get(pos).sumarCantidad(cantidad);
      listaVenta.get(pos).sumarCosto(cantidad * costo);
    } else
      addVenta(nombre, costo, cantidad);
  }

  public boolean medEstaVigente(int index) {
    return lista.getIndice(index).estaVigente();
  }
  public boolean medNecesitaRefrigeracion(int index){return lista.getIndice(index) instanceof MedicamentoRefrigerado;}

}
