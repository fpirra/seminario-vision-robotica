package geometria.cuaterniones;

import geometria.Rotacion;
import geometria.Vector;
import geometria.esferica.CoordenadaEsferica;
import geometria.esferica.RotacionEsferica;

public class Cuaternion implements Rotacion, Vector {
	private double x;
	private double y;
	private double z;
	private double angulo;

	public Cuaternion(double phi, double lambda,double theta) {
		this.x = Math.sin(theta/2)*Math.sin(phi)*Math.cos(lambda);
		this.y = Math.sin(theta/2)*Math.sin(phi)*Math.sin(lambda);;
		this.z = Math.sin(theta/2)*Math.cos(phi);
		this.angulo = Math.cos(theta/2);
	}


	public Cuaternion(double phi, double lambda) {
		this.x = Math.sin(phi)*Math.cos(lambda);
		this.y = Math.sin(phi)*Math.sin(lambda);;
		this.z = Math.cos(phi);
		this.angulo = 0.0;
	}
	
	public Cuaternion(double x,double y,double z,double angulo) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angulo = angulo;
	}


	public CoordenadaEsferica aCoordenadaEsferica() {
		double modulo = getModulo();
		double phi, lambda;

		double atanSqrtx2y2divZ = Math.atan(Math.sqrt(Math.pow(x, 2)
				+ Math.pow(y, 2))
				/ z);
		if (z > 0) {
			phi = atanSqrtx2y2divZ;
		} else if (z < 0) {
			phi = Math.PI + atanSqrtx2y2divZ;
		} else {
			phi = Math.PI / 2;
		}
		double atanYdivX = Math.atan(y / x);
		if (x > 0) {
			if (y > 0) {
				lambda = atanYdivX;
			} else {
				lambda = 2 * Math.PI + atanYdivX;
			}
		} else if (x < 0) {
			lambda = Math.PI + atanYdivX;
		} else {
			lambda = Math.PI / 2 * Math.signum(y);
		}

		CoordenadaEsferica retorno = new CoordenadaEsferica(lambda, phi, modulo);

		// String inspeccion=this.toString();
		// System.out.println(inspeccion);
		// String inspeccion = retorno.toString();
		// System.out.println(inspeccion);

		return retorno;
	}

	public double getAngulo() {
		return angulo;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public Vector rotar(Vector v) {
		Cuaternion cuaternion = null;
		if (v instanceof Cuaternion) {
			cuaternion = (Cuaternion) v;
		}
		if (v instanceof CoordenadaEsferica) {
			cuaternion = ((CoordenadaEsferica) v).aCuaternion();
			// String inspeccion = retorno.toString();
			// System.out.println(inspeccion);
		}

		return rotar(cuaternion);

	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public static Vector rotar(Rotacion rotacion, Vector vector) {
		Cuaternion cuaternion = null;
		if (rotacion instanceof Cuaternion) {
			cuaternion = (Cuaternion) rotacion;
			return cuaternion.rotar(vector);
		}
		if (rotacion instanceof RotacionEsferica) {
			cuaternion = ((RotacionEsferica) rotacion).aCuaternion();
			CoordenadaEsferica ce=cuaternion.rotar(vector).aCoordenadaEsferica();
		//	String inspeccion= ce.toString();
//			System.out.println(inspeccion);
			return ce;
		}

		return null;

	}

	public Cuaternion conjugado() {
		return new Cuaternion(-x, -y, -z, angulo);
	}

	public Cuaternion suma(Cuaternion c) {
		return new Cuaternion(x + c.x, y + c.y, z + c.z, angulo + c.angulo);
	}

	public Cuaternion mult(Cuaternion otro) {

		double angulo = this.angulo * otro.angulo - this.x * otro.x - this.y
				* otro.y - this.z * otro.z;
		double x = this.y * otro.z - this.z * otro.y + this.angulo * otro.x
				+ otro.angulo * this.x;
		double y = -this.x * otro.z + otro.x * this.z + this.angulo * otro.y
				+ otro.angulo * this.y;
		double z = this.x * otro.y - otro.x * this.y + this.angulo * otro.z
				+ otro.angulo * this.z;
		return new Cuaternion(x, y, z, angulo);

	}

	public Cuaternion multFactor(double escalar) {
		return new Cuaternion(x * escalar, y * escalar, z * escalar, angulo
				* escalar);
	}

	public Cuaternion inverso() {
		double d = angulo * angulo + x * x + y * y + z * z;
		return new Cuaternion(-x / d, -y / d, -z / d, angulo / d);
	}

	public Cuaternion divide(Cuaternion b) {
		Cuaternion a = this;
		return a.inverso().mult(b);
	}

	public double getModulo() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}

	public RotacionEsferica aRotacionEsferica() {
		return new RotacionEsferica(aCoordenadaEsferica(), angulo);
	}

	public String toString() {
		return "x: " + x + " y: " + y + " z: " + z + " angulo: " + angulo
				+ " Modulo: " + getModulo();
	}

	public Cuaternion aCuaternion() {
		return new Cuaternion(x, y, z, angulo);
	}

	public Cuaternion multVectorial(Cuaternion c) {
		double nx = y * c.z - z * c.y;

		double ny = z * c.x - x * c.z;

		double nz = x * c.y - y * c.x;

		return new Cuaternion(nx, ny, nz);
	}

	public double multEscalar(Cuaternion c) {
		return (x * c.x) + (y * c.y) + (z * c.z);
	}
	
	public void sacarvalores(Cuaternion p){
		Cuaternion qconj= this.conjugado();
		Cuaternion temp = this.mult(p);
		Cuaternion Prot = temp.mult(qconj);
		Double phiPprim = Math.atan((Math.sqrt(Math.pow(Prot.getX(), 2)+ Math.pow(Prot.getY(), 2)))/Prot.getZ());
		Double lambdaPprim = Math.atan(Prot.getY()/Prot.getX());
	}
}
