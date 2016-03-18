package utilitaires;

import static org.junit.Assert.*;

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

}
