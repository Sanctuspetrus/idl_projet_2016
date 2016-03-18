package utilitaires;

public class Vent {

	float dNord;
	float dEst;
	float u;
	float v;
	float pression;
	
	public Vent(float dNord, float dEst, float u, float v, float pression) {
		super();
		this.dNord = dNord;
		this.dEst = dEst;
		this.u = u;
		this.v = v;
		this.pression = pression;
	}

	public float getdNord() {
		return dNord;
	}

	public void setdNord(float dNord) {
		this.dNord = dNord;
	}

	public float getdEst() {
		return dEst;
	}

	public void setdEst(float dEst) {
		this.dEst = dEst;
	}

	public float getU() {
		return u;
	}

	public void setU(float u) {
		this.u = u;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public float getPression() {
		return pression;
	}

	public void setPression(float pression) {
		this.pression = pression;
	}
	
	
	
}
