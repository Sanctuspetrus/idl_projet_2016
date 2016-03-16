package lecteur_Grib;

public class Point {

	double temps;
	float longitude;
	float latitude;
	float pression;
	float ventU;
	float ventV;
	
	
	public double getTemps() {
		return temps;
	}
	public void setTemps(double temps) {
		this.temps = temps;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getPression() {
		return pression;
	}
	public void setPression(float pression) {
		this.pression = pression;
	}
	public float getVentU() {
		return ventU;
	}
	public void setVentU(float ventU) {
		this.ventU = ventU;
	}
	public float getVentV() {
		return ventV;
	}
	public void setVentV(float ventV) {
		this.ventV = ventV;
	}
}
