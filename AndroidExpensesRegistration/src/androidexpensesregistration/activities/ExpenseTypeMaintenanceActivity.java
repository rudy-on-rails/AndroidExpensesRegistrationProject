package androidexpensesregistration.activities;

import java.util.ArrayList;

import seidingersoftware.androidexpensesregistration.R;
import seidingersoftware.androidexpensesregistration.R.drawable;
import seidingersoftware.androidexpensesregistration.R.id;
import seidingersoftware.androidexpensesregistration.R.layout;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidexpensesregistration.domain.adapters.ExpenseTypeAdapter;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;

public class ExpenseTypeMaintenanceActivity extends Activity {
	private ExpenseTypeRepository expenseTypesRepository;
	private ExpenseType selectedExpenseType;
	private ExpenseTypeAdapter adapter;
	private GridView expenseTypeGridView;
	private ArrayList<ExpenseType> expenseTypesList;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expensetypemaintenance);
        ImageView view = (ImageView) findViewById(R.id.imageView1);        
        final ImageButton imgBackBtn = (ImageButton) findViewById(R.id.backExpRegButton);
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

}
