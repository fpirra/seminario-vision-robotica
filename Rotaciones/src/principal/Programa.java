package principal;

import javax.swing.JFrame;

import util.RotacionUtil;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Programa extends JFrame{

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private JLabel cantidad, esfarcsencos, esfarcarc, esfraizarc, esfraizsencos, cuatcuat;


        private JTextField cantText, esfArcSenCosText, esfRaizSenCosText, esfArcArcText, esfRaizArcText, cuarterText;

        private JButton calculateButton, exitButton;

 

        private static final int HEIGHT = 500;

        private static final int WIDTH = 600;

       

        private CalculateButtonHandler calculateButtonHandler;

        private ExitButtonHandler exitButtonHandler;

       

        public Programa()

        {
                    cantidad = new JLabel("Cantidad de datos a procesar: ",SwingConstants.RIGHT);

                    esfarcsencos = new JLabel("Rot. esfericas, Arc-SenCos: ", SwingConstants.RIGHT);
                    esfarcarc = new JLabel("Rot. esfericas, Arc-Arc:", SwingConstants.RIGHT);
                    esfraizsencos = new JLabel("Rot. esfericas, Raiz-SenCos: ", SwingConstants.RIGHT);
                    esfraizarc = new JLabel("Rot. esfericas, Raiz-Arc: ", SwingConstants.RIGHT);
                    cuatcuat = new JLabel("Rot. cuaterniones: ", SwingConstants.RIGHT);
                   

                    cantText = new JTextField(12);

                    esfArcSenCosText = new JTextField(12);
                    esfRaizSenCosText = new JTextField(12);
                    esfArcArcText = new JTextField(12);
                    esfRaizArcText = new JTextField(12);
                    cuarterText = new JTextField(12);

                   

                    //Esta secci�n espec�fica los controladores para los botones y a�ade un ActionListener.

                    calculateButton = new JButton("Calcular Rotaciones");

                    calculateButtonHandler = new CalculateButtonHandler();

                    calculateButton.addActionListener(calculateButtonHandler);

                    exitButton = new JButton("Cerrar");

                    exitButtonHandler = new ExitButtonHandler();

                    exitButton.addActionListener(exitButtonHandler);

                   

                    setTitle("Comparador de Rotaciones - Tp Final, materia 67.61 ");

                    Container pane = getContentPane();

                    pane.setLayout(new GridLayout(7, 2));

                   

                    // El dise�o de Grid requiere que agregue componentes al panel de contenido, en el orden en que deben aparecer

                    pane.add(cantidad);
                    pane.add(cantText);

                    pane.add(esfarcsencos);
                    pane.add(esfArcSenCosText);
                    
                    pane.add(esfarcarc);
                    pane.add(esfArcArcText);
                    
                    pane.add(esfraizsencos);
                    pane.add(esfRaizSenCosText);
                    
                    pane.add(esfraizarc);
                    pane.add(esfRaizArcText);

                    pane.add(cuatcuat);
                    pane.add(cuarterText);


                    pane.add(calculateButton);

                    pane.add(exitButton);

                   

                    setSize(WIDTH,HEIGHT);

                    setVisible(true);

                    setDefaultCloseOperation(EXIT_ON_CLOSE);

        }

       

        private class CalculateButtonHandler implements ActionListener

        {

                    public void actionPerformed(ActionEvent e)

                    {

                    			double[][] tiempo = new double[2][2];
                    			int cantidadComparaciones = Integer.parseInt(cantText.getText());
                    			tiempo = RotacionUtil.iniciarComparacion(cantidadComparaciones);
                    	                        
                    			esfArcSenCosText.setText(""+tiempo[0][1]+" milisegundos");
                    			esfArcArcText.setText(""+tiempo[0][0]+" milisegundos");
                                esfRaizSenCosText.setText(""+tiempo[1][1]+" milisegundos");
                                esfRaizArcText.setText(""+tiempo[1][0]+" milisegundos");
                                cuarterText.setText(""+tiempo[2][0]+" milisegundos");

                    }

        }

       

        public class ExitButtonHandler implements ActionListener

        {

                    public void actionPerformed(ActionEvent e)

                    {

                                System.exit(0);

                    }

        }
	
	
	
	public static void main(String[] args){
	
		Programa prog = new Programa();
	
	}
}
