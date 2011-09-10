package androidexpensesregistration.domain.model;

import android.content.ContentValues;

public interface IGenericRecord {

	public int getId();

	public void setId(int id);
	
	public ContentValues GetContentValues();
}
