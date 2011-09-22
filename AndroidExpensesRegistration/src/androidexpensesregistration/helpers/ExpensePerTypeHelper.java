package androidexpensesregistration.helpers;

import java.util.Collection;
import java.util.HashMap;

import androidexpensesregistration.domain.model.ExpenseType;

public class ExpensePerTypeHelper {
	private HashMap<Integer, ExpensePerTypeRecordHelper> expensePerTypeInternalStorage;
	
	public ExpensePerTypeHelper(){
		expensePerTypeInternalStorage = new HashMap<Integer, ExpensePerTypeRecordHelper>();
	}
	
	public void addExpenseTypeAndValueRecord(ExpenseType expenseType, float valueExpent){
		int key = expenseType.getId();
		float totalValue = valueExpent;
		if (expensePerTypeInternalStorage.containsKey(key)){
			totalValue += expensePerTypeInternalStorage.get(key).getExpenseTypeTotalValueExpent();
			expensePerTypeInternalStorage.get(key).setExpenseTypeTotalValueExpent(totalValue);
		}
		else{
			expensePerTypeInternalStorage.put(key, new ExpensePerTypeRecordHelper(expenseType.getName(), totalValue));			
		}		
	}
	
	public Collection<ExpensePerTypeRecordHelper> getExpensesPerTypeRecordDTOs(){
		return expensePerTypeInternalStorage.values(); 
	}
	
}
