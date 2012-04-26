package androidexpensesregistration.domain.repository;

import java.util.ArrayList;

import android.content.Context;
import androidexpensesregistration.domain.datamappers.ExpenseTypeDataMapper;
import androidexpensesregistration.infra.adapters.ExpensesDataBaseAdapter;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.helpers.DateHelper;

public class ExpenseTypeRepository extends Repository<ExpenseType> {
	private static final String COLUMNS_STRING[] = 
			new String[] {"id", "description", "value", "start_time_aprox", "end_time_aprox"}; 
	public ExpenseTypeRepository(Context context){		
		super(context,COLUMNS_STRING, new ExpensesDataBaseAdapter(context), "expense_types", new ExpenseTypeDataMapper());		
	}
		
	public ExpenseType getSuggestedExpenseTypeForNow() throws Exception{		
		String nowTimeString = DateHelper.getNowTimeString();
		ArrayList<ExpenseType> values = this.all();
		for (ExpenseType expenseType : values) {			
			if (expenseType.getEstimatedTimeInterval() != null && 
					expenseType.getEstimatedTimeInterval().isInPeriodOf(nowTimeString) == true)
				return expenseType;
		}		
		return null;		
	}
}
