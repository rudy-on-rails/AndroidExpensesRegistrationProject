package androidexpensesregistration.domain.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExpensesDataBaseAdapter extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "expenses_registration.db";
	private static final int DATABASE_VERSION = 1;
	private static final String EXPENSE_TYPE_TABLE_SQL =
	"create table expense_types (id integer primary key autoincrement, " +								
	"description text, " +
	"value numeric(5,2) not null, " +
	"start_time_aprox time, " +
	"end_time_aprox time);";
	private static final String EXPENSES_TABLE_SQL = 
			"create table expenses (id integer primary key autoincrement, " +
				"expense_type_id integer, " +
				"value numeric(5,2) not null, " +
				"date_expense date not null, " +
				"description text, " +
				"quantity integer," +
				"FOREIGN KEY(expense_type_id) REFERENCES expense_types(id))";
	public ExpensesDataBaseAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(EXPENSE_TYPE_TABLE_SQL);
		database.execSQL(EXPENSES_TABLE_SQL);
	}

	// Method is called during an upgrade of the database, e.g. if you increase
	// the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(ExpensesDataBaseAdapter.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS expenses");
		database.execSQL("DROP TABLE IF EXISTS expense_types");
		onCreate(database);
	}	
}
