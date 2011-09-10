package androidexpensesregistration.widgets;

import android.widget.EditText;

public class EditTextRequired implements IValidationRequiredAttribute{
	private String fieldName;
	private EditText fieldToValidaText;
	public EditTextRequired(EditText editTextField) {
		fieldToValidaText = editTextField;		
	}

	public EditText getFieldToValidaText() {
		return fieldToValidaText;
	}

	public void setFieldToValidaText(EditText fieldToValidaText) {
		this.fieldToValidaText = fieldToValidaText;
	}

	@Override
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName; 		
	}

	@Override
	public String getFieldName() {
		return fieldName;		
	}
}
