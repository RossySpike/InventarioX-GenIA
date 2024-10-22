/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package inventariox.genia.vista;

import inventariox.genia.ListaMedicamentos;
import inventariox.genia.controladora.Controladora;

/**
 *
 * @author honey
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    Controladora c;
    public PantallaPrincipal(ListaMedicamentos lista) {
        c = new Controladora(lista);
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPantPrincipal = new javax.swing.JPanel();
        labelBienvenido = new javax.swing.JLabel();
        labelSelecOpcion = new javax.swing.JLabel();
        labelEntCliente = new javax.swing.JButton();
        labelEntEmpleado = new javax.swing.JButton();
        labelSalir = new javax.swing.JButton();
        labelIcono = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelBienvenido.setFont(new java.awt.Font("Source Code Pro", 0, 48)); // NOI18N
        labelBienvenido.setText("Bienvenido!");

        labelSelecOpcion.setFont(new java.awt.Font("Source Code Pro", 0, 24)); // NOI18N
        labelSelecOpcion.setText("Seleccione una opcion");

        labelEntCliente.setText("Entrar como cliente");
        labelEntCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelEntClienteActionPerformed(evt);
            }
        });

        labelEntEmpleado.setText("Entrar como empleado");
        labelEntEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelEntEmpleadoActionPerformed(evt);
            }
        });

        labelSalir.setText("Salir :c");
        labelSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelSalirActionPerformed(evt);
            }
        });

        labelIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventariox/genia/vista/pill.png"))); // NOI18N

        javax.swing.GroupLayout panelPantPrincipalLayout = new javax.swing.GroupLayout(panelPantPrincipal);
        panelPantPrincipal.setLayout(panelPantPrincipalLayout);
        panelPantPrincipalLayout.setHorizontalGroup(
            panelPantPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPantPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPantPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSelecOpcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelPantPrincipalLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(panelPantPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEntEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelEntCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelIcono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(45, 45, 45))
            .addGroup(panelPantPrincipalLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(labelBienvenido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPantPrincipalLayout.setVerticalGroup(
            panelPantPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPantPrincipalLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(labelBienvenido, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addGroup(panelPantPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPantPrincipalLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(labelSelecOpcion, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(labelEntCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(labelEntEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(panelPantPrincipalLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelIcono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPantPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPantPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelEntClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelEntClienteActionPerformed
        // TODO add your handling code here:
        c.iniciarPantallaCliente(new PantallaCliente(c.getLista()));
        dispose();
    }//GEN-LAST:event_labelEntClienteActionPerformed

    private void labelEntEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelEntEmpleadoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_labelEntEmpleadoActionPerformed

    private void labelSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelSalirActionPerformed
        // TODO add your handling code here:
        c.cerrarPrograma();
    }//GEN-LAST:event_labelSalirActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelBienvenido;
    private javax.swing.JButton labelEntCliente;
    private javax.swing.JButton labelEntEmpleado;
    private javax.swing.JLabel labelIcono;
    private javax.swing.JButton labelSalir;
    private javax.swing.JLabel labelSelecOpcion;
    private javax.swing.JPanel panelPantPrincipal;
    // End of variables declaration//GEN-END:variables
}