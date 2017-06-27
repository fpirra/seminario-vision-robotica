package geometria.esferica;

import geometria.Vector;
import geometria.cuaterniones.Cuaternion;

public class CoordenadaEsferica implements Vector
{
	private static final String	ESFERICAS1		= "esfericas ARCOSENO";
	private static final String	ESFERICAS2		= "esfericas raiz";
	private static final String	OPCION1		= "opcion ARCSEN";
	private static final String	OPCION2		= "opcion SENOS Y COSENOS";
	
	private double	lambda, phi, radio;

	public CoordenadaEsferica(double lambda, double phi)
	{
		this.lambda = lambda;
		this.phi = phi;
		this.radio=1;
	}

	public CoordenadaEsferica(double lambda, double phi, double radio)
	{
		this.lambda = lambda;
		this.phi = phi;
		this.radio=radio;
	}

	public Cuaternion aCuaternion()
	{
	    double senoPhi=Math.sin(phi);
		double x = radio*senoPhi*Math.cos(lambda); 
	    double y = radio*senoPhi*Math.sin(lambda); 
	    double z = radio*senoPhi; 
	    
	    return new Cuaternion(x, y, z);

		// Cuaternion retorno=new Cuaternion(x, y, z);
		// String inspeccion=this.toString();
		// // System.out.println(inspeccion);
		// String inspeccion=retorno.toString();
		// System.out.println(inspeccion);
		// return retorno ;
	}

	public double getLambda()
	{
		return lambda;
	}

	public double getPhi()
	{
		return phi;
	}

	public double getRadio()
	{
		return radio;
	}

	public void setLambda(double lambda)
	{
		this.lambda = lambda;
	}

	public void setPhi(double phi)
	{
		this.phi = phi;
	}

	public void setRadio(double radio)
	{
		this.radio = radio;
	}
	@Override
	public String toString()
	{
		return "phi: "+phi+" lambda: "+ lambda+" radio: "+radio;
	}

	public CoordenadaEsferica aCoordenadaEsferica()
	{
		return new CoordenadaEsferica(lambda, phi);
	}

	public Vector rotar(Vector v) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void sacarvalores(Double theta, CoordenadaEsferica esfericaP,String metodo, String opcion){
	
			Double lambdaP = esfericaP.getLambda();
			Double phiP = esfericaP.getPhi();
			
			Double AP = Math.acos(Math.cos(this.phi)*Math.cos(phiP) + Math.sin(this.phi)*Math.sin(phiP)* Math.cos(lambdaP - this.lambda));
			Double ZAP = 0.0;
			if (metodo.equals(ESFERICAS1)){
				
				ZAP = Math.asin((Math.sin(lambdaP - this.lambda)*Math.sin(lambdaP)) / Math.sin(AP));
			}
			
			else if (metodo.equals(ESFERICAS2)){
				
				ZAP = Math.asin((Math.sin(lambdaP - this.lambda)*Math.sin(lambdaP)) / Math.sqrt((1-(Math.pow(Math.cos(AP), 2)))));
				
			}
			
			Double ZAPprim = 0.0;
			Double SENZAPprim = 0.0;
			Double COSZAPprim = 0.0;
			
			if(opcion.equals(OPCION1)){
				
				ZAPprim = ZAP + theta;
				COSZAPprim = Math.cos(ZAPprim);
				SENZAPprim = Math.sin(ZAPprim);
				
			}
			
			else if(opcion.equals(OPCION2)){
				
				COSZAPprim = Math.cos(ZAP) * Math.cos(theta) - Math.sin(ZAP) * Math.sin(theta);
				SENZAPprim = Math.sin(ZAP) * Math.cos(theta) + Math.cos(ZAP) * Math.sin(theta);
			}
			
			Double phiPprim = Math.acos(Math.cos(this.phi)*Math.cos(AP) + Math.sin(this.phi)*Math.sin(AP)*COSZAPprim);
			
			Double PprimZA = Math.asin((SENZAPprim * Math.sin(AP)) / Math.sin(phiPprim));
			
			Double lambdaPprim = this.lambda - PprimZA;
	}
}
