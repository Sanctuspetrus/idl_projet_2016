package testUtilitaire;

public class ConversionImpl {
	
	public static double getForceFromUV(double u, double v){
		return Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
		
	}
	
	public static double getAngleFromUV(double u, double v){
		return Math.toDegrees(Math.atan2(v, u));
		
	}
	

}
