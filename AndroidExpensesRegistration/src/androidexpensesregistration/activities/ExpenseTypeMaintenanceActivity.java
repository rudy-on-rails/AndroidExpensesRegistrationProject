package androidexpensesregistration.activities;

import java.util.ArrayList;

import seidingersoftware.androidexpensesregistration.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.GridView;
import android.widget.ImageButton;
import androidexpensesregistration.domain.adapters.ExpenseTypeAdapter;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;

public class ExpenseTypeMaintenanceActivity extends Activity {
	private ExpenseTypeRepository expenseTypesRepository;
	private ExpenseType selectedExpenseType;
	private ExpenseTypeAdapter adapter;
	private GridView expenseTypeGridView;
	private ArrayList<ExpenseType> expenseTypesList;
	private ImageButton imgAddNewBtn;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expensetypemaintenance);
        final ImageButton imgBackBtn = (ImageButton) findViewById(R.id.backExpRegButton);
        ImageButton imgAddNewBtn = (ImageButton) findViewById(R.id.addNewBtn);
        expenseTypeGridView = (GridView) findViewById(R.id.ExpenseTypesGridView);
        loadRepositoryDataToAdapter();
        registerForContextMenu(expenseTypeGridView);                       
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				imgBackBtn.setImageDrawable(getResources().getDrawable(R.drawable.backhighlight));				
				Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});        
        imgAddNewBtn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {				
				openEditionFormFor(null);
			}			
		});
    }
	private void openEditionFormFor(ExpenseType expenseTypeObject) {
		if (imgAddNewBtn != null)
			imgAddNewBtn.setImageDrawable(getResources().getDrawable(R.drawable.add_highlight));
		Intent myIntent = new Intent(getApplicationContext(), ExpenseTypeEditionActivity.class);
		if (expenseTypeObject != null)
			myIntent.putExtra("recordId", expenseTypeObject.getId());
        startActivityForResult(myIntent, 0);            	
	}
	private void loadRepositoryDataToAdapter() {
		expenseTypesRepository = new ExpenseTypeRepository(getApplicationContext());
		expenseTypesList = expenseTypesRepository.all();
        adapter = new ExpenseTypeAdapter(getApplicationContext(), expenseTypesList);
        expenseTypeGridView.setAdapter(adapter);		
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();		    
		selectedExpenseType = (ExpenseType) adapter.getItem(info.position);
	    switch (item.getItemId()) {
	    case R.id.deletemenuitem:	
	    	expenseTypesRepository.delete(selectedExpenseType);	    	
	    	loadRepositoryDataToAdapter();
	    	adapter.notifyDataSetChanged();
	        return true;
	    case R.id.editmenuitem:	
	    	openEditionFormFor(selectedExpenseType);	    	
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {		
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.recordmenu, menu);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		loadRepositoryDataToAdapter();
    	adapter.notifyDataSetChanged();
    	if (imgAddNewBtn != null)
    		imgAddNewBtn.setImageDrawable(getResources().getDrawable(R.drawable.add));
	}
	
	
	

}
