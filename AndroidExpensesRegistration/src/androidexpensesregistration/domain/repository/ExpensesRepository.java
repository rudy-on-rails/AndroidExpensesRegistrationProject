package androidexpensesregistration.domain.repository;

import android.content.Context;
import androidexpensesregistration.domain.datamappers.ExpenseDataMapper;
import androidexpensesregistration.domain.infra.ExpensesDataBaseAdapter;
import androidexpensesregistration.domain.model.Expense;

public class ExpensesRepository extends Repository<Expense> {
	private static final String COLUMNS_STRING[] = 
			new String[] {"id", "expense_type_id", "value", "date_expense", "description"};
	public ExpensesRepository(Context context) {
		super(context, COLUMNS_STRING, new ExpensesDataBaseAdapter(context), "expenses", new ExpenseDataMapper());				
	}

}
