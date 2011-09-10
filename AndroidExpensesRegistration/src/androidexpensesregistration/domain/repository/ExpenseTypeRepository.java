package androidexpensesregistration.domain.repository;

import android.content.Context;
import androidexpensesregistration.domain.dto.ExpenseTypeDTO;
import androidexpensesregistration.domain.infra.ExpensesDataBaseAdapter;
import androidexpensesregistration.domain.model.ExpenseType;

public class ExpenseTypeRepository extends Repository<ExpenseType> {
	private static final String COLUMNS_STRING[] = 
			new String[] {"id", "description", "value", "start_time_aprox", "end_time_aprox"}; 
	public ExpenseTypeRepository(Context context){		
		super(context,COLUMNS_STRING, new ExpensesDataBaseAdapter(context), "expense_types", new ExpenseTypeDTO());		
	}

}
