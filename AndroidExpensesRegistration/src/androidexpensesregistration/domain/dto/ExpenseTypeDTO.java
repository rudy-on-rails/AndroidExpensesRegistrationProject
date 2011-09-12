package androidexpensesregistration.domain.dto;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.model.IGenericRecord;

public class ExpenseTypeDTO implements DTO<ExpenseType>{
	private static final int COL_ID = 0; 
	private static final int COL_DESCRIPTION = 1;
	private static final int COL_VALUE = 2;
	private static final int COL_START_TIME_APROX = 3;
	private static final int COL_END_TIME_APROX = 4;
	
	@Override
	public ContentValues getContentValues(IGenericRecord iGenericRecord) {
		if (!(iGenericRecord instanceof ExpenseType))
			throw new IllegalArgumentException("Argument must be an ExpenseType instance!");
		ContentValues contentValues = new ContentValues();
		ExpenseType expense = (ExpenseType)iGenericRecord;		
		contentValues.put("value", expense.getValue());		
		contentValues.put("description", expense.getName());
		if (expense.getEstimatedTimeInterval() != null){
			contentValues.put("start_time_aprox", expense.getEstimatedTimeInterval().getStartTimeString());
			contentValues.put("end_time_aprox", expense.getEstimatedTimeInterval().getEndTimeString());
		}		
		return contentValues;
	}

	@Override
	public ArrayList<ExpenseType> getCursorValues(Cursor cursor, Context context) {
		ArrayList<ExpenseType> expenseTypes = new ArrayList<ExpenseType>();
		while (cursor.moveToNext()) {					
			ExpenseType expenseType = new ExpenseType();
			expenseType.setId(cursor.getInt(COL_ID));
			expenseType.setName(cursor.getString(COL_DESCRIPTION));
			expenseType.setValue(cursor.getFloat(COL_VALUE));
			try {
				if (cursor.getString(COL_START_TIME_APROX) != null &&
						cursor.getString(COL_END_TIME_APROX) != null)
				expenseType.setEstimatedTimeInterval(cursor.getString(COL_START_TIME_APROX), cursor.getString(COL_END_TIME_APROX));
			} catch (Exception e) {				
				Log.v("Cannot parse DateTime Object", e.getMessage());
			}
			expenseTypes.add(expenseType);
		}
		return expenseTypes;
	}
}
