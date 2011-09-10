package seidingersoftware.androidexpensesregistration.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import android.R.string;
import android.test.AndroidTestCase;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;

public class ExpenseTypeRepositoryTest extends AndroidTestCase {
	private ExpenseTypeRepository expenseTypeRepository;	
	public ExpenseTypeRepositoryTest() {		
		super();				
	}

	protected void setUp() throws Exception {	
		getContext().deleteDatabase("expenses_registration.db");
		expenseTypeRepository = new ExpenseTypeRepository(getContext());
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	protected ExpenseType createExpenseType() throws IllegalArgumentException, ParseException{
		ExpenseType expenseType = new ExpenseType();
		expenseType.setName("Olá Mundo");
		expenseType.setValue((float) 123.58);
		expenseType.setEstimatedTimeInterval("13:00:00", "14:00:00");
		return expenseType;
	}
	
	protected ExpenseType createExpenseType(String name, String startTime, String endTime, float value) throws IllegalArgumentException, ParseException{
		ExpenseType expenseType = new ExpenseType();
		expenseType.setName(name);
		expenseType.setValue(value);
		expenseType.setEstimatedTimeInterval(startTime, endTime);
		return expenseType;
	}

	public void testSaveMustBeSaveSimpleItem() throws IllegalArgumentException, ParseException {
		ExpenseType expectedExpenseType = createExpenseType();
		expenseTypeRepository.save(expectedExpenseType);		
		assertEquals(1, expenseTypeRepository.count());
		ExpenseType gotExpenseType = expenseTypeRepository.all().get(0);
		assertEquals(gotExpenseType.getName(), expectedExpenseType.getName());
		assertEquals(gotExpenseType.getValue(), expectedExpenseType.getValue());
		assertEquals(gotExpenseType.getEstimatedTimeInterval().getStartTimeString(), expectedExpenseType.getEstimatedTimeInterval().getStartTimeString());		
		assertEquals(gotExpenseType.getEstimatedTimeInterval().getEndTimeString(), expectedExpenseType.getEstimatedTimeInterval().getEndTimeString());
	}
	
	public void testSaveAfterSaveIdObjectIdMustBeSet() throws IllegalArgumentException, ParseException{
		ExpenseType expectedExpenseType = createExpenseType();
		expenseTypeRepository.save(expectedExpenseType);
		assertEquals(expectedExpenseType.getId(),expenseTypeRepository.all().get(0).getId());
	}

	public void testDeleteDeletingSimpleItem() throws IllegalArgumentException, ParseException {
		ExpenseType expectedExpenseType = createExpenseType();
		expenseTypeRepository.save(expectedExpenseType);		
		expenseTypeRepository.delete(expectedExpenseType);
		assertEquals(0, expenseTypeRepository.count());
	}
	
	public void testCountSimpleCountNoObjectInserted() {
		assertEquals(0, expenseTypeRepository.count());
	}
	
	public void testCount5ObjectsInserted() throws IllegalArgumentException, ParseException {
		ArrayList<ExpenseType> expenseTypes = new ArrayList<ExpenseType>();
		for (int i = 0; i < 5; i++) {
			expenseTypes.add(createExpenseType());			
		}
		for (ExpenseType expenseType : expenseTypes) {
			expenseTypeRepository.save(expenseType);
		}
		assertEquals(5, expenseTypeRepository.count());
	}

	public void testFindByIdMustReturnNullIfNothingWasFound() throws IllegalArgumentException, ParseException {				
		assertNull(expenseTypeRepository.findById(12321));
	}
	
	public void testFindByIdMustReturnRecordCaseItExists() throws IllegalArgumentException, ParseException {
		ExpenseType expectedExpenseType = createExpenseType();
		expenseTypeRepository.save(expectedExpenseType);		
		ExpenseType gotExpenseType = expenseTypeRepository.findById(expectedExpenseType.getId());
		assertTrue(expectedExpenseType.equals(gotExpenseType));		
	}

	public void testFindByPropertySimpleReturn() throws IllegalArgumentException, ParseException {
		ExpenseType expectedExpenseType = createExpenseType();
		expenseTypeRepository.save(expectedExpenseType);
		ExpenseType expectedExpenseType2 = createExpenseType("TestCase", "01:00:00", "02:00:00", (float)3.14);
		expenseTypeRepository.save(expectedExpenseType2);
		expenseTypeRepository.findByProperty(new String[]{"name"}, new String[]{""});
	}
		
	public void testAll() {
		fail("Not yet implemented");
	}

}

