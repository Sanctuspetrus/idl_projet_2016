package utilitaires;

public class ConversionImpl {
	
	public static float getForceFromUV(float u, float v){
		return (float) Math.sqrt(Math.pow(u, 2)+Math.pow(v, 2));
		
	}
	
	public static float getAngleFromUV(float u, float v){
		return (float) Math.toDegrees(Math.atan2(u,v));
		
	}
	
	public static float msToNoeud(float v){
		return v*(1.94384f);
	}
	
	public static float noeudToMS(float v){
		return v/(1.94384f);
	}
	

}
