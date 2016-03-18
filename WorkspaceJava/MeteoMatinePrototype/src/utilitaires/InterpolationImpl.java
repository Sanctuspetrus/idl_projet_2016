package utilitaires;

public class InterpolationImpl {

	public static Vent interpolationCarre(Vent A,Vent B,Vent C,Vent D, float lonX, float latX){
		float uXFinal, vXFinal, pressionFinal;
		float uXTMP1, vXTMP1, pressionTMP1;
		float uXTMP2, vXTMP2, pressionTMP2;
		System.out.println("						Util : InterpolationCarre");
		
		uXTMP1 = interpolationLigne(A.getdEst(), B.getdEst(), lonX, A.getU(), B.getU());
		uXTMP2 = interpolationLigne(C.getdEst(), D.getdEst(), lonX, C.getU(), D.getU());
		uXFinal = interpolationLigne(A.getdNord(), C.getdNord(), latX, uXTMP1, uXTMP2);
		
		vXTMP1 = interpolationLigne(A.getdEst(), B.getdEst(), lonX, A.getV(), B.getV());
		vXTMP2 = interpolationLigne(C.getdEst(), D.getdEst(), lonX, C.getV(), D.getV());
		vXFinal = interpolationLigne(A.getdNord(), C.getdNord(), latX, vXTMP1, vXTMP2);
		
		pressionTMP1 = interpolationLigne(A.getdEst(), B.getdEst(), lonX, A.getPression(), B.getPression());
		pressionTMP2 = interpolationLigne(C.getdEst(), D.getdEst(), lonX, C.getPression(), D.getPression());
		pressionFinal = interpolationLigne(A.getdNord(), C.getdNord(), latX, pressionTMP1, pressionTMP2);
		

		
		
		return new Vent(latX, lonX, uXFinal, vXFinal, pressionFinal);
		
	}
	
	public static float interpolationLigne(float posA, float posB, float posX, float valA, float valB){
		float coeficient = (posX-posA)/(posB-posA);
		float resultat = (valA*(1-coeficient))+(valB*coeficient);
		System.out.println("						Util : InterpolationLigne" + resultat);
		return resultat;
	}
	
	public static void main(String args[]){
		
		System.out.println(interpolationLigne(0,1,0.5f,0,1));
		System.out.println(interpolationLigne(0,1,0.1f,0,2));
		System.out.println(interpolationLigne(0,1,0.1f,2,0));
		System.out.println(interpolationLigne(0,1,0.5f,1,1));
		System.out.println(interpolationLigne(0,1,0.5f,-1,1));
		
		Vent A = new Vent(8,4, 1, 0, 1013000);
		Vent B = new Vent(8,8, 0, 1, 1009000);
		Vent C = new Vent(4,8,0, 1, 1008000);
		Vent D = new Vent(4,4,1, 0, 1014000);
		Vent X = interpolationCarre(A, B, C, D, 5, 5);
		Vent y = interpolationCarre(A, B, C, D, 7, 7);
		Vent z = interpolationCarre(A, B, C, D, 7, 5);
	}
	
}
