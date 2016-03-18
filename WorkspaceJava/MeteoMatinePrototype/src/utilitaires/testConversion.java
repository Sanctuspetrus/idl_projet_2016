package utilitaires;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class testConversion {
	
	float u;
	float v;

	@Before
	public void setUp() throws Exception {
		u=3;
		v=-4;
	}

	@Test
	public void testgetForceFromUV() {
		assertTrue(5==ConversionImpl.getForceFromUV(u,v));
		
	}
	
	@Test
	public void testgetAngleFromUV() {
		assertTrue((float)Math.toDegrees(Math.atan2(-4, 3))==ConversionImpl.getAngleFromUV(u,v));
	}
	
	@Test
	public void testmsToNoeud(float v){
		assertTrue(1.94384f == ConversionImpl.msToNoeud(1));
	}
	
	@Test
	public void testnoeudToMS(float v){
		assertTrue(1 == ConversionImpl.msToNoeud(1.94384f));
	}

}
