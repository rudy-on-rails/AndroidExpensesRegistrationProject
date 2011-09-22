package androidexpensesregistration.domain.datamappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import androidexpensesregistration.domain.model.Expense;
import androidexpensesregistration.domain.model.IGenericRecord;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;
import androidexpensesregistration.helpers.DateHelper;

public class ExpenseDataMapper implements DataMapper<Expense>{
	private final int COL_ID = 0;
	private final int COL_EXPENSE_TYPE_ID = 1;
	private final int COL_VALUE = 2;
	private final int COL_EXPENSE_DATE = 3;
	private final int COL_DESCRIPTION = 4;	
	private final int COL_QUANTITY = 5;
	
	@Override
	public ContentValues getContentValues(IGenericRecord iGenericRecord) {
		if (!(iGenericRecord instanceof Expense))
			throw new IllegalArgumentException("Argument must be an Expense instance!");
		ContentValues contentValues = new ContentValues();
		Expense expense = (Expense)iGenericRecord;
		if (expense.getExpenseType() != null)
			contentValues.put("expense_type_id", expense.getExpenseType().getId());
		contentValues.put("quantity", expense.getQuantity());
		contentValues.put("value", expense.getExpenseValue().floatValue());		
		contentValues.put("description", expense.getDescription());
		contentValues.put("date_expense", DateHelper.getFormattedDataStringForDB(expense.getDateExpenseWasTaken()));		
		return contentValues;
	}

	@Override
	public ArrayList<Expense> getCursorValues(Cursor cursor, Context context){
		ArrayList<Expense> expensesArrayList = new ArrayList<Expense>();
		while (cursor.moveToNext()) {					
			Expense expense = new Expense();
			expense.setId(cursor.getInt(COL_ID));
			expense.setDescription(cursor.getString(COL_DESCRIPTION));
			expense.setExpenseValue(BigDecimal.valueOf(cursor.getFloat(COL_VALUE)));
			expense.setQuantity(cursor.getInt(COL_QUANTITY));
			try {
				if (cursor.getInt(COL_EXPENSE_TYPE_ID) != 0)
					expense.setExpenseType(new ExpenseTypeRepository(context).findById(cursor.getInt(COL_EXPENSE_TYPE_ID)));
			} catch (Exception e) {
				Log.d("Error getting expense type...", e.getMessage());
			}			
			expense.setDateExpenseWasTaken(DateHelper.parseDateInputStringToDate(cursor.getString(COL_EXPENSE_DATE)));			
			expensesArrayList.add(expense);
		}
		return expensesArrayList;
	}
}
