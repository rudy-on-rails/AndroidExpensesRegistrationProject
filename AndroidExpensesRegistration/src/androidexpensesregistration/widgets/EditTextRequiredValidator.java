package androidexpensesregistration.widgets;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class EditTextRequiredValidator {	
	private ArrayList<EditTextRequired> fieldsToValidate;
	private ArrayList<EditTextRequired> invalidFields;
	private Context context;
	public EditTextRequiredValidator(ArrayList<EditTextRequired> listOfFieldsToValidate, Context context) {		
		fieldsToValidate = listOfFieldsToValidate;
		this.context = context; 
	}
		
	
	public boolean validate() {
		invalidFields = new ArrayList<EditTextRequired>();
		boolean hasInvalidFields = false;
		for (EditTextRequired editText : fieldsToValidate) {
			if (editText.getFieldToValidaText().getText() == null || editText.getFieldToValidaText().length() == 0) {
				hasInvalidFields = true;
				invalidFields.add(editText);
			}				 
		}
		if (hasInvalidFields)
			showErrorMessages();
		return !hasInvalidFields; 		
	}
	
	private void showErrorMessages() {		
		AlertDialog errorDialog = new AlertDialog.Builder(this.context).create();
		errorDialog.setTitle("Erro de Validação");					
		errorDialog.setMessage(buildErrorMessage());
		errorDialog.setButton("OK", new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int which) {
	    	  dialog.dismiss();
	       	} 
	      }); 
		errorDialog.show();
	}

	private CharSequence buildErrorMessage() {
		StringBuilder sBuilder = new StringBuilder();
		String header;
		String footer;		
		if (invalidFields.size() == 1) {
			header = "O campo ";
			footer = " não pode estar em branco!";
		}			
		else {
			header = "Os campos ";
			footer = " não podem estar em branco!";
		}			
		sBuilder.append(header);
		int currentField = 0;
		for (EditTextRequired editTextRequired : this.invalidFields) {
			if (currentField == 0)
				sBuilder.append("\"" + editTextRequired.getFieldName() + "\"");
			else
				sBuilder.append(", \"" + editTextRequired.getFieldName() + "\"");
			currentField++;
		}		
		sBuilder.append(footer);
		return sBuilder;
	}
}
