package androidexpensesregistration.domain.repository;

import android.content.Context;
import androidexpensesregistration.domain.datamappers.ExpenseDataMapper;
import androidexpensesregistration.domain.infra.ExpensesDataBaseAdapter;
import androidexpensesregistration.domain.model.Expense;

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

}
