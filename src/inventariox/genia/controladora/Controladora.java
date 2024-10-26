/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia.controladora;

import inventariox.genia.InventarioXGenIA;
import inventariox.genia.ListaMedicamentos;
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
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


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
    registrarVenta(lista.getIndice(indiceMed).getNombre(),lista.getIndice(indiceMed).getCostoVenta(),cantidad);
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
  public void mostrarInfo(JTextArea dest, Venta vent){
      String txt= "************";
      double costoTotal=0;
      for (Venta venta : listaVenta){
          txt+="\n"+venta.mostrarDatos()+"\n************";
          costoTotal+=venta.getCosto();
      }
      dest.setText(txt+"\nCosto Total: "+Utility.getSolo2Decimales(costoTotal));
  }
  /*
   * 
   * for (String i: listaMed.getNombreMedicAsArray()){
   * System.out.println(i);
   * }
   */
  
  public void ocultarOpcMedRefri(JRadioButton btnCertificado,JLabel tempMax, JLabel tempMin,JTextField txtMin,
    JLabel lblTmpSinRefri, JTextField txtTmpSinRefri, JTextField txtTmpAbrir,JTextField txtTempMax, JLabel tmpAbrir){
      btnCertificado.setVisible(false);
      tempMax.setVisible(false);
      tempMin.setVisible(false);
      txtMin.setVisible(false);
      lblTmpSinRefri.setVisible(false);
      txtTmpSinRefri.setVisible(false);
      txtTmpAbrir.setVisible(false);
      tmpAbrir.setVisible(false);
      txtTempMax.setVisible(false);

      
      
  }
  public void mostrarOpcMedRefri(JRadioButton btnCertificado,JLabel tempMax, JLabel tempMin,JTextField txtMin,
    JLabel lblTmpSinRefri, JTextField txtTmpSinRefri, JTextField txtTmpAbrir, JTextField txtTempMax, JLabel tmpAbrir){
      btnCertificado.setVisible(true);
      tempMax.setVisible(true);
      tempMin.setVisible(true);
      txtMin.setVisible(true);
      lblTmpSinRefri.setVisible(true);
      txtTmpSinRefri.setVisible(true);
      txtTmpAbrir.setVisible(true);
      tmpAbrir.setVisible(true);
      txtTempMax.setVisible(true);
      
      
  }
  public void ocultarOpcMedNoRefri(JLabel lbl, JTextField txtArea){
      lbl.setVisible(false);
      txtArea.setVisible(false);
  }
  public void mostrarOpcMedNoRefri(JLabel lbl, JTextField txtArea){
      lbl.setVisible(true);
      txtArea.setVisible(true);      
  }
  public void ocultarOpcTipoMed(JRadioButton btnCertificado,JLabel tempMax, JLabel tempMin,JTextField txtMin,
    JLabel lblTmpSinRefri, JTextField txtTmpSinRefri, JTextField txtTmpAbrir, JTextField txtTempMax, JLabel tmpAbrir,
    JLabel lbl, JTextField txtArea){
      ocultarOpcMedRefri(btnCertificado, tempMax,  tempMin, txtMin,
     lblTmpSinRefri,  txtTmpSinRefri,  txtTmpAbrir, txtTempMax,  tmpAbrir);
      ocultarOpcMedNoRefri(lbl, txtArea);
      
  }
  public void ocultarOferta(JTextField txtArea){
      txtArea.setVisible(false);
  }
  public void mostrarOferta(JTextField txtArea){
      txtArea.setVisible(true);      
  }
  public int buscarVentaPorNombre(String nombre){
      for (int i = 0; i<listaVenta.size(); i++){
          if (listaVenta.get(i).getNombreProducto().equals(nombre))
              return i;
      }
      return -1;
  }
  public Venta obtVentaPorIndiceListaMed(int indice){
      return listaVenta.get(buscarVentaPorNombre(lista.getIndice(indice).getNombre()));
  }
  
  public void addVenta(String nombre, double costo, int cantidad){
      listaVenta.add(new Venta(nombre,costo*cantidad,cantidad));
  }
  
  public void registrarVenta(String nombre, double costo, int cantidad){
      int pos;
      if(listaVenta.isEmpty()){
          addVenta(nombre,costo,cantidad);
          }
      else if ((pos = buscarVentaPorNombre(nombre))>-1){
          listaVenta.get(pos).sumarCantidad(cantidad);
          listaVenta.get(pos).sumarCosto(cantidad*costo);
      }
      else
          addVenta(nombre,costo,cantidad);
  }
  public boolean medEstaVigente(int index){return lista.getIndice(index).estaVigente();}

    
}
