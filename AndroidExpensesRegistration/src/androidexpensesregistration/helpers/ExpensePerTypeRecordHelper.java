package androidexpensesregistration.helpers;

public class ExpensePerTypeRecordHelper {		
	private String expenseTypeDescription;
	private float expenseTypeTotalValueExpent;
		
	public ExpensePerTypeRecordHelper(String expenseTypeDescription,
			float expenseTypeTotalValueExpent) {		
		this.expenseTypeDescription = expenseTypeDescription;
		this.expenseTypeTotalValueExpent = expenseTypeTotalValueExpent;
	}
		
	public String getExpenseTypeDescription() {
		return expenseTypeDescription;
	}	
	public float getExpenseTypeTotalValueExpent() {
		return expenseTypeTotalValueExpent;
	}

	public void setExpenseTypeDescription(String expenseTypeDescription) {
		this.expenseTypeDescription = expenseTypeDescription;
	}

	public void setExpenseTypeTotalValueExpent(float expenseTypeTotalValueExpent) {
		this.expenseTypeTotalValueExpent = expenseTypeTotalValueExpent;
	}	
	
}
