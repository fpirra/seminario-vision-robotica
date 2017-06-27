package util;

import geometria.Rotacion;
import geometria.Vector;
import geometria.cuaterniones.Cuaternion;
import geometria.esferica.CoordenadaEsferica;
import geometria.esferica.RotacionEsferica;

import java.util.ArrayList;
import java.util.List;

public class RotacionUtil
{

	private static final String	ESFERICAS1		= "esfericas ARCOSENO";
	private static final String	ESFERICAS2		= "esfericas raiz";
	private static final String	CUATERNIONES	= "cuaterniones";

	private static final String	OPCION1		= "opcion ARCSEN";
	private static final String	OPCION2		= "opcion SENOS Y COSENOS";
	
	public static double[][] iniciarComparacion(int i)
	{
		final double[][] tiempos = new double[3][2];

		try
		{
			final List<CoordenadaEsferica> tablaEsfericasA = new ArrayList<CoordenadaEsferica>(i);
			final List<CoordenadaEsferica> tablaEsfericasP = new ArrayList<CoordenadaEsferica>(i);
			final List<Double> tablathetas = new ArrayList<Double>(i);
			llenarTablas(tablathetas,tablaEsfericasA, tablaEsfericasP, i);
		

			long tiempo = System.currentTimeMillis();

			// rotaciones cuaterniones datos esfericas
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericasA, tablaEsfericasP,tablathetas, ESFERICAS1,OPCION1);
			tiempos[0][0] = System.currentTimeMillis() - tiempo;
			
			// rotaciones cuaterniones datos esfericas
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericasA, tablaEsfericasP,tablathetas, ESFERICAS1,OPCION2);
			tiempos[0][1] = System.currentTimeMillis() - tiempo;
		//	System.out.println("Rotacion concuaterniones, datos esfericas: "+tiempos[0][1]);

			// rotaciones esfericas esfericas ARCOSENO
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericasA, tablaEsfericasP,tablathetas, ESFERICAS2,OPCION1);
			tiempos[1][0] = System.currentTimeMillis() - tiempo;
	//		System.out.println("Rotacion esfericas, datos cuaterniones: "+tiempos[1][0]);

			// rotaciones esfericas datos esfericas raiz
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericasA, tablaEsfericasP,tablathetas, ESFERICAS2,OPCION2);
			tiempos[1][1] = System.currentTimeMillis() - tiempo;
		//	System.out.println("Rotacion esfericas, datos esfericas: "+tiempos[1][1]);

			// rotaciones esfericas datos esfericas raiz
			tiempo = System.currentTimeMillis();
			rotarLista(tablaEsfericasA, tablaEsfericasP,tablathetas, CUATERNIONES, OPCION1);
			tiempos[2][0] = System.currentTimeMillis() - tiempo;
			//	System.out.println("Rotacion esfericas, datos esfericas: "+tiempos[1][1]);
		}
		catch (final Exception e)
		{
			System.out.print(e.getLocalizedMessage());
		}
		return tiempos;

	}

	private static void llenarTablas(List<Double> tablathetas,List<CoordenadaEsferica> tablaEsfericasA, List<CoordenadaEsferica> tablaEsfericasP, int cantidad)
	{

		for (int i = 0; i < cantidad; i++)
		{
			final CoordenadaEsferica coordenadaEsfericaA = new CoordenadaEsferica(Math.random()*20+35, Math.random() * 5+35);
			final CoordenadaEsferica coordenadaEsfericaP = new CoordenadaEsferica(Math.random()*10+55, Math.random() * 5+60);
            final Double theta = Math.random()*5+80; 

			tablaEsfericasA.add(coordenadaEsfericaA);
			tablaEsfericasP.add(coordenadaEsfericaP);
			tablathetas.add(theta);
		}
	}
	
	
	private static void rotarLista(List<CoordenadaEsferica> tablaA, List<CoordenadaEsferica> tablaP, List<Double> tablaT , String metodo,String opcion)
	{
//		System.out.println("Modo : "+metodo);

		for (int i=0;i<tablaA.size();i++)
		{
			final CoordenadaEsferica esfericaA = tablaA.get(i);
			final CoordenadaEsferica esfericaP = tablaP.get(i);
			final Double theta = tablaT.get(i);
						if (metodo.equals(CUATERNIONES)){
							Cuaternion q = new Cuaternion(esfericaA.getPhi(),esfericaA.getLambda(),theta);
							Cuaternion p = new Cuaternion(esfericaA.getPhi(),esfericaA.getLambda());
							q.sacarvalores(p);
						}
						else esfericaA.sacarvalores(theta, esfericaP,metodo,opcion);
		}

	}
}
