package com.rodri.practica2_SCB;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rodrigo Alavarez Echarri.
 * Date: 03/10/12
 * Time: 10:39 AM
 * 
 */
public class Pantalla extends JFrame{

    private Container cont;
    private JLabel lblCriterio;
    private JTextField txtCriterio;

    private JLabel lblDirectorio;
    private JTextField txtDirectorio;
    private JButton cmdExaminar;
    private JFileChooser directorio;

    private JLabel lblTipoArchivo;
    private JTextField txtTipoArchivo;

    private JButton cmdBuscar;
    private JButton cmdSalir;

    private JTextArea resultados;
    private BuscaFicheros buscarCadenas;

    public Pantalla(){
        super("Busca en Archivos by Rodrigo");
        cont = getContentPane();
        cont.setLayout(null);
        generaGUI();
        setSize(new Dimension(470,355));
        setVisible(true);
    }

    private void generaGUI() {
        lblCriterio = new JLabel("Criterio de busqueda:");
        lblCriterio.setBounds(10,10,150,20);
        cont.add(lblCriterio);

        txtCriterio = new JTextField();
        txtCriterio.setBounds(170,10,150,20);
        cont.add(txtCriterio);

        lblDirectorio = new JLabel("Buscar en:");
        lblDirectorio.setBounds(10,35,150,20);
        cont.add(lblDirectorio);

        txtDirectorio = new JTextField();
        txtDirectorio.setBounds(170,35,100,20);
        cont.add(txtDirectorio);

        cmdExaminar = new JButton("...");
        cmdExaminar.setBounds(280,35,40,20);
        cont.add(cmdExaminar);

        lblTipoArchivo = new JLabel("Tipo Archivo");
        lblTipoArchivo.setBounds(10,60,150,20);
        cont.add(lblTipoArchivo);

        txtTipoArchivo = new JTextField();
        txtTipoArchivo.setBounds(170,60,150,20);
        cont.add(txtTipoArchivo);

        resultados = new JTextArea();
        resultados.setBounds(10,85,305,200);
        cont.add(resultados);

        cmdBuscar = new JButton("Buscar");
        cmdBuscar.setBounds(350,10,100,20);
        cont.add(cmdBuscar);

        
        
        
        
        
        
        
        
        cmdSalir = new JButton("Salir");
        cmdSalir.setBounds(350,35,100,20);
        cont.add(cmdSalir);

        cmdSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        cmdExaminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                directorio = new JFileChooser();
                directorio.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                directorio.showOpenDialog(null);

                txtDirectorio.setText("" + directorio.getSelectedFile());
            }
        });
        
        
        cmdBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCadenas = new BuscaFicheros(txtCriterio.getText(),txtDirectorio.getText(),txtTipoArchivo.getText());
                buscarCadenas.SearchName();
				resultados.setText(buscarCadenas.leer("salida.txt"));
            }
        });


    }

    public static void main(String arg[]){
       new Pantalla();
    }

}