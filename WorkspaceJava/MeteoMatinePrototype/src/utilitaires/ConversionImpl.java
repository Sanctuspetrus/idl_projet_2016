package testUtilitaire;

public class ConversionImpl {
	
	public static float getForceFromUV(float u, float v){
		return (float) Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
		
	}
	
	public static float getAngleFromUV(float u, float v){
		return (float) Math.toDegrees(Math.atan2(v, u));
		
	}
	

}
