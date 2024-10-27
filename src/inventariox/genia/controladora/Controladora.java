/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventariox.genia.controladora;

import inventariox.genia.InventarioXGenIA;
import inventariox.genia.ListaMedicamentos;
import inventariox.genia.Medicamento;
import inventariox.genia.MedicamentoAmbiente;
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
    public void raiseWarning(String txt, String titulo, ImageIcon icon, int tipoMensaje){
        JFrame frame = new JFrame();
        frame.setIconImage(icon.getImage());
        JOptionPane.showMessageDialog(frame, txt, titulo,tipoMensaje);
    }
  public void ventanaError( String errTxt) {
    ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/inventariox/genia/vista/pill.png"));
    raiseWarning(errTxt,"Error",icon,JOptionPane.ERROR_MESSAGE);
    
  }
  public void ventanaExito(String txt){
      ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/inventariox/genia/vista/pill.png"));
      raiseWarning(txt,"Exito",icon,JOptionPane.INFORMATION_MESSAGE);
  }

  public boolean verificarEstado(int indiceMed) {
    return lista.getIndice(indiceMed).estaVigente();
  }

  public void mostrarInfo(JTextArea dest, int indiceMed) {
      if(lista.estaVacia()){
          dest.setText("Vacio! D:");
          return;
      }
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
      radB.setSelected(false);
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
    txtArea.setText(null);
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
  public void mostrarTipoMedRefri(JLabel[] lbls, JTextField[] txts,JRadioButton radB){
      mostrarLbls(lbls);
      mostrarTxts(txts);
      radB.setVisible(true);
      
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

  
  public void setIcon(JLabel lbl, String iconName, String txt){
      lbl.setVisible(true);
      lbl.setToolTipText(txt); lbl.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconName)));
  }
  public void raiseIcon(JLabel lbl, String txt, boolean bueno){
      if (bueno)
          setIcon(lbl,"/inventariox/genia/vista/cheque.png",":Ds");
      else
          setIcon(lbl,"/inventariox/genia/vista/advertencia.png",txt);
  }
  public boolean validarCodigoMed(String cod, JLabel lbl){
      boolean m = Utility.validarCodigo(cod);
      raiseIcon(lbl, "El codigo debe comenzar con 2 letras mayusculas seguidas de 8 digitos!",m);
      return Utility.validarCodigo(cod);
          
  }
  
  public boolean validarNombre(String nom,JLabel lbl) {raiseIcon(lbl,"El nombre no puede estar vacio!",!(nom.isEmpty())); return !nom.isEmpty();}
  
  public boolean validarFecha(String fecha, JLabel lbl){
      if (!Utility.validarFechaVencimiento(fecha)){
          raiseIcon(lbl, "Fecha con formato invalida! (MM/AAAA) donde M=mes y A=año",false);
          return false;}
      else if (!(Utility.getAnioActual()<=Utility.getAnioVencimiento(fecha))){
          raiseIcon(lbl, "Producto ya caducado",false);
          return false;}
      boolean m = Utility.getMesActual()<Utility.getMesVencimiento(fecha);
      raiseIcon(lbl,"Producto ya caducado",m);      
      return m;
  }
  public boolean validarNatural(String dato, JLabel lbl){
      boolean m = Utility.validarNatural(dato);
      raiseIcon(lbl, "Error ingresa un numero valido!(Positivo, max 8 cifras)",m);
      return Utility.validarNatural(dato);
  }
  
  public boolean validarOferta(String dato, JLabel lbl){
      boolean m = Utility.validarDouble(dato);
      raiseIcon(lbl,"Error Ingresa un numero valido!(de 1 a 5 enteros y de 1 a 3 decimales)!",m);
      return m;
  }
  public boolean validarNoAlma(String txt,JLabel lbl){
      boolean m = Utility.validarAlmacenamiento(txt);
      raiseIcon(lbl,"Error, Debes indicar almenos 3 lugares distintos y separarlos por comas!",m);
      return m;
  }
  public boolean validarTemp(String tempMax, String tempMin, JLabel lbl){
      boolean m = Utility.validarDouble(tempMax);
      boolean c = Utility.validarDouble(tempMin);
      if(!m || !c){
          raiseIcon(lbl,"Error Ingresa un numero valido!(de 1 a 5 enteros y de 1 a 3 decimales)!",!(!m || !c));
          return m;
      }
      
      m = (int) Math.round(Double.parseDouble(tempMax)) > (int) Math.round(Double.parseDouble(tempMin));
          raiseIcon(lbl,"Temperatura minima y maxima incompatibles",m);
          return m;
      
  }
  public boolean validarHoras(String dato, JLabel lbl){
      boolean m = Utility.validarDoublePositivo(dato);
      raiseIcon(lbl, "Error, la hora no puede ser negativa y debe ser un numero natural o deminal",m);
      return m;
      
  }
  public boolean validarCosto(String dato, JLabel lbl){
      boolean m = Utility.validarDoublePositivo(dato);
      raiseIcon(lbl, "Error, El costo no puede ser negativo y debe ser un numero natural o deminal",m);
      return m;
  }
  public void ocultarIcon(JLabel []icons){
      ocultarLbls(icons);
  }
  public void limpiar(JTextField [] txt, JComboBox vigencia,JComboBox tipoMed, JRadioButton cert){
      for(JTextField t : txt)
          t.setText(null);
      vigencia.setSelectedIndex(0);
      tipoMed.setSelectedIndex(0);
      cert.setSelected(false);
  }
  public void limpiar(JTextField [] txt, JComboBox vigencia, JRadioButton cert){
      for(JTextField t : txt)
          t.setText(null);
      vigencia.setSelectedIndex(0);
      cert.setSelected(false);
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
  public boolean medNecesitaRefrigeracion(int index){
      if (index==-1){
          ventanaError("NO HAY MAS MEDICAMENTOS EN EL INVENTARIO");
          return false;
      }
          
      return lista.getIndice(index) instanceof MedicamentoRefrigerado;}

  public void ocultarOpcTipoMed(JLabel[] lbls, JTextField[] txts, JRadioButton rButton) {
    ocultarLbls(lbls);
    ocultarTxts(txts);
    rButton.setVisible(false);
  }
  public boolean agregarMed(String cod, String nom, String vence, String costo,String lote, String unidDispo, Boolean hayOferta, String oferta, int vigencia, int tipoMed, Boolean certCadFrio, String tempMax, String tempMin, String horasNoRefri, String tiempAbrir, String noAlma){
      if (!Utility.validarCodigo(cod) | !Utility.validarFechaVencimiento(vence)|!Utility.validarNatural(unidDispo)| vigencia ==0| !Utility.validarDoublePositivo(costo) | !Utility.validarNatural(lote)){
          ventanaError("Uno o mas valores no son validos1!");
          return false;
      } 
      if (hayOferta && !Utility.validarDouble(oferta)){
           ventanaError("Oferta seleccionada, pero ningun valor introducido!");
           return false;
       }
       switch(tipoMed){
           case 0 -> ventanaError("No se selecciono el tipo de Medicamento!");
           case 1 -> {
               if(!Utility.validarDouble(tempMax) || !Utility.validarDouble(tempMin)){
                    ventanaError("Uno o mas valores no son validos!3");
                    return false;
                }

                if ((int) Math.round(Double.parseDouble(tempMax)) < (int) Math.round(Double.parseDouble(tempMin))){
                    ventanaError("La temperatura maxima y la temperatura minima son incompatibles!");
                    return false;
                }
               if (!Utility.validarDouble(horasNoRefri) | !Utility.validarDouble(tiempAbrir)){
                   ventanaError("Uno o mas valores no son validos!4");
                    return false;
                   
               }
               lista.aggUltimo(new MedicamentoRefrigerado(cod,0,Integer.parseInt(unidDispo),Integer.parseInt(lote),getVigencia(vigencia),
                       Double.parseDouble(costo),nom,vence,certCadFrio,Double.parseDouble(tempMin),
                       Double.parseDouble(tempMax),Double.parseDouble(tiempAbrir),
                       Double.parseDouble(horasNoRefri)));
               
          }
           case 2 -> {
               if(!Utility.validarAlmacenamiento(noAlma)){
                   ventanaError("Los lugares de no almacenamiento son invalidos2!");
                   return false;
               }
               lista.aggUltimo(new MedicamentoAmbiente(cod,0,Integer.parseInt(unidDispo),Integer.parseInt(lote),getVigencia(vigencia),
                       Double.parseDouble(costo),nom,vence, Utility.getLugares(noAlma)));
                   
          }
       }
       if (hayOferta)
           lista.getUltimo().colocarOferta(Double.parseDouble(oferta));
       ventanaExito("Medicamento Agregado!");
       return true;
   }
  public void escribirTxt(JTextArea dest, String txt){
      dest.setText(dest.getText() + "\n"+txt);
  }
  public int getVigencia(int vigencia){
      switch (vigencia){
          case 1 -> {
              return 1;
          }
          case 2 -> {
              return 0;
          }
          case 3 -> {
              return 2;
          }
      }
      return -1;
  }
  public void realizarCambios(String cod, String nom, String vence, String costo,String lote, String unidDispo, String unidVend,
        Boolean hayOferta, String oferta, int vigencia, Boolean certCadFrio, String tempMax, String tempMin, 
        String horasNoRefri, String tiempAbrir, String noAlma,JTextArea cambios, int index){
      if(index==-1){
          ventanaError("NO HAY MAS MEDICAMENTOS EN EL INVENTARIO");
          return;
      }
      Medicamento selected = lista.getIndice(index);
      
      if (Utility.validarCodigo(cod)){
          selected.setCodigo(cod);
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nCodigo cambiado a "+cod);
      }
      if (!nom.isEmpty()){
          selected.setNombre(nom);
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nNombre cambiado a "+nom);
      }
      if (Utility.validarFechaVencimiento(vence)){
          selected.setFechaVencimiento(vence);
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nCaducidad cambiada a "+vence);
      }
      if (Utility.validarDoublePositivo(costo)){
          selected.setCosto(Double.parseDouble(costo));
          selected.precioAPagar();
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nCosto cambiado a "+costo);
      }
      if (Utility.validarNatural(lote)){
          selected.setLote(Integer.parseInt(lote));
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nLote cambiado a "+lote);
      }
      if (Utility.validarNatural(unidDispo)){
          selected.setUnidDispo(Integer.parseInt(unidDispo));
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nUnidades disponibles cambiadas a "+unidDispo);
      }
      if(Utility.validarNatural(unidVend)){
          selected.setUnidDispo(Integer.parseInt(unidVend));
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nUnidades vendidas cambiadas a "+unidVend);
          
      }
      if (hayOferta && Utility.validarDouble(oferta)){
          selected.colocarOferta(Double.parseDouble(oferta));
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nAplicada la oferta de:  "+oferta+"%");
      }
      if (vigencia != 0){
          selected.setVigencia(getVigencia(vigencia));
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nVigencia cambiada a "+getVigencia(vigencia));
      }
      if (!(selected instanceof MedicamentoRefrigerado)){
          if (Utility.validarAlmacenamiento(noAlma)){
              ((MedicamentoAmbiente)selected).setNoAlmacenar(Utility.getLugares(noAlma));
              escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nLugares de no almacenamiento cambiados");
          }
      }else{
          ((MedicamentoRefrigerado) selected).setCertifCadenaFrio(certCadFrio);
          escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nCertificado cambiada a "+certCadFrio);
          if (Utility.validarDouble(tempMax)){
              ((MedicamentoRefrigerado) selected).setTempMax(Double.parseDouble(tempMax));
              escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nTemp Max cambiada a "+tempMax);
          }
          if (Utility.validarDouble(tempMin)){
              ((MedicamentoRefrigerado) selected).setTempMin(Double.parseDouble(tempMin));
              escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nTemp Min cambiada a "+tempMin);
          }
          if (Utility.validarDoublePositivo(horasNoRefri)){
              ((MedicamentoRefrigerado) selected).setTiempSinRefri(Double.parseDouble(horasNoRefri));
              escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nTiempo sin refrigeracion cambiado a "+horasNoRefri);
          }
          if (Utility.validarDoublePositivo(tiempAbrir)){
              ((MedicamentoRefrigerado) selected).setTiempAbierto(Double.parseDouble(tiempAbrir));
              escribirTxt(cambios,"Medicamento: "+selected.getNombre()+"\nTiempo despues de abrir cambiado a "+tiempAbrir);
          }
      }
      
  }
  public void borrarSeleccionado(int index){
      if (lista.estaVacia()){
          ventanaError("NO HAY MAS MEDICAMENTOS EN EL INVENTARIO");
          return;
      }
      lista.elimIndice(index);
  }
}