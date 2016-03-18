package utilitaires;

public class Vent {

	float u;
	float v;
	float pression;
	
	public Vent(float u, float v, float pression) {
		super();
		this.u = u;
		this.v = v;
		this.pression = pression;
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
