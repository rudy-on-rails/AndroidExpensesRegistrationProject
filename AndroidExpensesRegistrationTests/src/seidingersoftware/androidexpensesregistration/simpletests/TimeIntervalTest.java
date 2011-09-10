package seidingersoftware.androidexpensesregistration.simpletests;

import androidexpensesregistration.domain.types.TimeInterval;
import junit.framework.TestCase;

public class TimeIntervalTest extends TestCase {
	private TimeInterval timeInterval;
		
	@Override
	protected void setUp() throws Exception {
		timeInterval = new TimeInterval("01:00:00", "02:00:00");
		super.setUp();
	}

	public void testMustThrowExceptionIfStartTimeGreaterThanEndTime(){
		Exception exception = null;
		try {
			new TimeInterval("02:00:00", "01:00:00");			
		} catch (Exception e) {
			exception = e;			
		}
		assertNotNull(exception);
		assertEquals("Start Time Can't be Greater Than End Time!", exception.getMessage());
	}
	
	public void testGetStartTimeString() {
		assertEquals("01:00:00", timeInterval.getStartTimeString());	
	}

	public void testGetEndTimeString() {
		assertEquals("02:00:00", timeInterval.getEndTimeString());
	}

	public void testIsInPeriodOfReturnTrue() {
		assertTrue(timeInterval.isInPeriodOf("01:30:00"));
	}
	public void testIsInPeriodOfReturnFalse() {
		assertFalse(timeInterval.isInPeriodOf("02:00:01"));
	}
}
