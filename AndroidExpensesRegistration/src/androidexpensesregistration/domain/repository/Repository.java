package androidexpensesregistration.domain.repository;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidexpensesregistration.domain.datamappers.DataMapper;
import androidexpensesregistration.domain.model.IGenericRecord;
import androidexpensesregistration.domain.types.QueryKeyValuePair;

public abstract class Repository<T extends IGenericRecord> implements IRepository<T> {
	
	SQLiteDatabase sqliteDatabase;
	String tableName;
	String tableColumnsString[];
	@SuppressWarnings("rawtypes")
	DataMapper dto;
	protected Context context;
	public Repository(Context context, String tableColumns[], SQLiteOpenHelper adapterInstance, String tableName, @SuppressWarnings("rawtypes") DataMapper dto) {		
		sqliteDatabase = adapterInstance.getWritableDatabase();
		this.context = context;
		this.tableName = tableName;		
		this.dto = dto;
		this.tableColumnsString = tableColumns;
	}
	
	@Override
	public void save(T item) {		
		if (findById(item.getId()) != null) {
			sqliteDatabase.update(tableName, item.GetContentValues(), "id=?", new String []{String.valueOf(item.getId())});
		}
		else {
			long recordId = sqliteDatabase.insert(tableName, null, item.GetContentValues());
			item.setId((int) recordId);
		}
	}

	@Override
	public void delete(T item) {		
		sqliteDatabase.delete(tableName, "id=?", new String []{String.valueOf(item.getId())});
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(int id) {
		Cursor cursor = sqliteDatabase.query(tableName, tableColumnsString, "id=?", new String []{String.valueOf(id)}, null, null, null);
		ArrayList<T> values = dto.getCursorValues(cursor, this.context);
		cursor.close();		
		return values.size() > 0 ? values.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> findByProperty(QueryKeyValuePair queryKeyValuePair) {
		Cursor cursor = sqliteDatabase.query(tableName, tableColumnsString, queryKeyValuePair.returnKeysPropertyString(), queryKeyValuePair.returnAttributesStringArray(), null, null, null);
		ArrayList<T> values = dto.getCursorValues(cursor, this.context);
		cursor.close();		
		return values;
	}

	@Override
	public ArrayList<T> all() {
		Cursor cursor = sqliteDatabase.query(tableName, tableColumnsString, null, null, null, null, null);
		@SuppressWarnings("unchecked")
		ArrayList<T> values = dto.getCursorValues(cursor, this.context);
		cursor.close();		
		return values;
	}
	
	public int count(){
		return all().size();
	}

	@Override
	protected void finalize() throws Throwable {
		if (sqliteDatabase.isOpen())
			this.sqliteDatabase.close();
		super.finalize();
	}	
	
	
}
