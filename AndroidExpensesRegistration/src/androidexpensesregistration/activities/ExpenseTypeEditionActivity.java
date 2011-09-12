package androidexpensesregistration.activities;

import java.util.ArrayList;

import seidingersoftware.androidexpensesregistration.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;
import androidexpensesregistration.widgets.EditTextRequired;
import androidexpensesregistration.widgets.EditTextRequiredValidator;

public class ExpenseTypeEditionActivity extends Activity {
	private ExpenseType expenseType;
	private ExpenseTypeRepository expenseTypeRepository;
	private EditText valorText;
	private EditText descricaoText;
	private EditText horaInicialText;
	private EditText horaFinalText;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expensetypeedition);
        expenseTypeRepository = new ExpenseTypeRepository(getApplicationContext());
        valorText = (EditText) findViewById(R.id.ValueText);
        descricaoText = (EditText) findViewById(R.id.expenseTypeDescription);
        horaInicialText = (EditText) findViewById(R.id.startTimeText);
        horaFinalText = (EditText) findViewById(R.id.endTimeText);
        expenseType = getOrCreateExpenseType();        
        final ImageButton imgBackButton = (ImageButton) findViewById(R.id.BackButton);
        final ImageButton imgSaveButton = (ImageButton) findViewById(R.id.ConfirmButton);
        imgBackButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {		
				imgBackButton.setImageDrawable(getResources().getDrawable(R.drawable.backhighlight));				
				Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
        imgSaveButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {				
				EditTextRequired descricaoFieldRequired = 
						new EditTextRequired(descricaoText);
				EditTextRequired valueFieldRequired = 
						new EditTextRequired(valorText);				
				valueFieldRequired.setFieldName("Valor");
				descricaoFieldRequired.setFieldName("Descricão");				
				ArrayList<EditTextRequired> editTextRequiredList = 
						new ArrayList<EditTextRequired>();
				editTextRequiredList.add(descricaoFieldRequired);
				editTextRequiredList.add(valueFieldRequired);
				EditTextRequiredValidator editTextRequiredValidator = 
						new EditTextRequiredValidator(editTextRequiredList, getThisContext());
				if (editTextRequiredValidator.validate()){											
					expenseType.setName(descricaoText.getText().toString());
					expenseType.setValue(Float.valueOf(valorText.getText().toString()));
					if (horaInicialText.getText().length() > 0 &&
							horaFinalText.getText().length() > 0){
						expenseType.setEstimatedTimeInterval(horaInicialText.getText().toString(), horaFinalText.getText().toString());
					}
					expenseTypeRepository.save(expenseType);
					Intent intent = new Intent();
					setResult(RESULT_OK, intent);
	                finish();
				}
			}			
		});
    }
	
	private ExpenseType getOrCreateExpenseType() {
		Bundle bundle = this.getIntent().getExtras();
		int recordId = 0;
		if (bundle  != null && bundle.containsKey("recordId"))
			recordId = bundle.getInt("recordId");
		if (recordId > 0){
			expenseType = expenseTypeRepository.findById(recordId);
			valorText.setText(String.valueOf(expenseType.getValue()));
			descricaoText.setText(expenseType.getName());
			if (expenseType.getEstimatedTimeInterval() != null){
				horaInicialText.setText(expenseType.getEstimatedTimeInterval().getStartTimeString());
				horaFinalText.setText(expenseType.getEstimatedTimeInterval().getEndTimeString());
			}			
			return expenseType;
		}
		else
			return new ExpenseType();
	}
	 
	private Context getThisContext() {
		return this;
    }
}
