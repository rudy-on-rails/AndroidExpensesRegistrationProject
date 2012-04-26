package androidexpensesregistration.domain.repository;

import java.util.Collection;

import android.content.Context;
import androidexpensesregistration.domain.datamappers.ExpenseDataMapper;
import androidexpensesregistration.infra.adapters.ExpensesDataBaseAdapter;
import androidexpensesregistration.domain.model.Expense;
import androidexpensesregistration.helpers.ExpensePerTypeHelper;
import androidexpensesregistration.helpers.ExpensePerTypeRecordHelper;

public class ExpensesRepository extends Repository<Expense> {
	private static final String COLUMNS_STRING[] = 
			new String[] {"id", "expense_type_id", "value", "date_expense", "description", "quantity"};
	public ExpensesRepository(Context context) {
		super(context, COLUMNS_STRING, new ExpensesDataBaseAdapter(context), "expenses", new ExpenseDataMapper());				
	}
	
	public float getAllExpensesSum(){
		float sum = 0;
		for (Expense expense : this.all()) {
			sum += expense.getTotalExpenseValue();
		}
		return sum;
	}
	
	public Collection<ExpensePerTypeRecordHelper> getExpensesPerType(){
		ExpensePerTypeHelper expensePerTypeHelper = new ExpensePerTypeHelper();
		for (Expense expense : this.all()) {
			if (expense.getExpenseType() != null){
				expensePerTypeHelper.addExpenseTypeAndValueRecord(expense.getExpenseType(), expense.getTotalExpenseValue());
			}
		}
		return expensePerTypeHelper.getExpensesPerTypeRecordDTOs();
	}
}
