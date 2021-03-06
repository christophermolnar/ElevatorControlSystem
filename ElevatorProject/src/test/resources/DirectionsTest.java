package test.resources;

import org.junit.Test;

import resources.Directions;
import static org.junit.Assert.*;

public class DirectionsTest {
	
	@Test
	public void testOppositeUpDown() {
		assertTrue("Wrong Opposite Value", Directions.isOpposite(Directions.UP, Directions.DOWN));
		assertTrue("Wrong Opposite Value", Directions.isOpposite(Directions.DOWN, Directions.UP));
	}
	
	@Test
	public void testOppositeUpUp() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.UP, Directions.UP));
	}
	
	@Test
	public void testOppositeDownDown() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.DOWN, Directions.DOWN));
	}
	
	@Test
	public void testOppositeDownSB() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.STANDBY, Directions.DOWN));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.DOWN, Directions.STANDBY));
	}
	
	@Test
	public void testOppositeUpSB() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.STANDBY, Directions.UP));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.UP, Directions.STANDBY));
	}
	
	@Test
	public void testOppositeSBSB() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.STANDBY, Directions.STANDBY));
	}
	
	@Test
	public void testOppositeUpErrDf() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.UP, Directions.ERROR_DEFAULT));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.ERROR_DEFAULT, Directions.UP));
	}
	
	@Test
	public void testOppositeUpErrDo() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.UP, Directions.ERROR_SOFT));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.ERROR_SOFT, Directions.UP));
	}
	
	@Test
	public void testOppositeUpErrMv() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.UP, Directions.ERROR_HARD));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.ERROR_HARD, Directions.UP));
	}
	
	@Test
	public void testOppositeDownErrDf() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.DOWN, Directions.ERROR_DEFAULT));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.ERROR_DEFAULT, Directions.DOWN));
	}
	
	@Test
	public void testOppositeDownErrDo() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.DOWN, Directions.ERROR_SOFT));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.ERROR_SOFT, Directions.DOWN));
	}
	
	@Test
	public void testOppositeDownErrMv() {
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.DOWN, Directions.ERROR_HARD));
		assertFalse("Wrong Opposite Value", Directions.isOpposite(Directions.ERROR_HARD, Directions.DOWN));
	}
	
	@Test
	public void testErrorUp() {
		assertFalse("Wrong Error Value", Directions.isInError(Directions.UP));
	}
	
	@Test
	public void testErrorDown() {
		assertFalse("Wrong Error Value", Directions.isInError(Directions.DOWN));
	}
	
	@Test
	public void testErrorSB() {
		assertFalse("Wrong Error Value", Directions.isInError(Directions.STANDBY));
	}
	
	@Test
	public void testErrorDoorErr() {
		assertTrue("Wrong Error Value", Directions.isInError(Directions.ERROR_SOFT));
	}

	public void testErrorDefaultErr() {
		assertTrue("Wrong Error Value", Directions.isInError(Directions.ERROR_DEFAULT));
	}
	
	@Test
	public void testErrorMoveErr() {
		assertTrue("Wrong Error Value", Directions.isInError(Directions.ERROR_HARD));
	}
	
	@Test
	public void testZeroReturnsDown() {
		assertEquals("Wrong Direction Value", Directions.DOWN, Directions.getDirByInt(0));
	}
	
	@Test
	public void testOneReturnsUp() {
		assertEquals("Wrong Direction Value", Directions.UP, Directions.getDirByInt(1));
	}
	
	@Test
	public void testTwoReturnsSB() {
		assertEquals("Wrong Direction Value", Directions.STANDBY, Directions.getDirByInt(2));
	}
	
	@Test
	public void testDownReturnsZero() {
		assertEquals("Wrong Integer Value", 0, Directions.getIntByDir(Directions.DOWN));
	}
	
	@Test
	public void testUpReturnsOne() {
		assertEquals("Wrong Integer Value", 1, Directions.getIntByDir(Directions.UP));
	}
	
	@Test
	public void testSBReturnsTwo() {
		assertEquals("Wrong Integer Value", 2, Directions.getIntByDir(Directions.STANDBY));
	}
	
	
	@Test
	public void testOtherValsReturnNegOne() {
		assertEquals("Wrong Integer Value", -1, Directions.getIntByDir(null));
	}
	
}
