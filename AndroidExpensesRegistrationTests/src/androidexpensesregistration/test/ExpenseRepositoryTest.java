package androidexpensesregistration.test;

import java.math.BigDecimal;
import java.util.Collection;

import android.test.AndroidTestCase;
import androidexpensesregistration.domain.model.Expense;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;
import androidexpensesregistration.domain.repository.ExpensesRepository;
import androidexpensesregistration.factories.ExpenseFactory;
import androidexpensesregistration.factories.ExpenseTypeFactory;
import androidexpensesregistration.helpers.DateHelper;
import androidexpensesregistration.helpers.ExpensePerTypeRecordHelper;

public class ExpenseRepositoryTest extends AndroidTestCase {
	
	private ExpensesRepository expensesRepository;
	private ExpenseTypeRepository expenseTypeRepository;
	
	public ExpenseRepositoryTest() {
		super();
	}

	protected void setUp() throws Exception {
		getContext().deleteDatabase("expenses_registration.db");
		expensesRepository = new ExpensesRepository(getContext());
		expenseTypeRepository = new ExpenseTypeRepository(getContext());
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
	
	public void testGetTotalsExpentPerTypeShouldReturnAnDTOCollectionCaseTheresRecords(){
		ExpenseType expenseTypeOne = ExpenseTypeFactory.createPersistentExpenseType(expenseTypeRepository);
		ExpenseType expenseTypeTwo = ExpenseTypeFactory.createPersistentExpenseType(expenseTypeRepository);
		for (int i = 0; i < 2; i++) {
			ExpenseFactory.createPersistentExpense(expensesRepository, expenseTypeOne);
		}
		for (int i = 0; i < 4; i++) {
			ExpenseFactory.createPersistentExpense(expensesRepository, expenseTypeTwo);
		}
		Collection<ExpensePerTypeRecordHelper> expensePerTypeRecordHelpers = expensesRepository.getExpensesPerType();
		assertEquals(2, expensePerTypeRecordHelpers.size());
		ExpensePerTypeRecordHelper firstExpensePerTypeRecordHelper = (ExpensePerTypeRecordHelper)expensePerTypeRecordHelpers.toArray()[0];
		ExpensePerTypeRecordHelper secondExpensePerTypeRecordHelper = (ExpensePerTypeRecordHelper)expensePerTypeRecordHelpers.toArray()[1];
		assertEquals((float)50.0, firstExpensePerTypeRecordHelper.getExpenseTypeTotalValueExpent());
		assertEquals((float)100.0, secondExpensePerTypeRecordHelper.getExpenseTypeTotalValueExpent());
	}
	
	public void testGetTotalsExpentPerTypeShouldReturnEmptyArrayListCaseTheresNothing(){
		Collection<ExpensePerTypeRecordHelper> expensePerTypeRecordHelpers = expensesRepository.getExpensesPerType();
		assertEquals(0, expensePerTypeRecordHelpers.size());
	}
}
