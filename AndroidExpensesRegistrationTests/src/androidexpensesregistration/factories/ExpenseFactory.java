package androidexpensesregistration.factories;

import java.math.BigDecimal;

import androidexpensesregistration.domain.model.Expense;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;
import androidexpensesregistration.domain.repository.ExpensesRepository;
import androidexpensesregistration.helpers.DateHelper;

public class ExpenseFactory {
	public static Expense createPersistentExpense(ExpensesRepository expensesRepository){
		ExpenseTypeRepository expenseTypeRepository = new ExpenseTypeRepository(expensesRepository.getUsedContext());
		Expense expense = new Expense();
		expense.setDateExpenseWasTaken(DateHelper.now());
		expense.setDescription("Descricao da despesa");
		expense.setQuantity(1);
		expense.setExpenseValue(BigDecimal.valueOf(25.00));
		expense.setExpenseType(ExpenseTypeFactory.createPersistentExpenseType(expenseTypeRepository));
		expensesRepository.save(expense);
		return expense;
	}
	
	public static Expense createPersistentExpense(ExpensesRepository expensesRepository, ExpenseType expenseType){
		Expense expense = new Expense();
		expense.setDateExpenseWasTaken(DateHelper.now());
		expense.setDescription("Descricao da despesa");
		expense.setQuantity(1);
		expense.setExpenseValue(BigDecimal.valueOf(25.00));
		expense.setExpenseType(expenseType);
		expensesRepository.save(expense);
		return expense;
	}
}
