package androidexpensesregistration.test;

import java.math.BigDecimal;
import android.test.AndroidTestCase;
import androidexpensesregistration.domain.model.Expense;
import androidexpensesregistration.domain.repository.ExpensesRepository;
import androidexpensesregistration.helpers.DateHelper;

public class ExpenseRepositoryTest extends AndroidTestCase {
	
	private ExpensesRepository expensesRepository;
	public ExpenseRepositoryTest() {
		super();
	}

	protected void setUp() throws Exception {
		getContext().deleteDatabase("expenses_registration.db");
		expensesRepository = new ExpensesRepository(getContext());
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetTotalExpensesAmountShouldReturnAllExpensesSum(){			
		Expense despesa1 = new Expense();
		Expense despesa2 = new Expense();
		despesa1.setQuantity(2);	
		despesa1.setExpenseValue(BigDecimal.valueOf(23.5));
		despesa1.setDateExpenseWasTaken(DateHelper.now());		
		despesa2.setQuantity(4);
		despesa2.setExpenseValue(BigDecimal.valueOf(25.0));
		despesa2.setDateExpenseWasTaken(DateHelper.now());		
		expensesRepository.save(despesa1);
		expensesRepository.save(despesa2);
		assertEquals((float) 147.0, expensesRepository.getAllExpensesSum());
	}
	
	public void testGetTotalExpensesAmountShouldReturnAllExpensesSumNoExpenseReturn0(){
		assertEquals(Float.valueOf(0), expensesRepository.getAllExpensesSum());
	}
}
