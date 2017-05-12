package principal;

import util.RotacionUtil;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Programa extends JFrame{

        private JLabel cantidad, cuatcuat, cuatesf, esfesf, esfcuat;

        private JTextField cantText, ccText, ceText, ecText, eeText;

        private JButton calculateButton, exitButton;

 

        private static final int HEIGHT = 500;

        private static final int WIDTH = 600;

       

        private CalculateButtonHandler calculateButtonHandler;

        private ExitButtonHandler exitButtonHandler;

       

        public Programa()

        {
        			// Este programa procesa tantas rotaciones, como las que sean solicitadas, generando set de datos
        			// de forma aleatoria en cada nueva rotacion.
        			// Para el objetivo buscado, el programa utiliza set de datos en esfericas y quaterniones, para luego
        			// procesarlos ya sea utilizando rotaciones esfericas o por cuaterniones... de forma que queden abarcados
        			// los cuatro casos de interes.
        	
        	

                    cantidad = new JLabel("Ingrese la cantidad de datos que desea procesar: ",SwingConstants.RIGHT);

                    cuatcuat = new JLabel("Rotacion con cuaterniones, datos cuaterniones: ", SwingConstants.RIGHT);
                    cuatesf = new JLabel("Rotacion con cuaterniones, datos esfericas: ", SwingConstants.RIGHT);
                    esfcuat = new JLabel("Rotacion con esfericas, datos cuaterniones: ", SwingConstants.RIGHT);
                    esfesf = new JLabel("Rotacion con esfericas, datos esfericas: ", SwingConstants.RIGHT);

                   

                    cantText = new JTextField(12);

                    ccText = new JTextField(12);
                    ceText = new JTextField(12);
                    ecText = new JTextField(12);
                    eeText = new JTextField(12);

                   

                    //Esta secci�n espec�fica los controladores para los botones y a�ade un ActionListener.

                    calculateButton = new JButton("Calcular Rotaciones");

                    calculateButtonHandler = new CalculateButtonHandler();

                    calculateButton.addActionListener(calculateButtonHandler);

                    exitButton = new JButton("Cerrar");

                    exitButtonHandler = new ExitButtonHandler();

                    exitButton.addActionListener(exitButtonHandler);

                   

                    setTitle("Comparador de Rotaciones - Tp Final, materia 67.61 ");

                    Container pane = getContentPane();

                    pane.setLayout(new GridLayout(6, 2));

                   

                    // El dise�o de Grid requiere que agregue componentes al panel de contenido, en el orden en que deben aparecer

                    pane.add(cantidad);

                    pane.add(cantText);

                    pane.add(cuatcuat);

                    pane.add(ccText);
                    
                    pane.add(cuatesf);

                    pane.add(ceText);
                    
                    pane.add(esfcuat);

                    pane.add(ecText);
                    
                    pane.add(esfesf);

                    pane.add(eeText);

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
                    	                        
                    			ccText.setText(""+tiempo[0][0]+" milisegundos");
                    			ceText.setText(""+tiempo[0][1]+" milisegundos");
                    			ecText.setText(""+tiempo[1][0]+" milisegundos");
                    			eeText.setText(""+tiempo[1][1]+" milisegundos");

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
		/*double[][] tiempo = new double[2][2];
		
		System.out.println("Introduzca la cantidad de datos que desea procesar: ");
		Scanner sc = new Scanner(System.in);
		
		try{
			int cantidadComparaciones = sc.nextInt();
			tiempo = RotacionUtil.iniciarComparacion(cantidadComparaciones);
			System.out.println("Rotacion con cuaterniones, datos cuaterniones: "+tiempo[0][0]+" milisegundos");
			System.out.println("Rotacion con cuaterniones, datos esfericas: "+tiempo[0][1]+" milisegundos");
			System.out.println("Rotacion esfericas, datos cuaterniones: "+tiempo[1][0]+" milisegundos");
			System.out.println("Rotacion esfericas, datos esfericas: "+tiempo[1][1]+" milisegundos");
		}
		catch(Exception e){
			System.out.println("El dato ingresado no es un numero entero");
		}
	
		sc.close();*/
	
	}
}
