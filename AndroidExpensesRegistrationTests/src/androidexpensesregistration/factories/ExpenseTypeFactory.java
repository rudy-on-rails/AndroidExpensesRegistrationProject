package androidexpensesregistration.factories;

import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;

public class ExpenseTypeFactory {
	public static ExpenseType createPersistentExpenseType(ExpenseTypeRepository expenseTypeRepository){
		ExpenseType expenseType = new ExpenseType();
		expenseType.setName("TIPODEDESPESA");
		expenseType.setEstimatedTimeInterval("00:00:00", "23:59:59");
		expenseType.setValue((float)100.00);
		expenseTypeRepository.save(expenseType);
		return expenseType;
	}
	public static ExpenseType createPersistentExpenseType(ExpenseTypeRepository expenseTypeRepository, String expenseTypeName, String startTime, String endTime, float value){
		ExpenseType expenseType = new ExpenseType();
		expenseType.setName(expenseTypeName);
		expenseType.setEstimatedTimeInterval(startTime, endTime);
		expenseType.setValue(value);
		expenseTypeRepository.save(expenseType);
		return expenseType;
	}
}
