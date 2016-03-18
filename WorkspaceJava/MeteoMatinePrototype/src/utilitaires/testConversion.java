package utilitaires;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class testConversion {
	
	float u;
	float v;

	@Before
	public void setUp() throws Exception {
		u=1;
		v=0;
	}

	@Test
	public void testgetForceFromUV() {
		assertTrue(1==ConversionImpl.getForceFromUV(u,v));
		
	}
	
	@Test
	public void testgetAngleFromUV() {
		assertTrue((float)Math.toDegrees(Math.atan2(1, 0))==ConversionImpl.getAngleFromUV(u,v));
	}
	
	@Test
	public void testmsToNoeud(){
		assertTrue(1.94384f == ConversionImpl.msToNoeud(1));
	}
	
	@Test
	public void testnoeudToMS(){
		assertTrue(1 == ConversionImpl.noeudToMS(1.94384f));
	}

}
