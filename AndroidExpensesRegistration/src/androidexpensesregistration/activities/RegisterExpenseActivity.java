package androidexpensesregistration.activities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import seidingersoftware.androidexpensesregistration.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import androidexpensesregistration.domain.model.Expense;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;
import androidexpensesregistration.domain.repository.ExpensesRepository;
import androidexpensesregistration.helpers.DateHelper;
import androidexpensesregistration.widgets.EditTextRequired;
import androidexpensesregistration.widgets.EditTextRequiredValidator;

public class RegisterExpenseActivity extends Activity {
	static final int DATE_DIALOG_ID = 0;
	private int mDay;
	private int mMonth;
	private int mYear;
	private Spinner expenseTypeSpinner;
	private EditText valueField;
	private DatePickerDialog.OnDateSetListener mDateSetListener =
             new DatePickerDialog.OnDateSetListener() {

                 public void onDateSet(DatePicker view, int year, 
                                       int monthOfYear, int dayOfMonth) {
                     mYear = year;
                     mMonth = monthOfYear + 1;
                     mDay = dayOfMonth;
                     updateDisplay();
                 }				
    };
    private void updateDisplay() {
    	TextView dateExpenseWasTakenTextView = (TextView) findViewById(R.id.dateExpenseWasTakentextView);
    	dateExpenseWasTakenTextView.setText(DateHelper.convertDateToString(mDay, mMonth, mYear));
	}
    private Context getThisContext() {
    	return this;
    }
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenseregistration);                      
        Button pickDateButton = (Button) findViewById(R.id.changeDateButton);
        final ImageButton backButton = (ImageButton) findViewById(R.id.BackButton);
        final ImageButton confirmButton = (ImageButton) findViewById(R.id.ConfirmButton);
        Calendar c = Calendar.getInstance();
        fillExpenseTypeField();
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mMonth = c.get(Calendar.MONTH);
        mYear = c.get(Calendar.YEAR);
        updateDisplay();
        pickDateButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
        backButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				backButton.setImageDrawable(getResources().getDrawable(R.drawable.backhighlight));				
				Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
        confirmButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				confirmButton.setImageDrawable(getResources().getDrawable(R.drawable.confirmhighlight));
				TextView dateExpenseTextView = (TextView) findViewById(R.id.dateExpenseWasTakentextView); 
				EditText descriptionField = (EditText) findViewById(R.id.expenseDescription);
				valueField = (EditText) findViewById(R.id.ValueText);							
				EditText quantityField = (EditText) findViewById(R.id.QuantityText);
				EditTextRequired quantityFieldRequired = 
						new EditTextRequired(quantityField);
				EditTextRequired valueFieldRequired = 
						new EditTextRequired(valueField);				
				valueFieldRequired.setFieldName("Valor");
				quantityFieldRequired.setFieldName("Quantidade");				
				ArrayList<EditTextRequired> editTextRequiredList = 
						new ArrayList<EditTextRequired>();
				editTextRequiredList.add(quantityFieldRequired);
				editTextRequiredList.add(valueFieldRequired);
				EditTextRequiredValidator editTextRequiredValidator = 
						new EditTextRequiredValidator(editTextRequiredList, getThisContext());
				if (editTextRequiredValidator.validate()){					
					int qty = Integer.valueOf(quantityField.getText().toString());
					double value = Float.valueOf(valueField.getText().toString());
					Expense expense = new Expense();
					expense.setDateExpenseWasTaken(DateHelper.parseScreenDateStringToDate(dateExpenseTextView.getText().toString()));
					expense.setDescription(descriptionField.getText().toString());
					expense.setQuantity(qty);
					expense.setExpenseValue(BigDecimal.valueOf(value * qty));
					expenseTypeSpinner = (Spinner) findViewById(R.id.typeExpenseSpinner);
					if (expenseTypeSpinner.getSelectedItem() != null) {
						ExpenseType expenseType = (ExpenseType) expenseTypeSpinner.getSelectedItem();
						expense.setExpenseType(expenseType);
					}				
					ExpensesRepository expensesRepository = 
							new ExpensesRepository(getApplicationContext());
					expensesRepository.save(expense);
					Intent intent = new Intent();
	                setResult(RESULT_OK, intent);
	                finish();
				}
				else {
					confirmButton.setImageDrawable(getResources().getDrawable(R.drawable.confirm));
				}
			}
		});
    }
	
	private void fillExpenseTypeField() {			
		expenseTypeSpinner = (Spinner) findViewById(R.id.typeExpenseSpinner);
		valueField = (EditText) findViewById(R.id.ValueText);
		expenseTypeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View v,
					int pos, long id) {				
				ExpenseType expenseType = (ExpenseType) adapterView.getItemAtPosition(pos);
				if (expenseType != null)
					valueField.setText(String.valueOf(expenseType.getValue()));
			}			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {										
			}					
		});		
		expenseTypeSpinner.setPrompt(getString(R.string.select));
		configureExpenseTypeSpinner();		
	}
	
	private void configureExpenseTypeSpinner() {
		ExpenseTypeRepository expenseTypeRepository = new ExpenseTypeRepository(getApplicationContext());
		expenseTypeSpinner = (Spinner) findViewById(R.id.typeExpenseSpinner);
		ArrayAdapter<ExpenseType> expensetypeAdapter = new ArrayAdapter<ExpenseType>(expenseTypeSpinner.getContext(), android.R.layout.simple_spinner_item);
		expensetypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);		
		for (ExpenseType expenseType : expenseTypeRepository.all()) {
			expensetypeAdapter.add(expenseType);
		}		
		expenseTypeSpinner.setAdapter(expensetypeAdapter);
		setSuggestedValueFor(expenseTypeSpinner, expensetypeAdapter, expenseTypeRepository);
		expenseTypeRepository = null;
	}
	
	private void setSuggestedValueFor(Spinner expenseTypeSpinner,
			ArrayAdapter<ExpenseType> expensetypeAdapter,
			ExpenseTypeRepository expenseTypeRepository) {		
		ExpenseType suggestedExpenseType = null;
		try {
			suggestedExpenseType = expenseTypeRepository.getSuggestedExpenseTypeForNow();
		} catch (Exception e) {			
			Log.e("Error getting expense type suggestion", e.getMessage());
		}
		if (suggestedExpenseType != null){
			expenseTypeSpinner.setSelection(expensetypeAdapter.getPosition(suggestedExpenseType));			
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
	    switch (id) {
	    case DATE_DIALOG_ID:
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    mYear, mMonth, mDay);
	    }
	    return null;
	}
}
