package androidexpensesregistration.activities;

import java.math.BigDecimal;

import seidingersoftware.androidexpensesregistration.R;
import seidingersoftware.androidexpensesregistration.R.drawable;
import seidingersoftware.androidexpensesregistration.R.id;
import seidingersoftware.androidexpensesregistration.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidexpensesregistration.domain.infra.ExpensesDataBaseAdapter;
import androidexpensesregistration.domain.model.ExpenseType;
import androidexpensesregistration.domain.repository.ExpenseTypeRepository;

public class AdministrationActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administration);
        final ImageView view = (ImageView) findViewById(R.id.imageView1);        
        final ImageButton imgBackBtn = (ImageButton) findViewById(R.id.imageButton1);
        Button btnReportsButton = (Button) findViewById(R.id.ReportsButton);
        Button btnRegType = (Button) findViewById(R.id.RegTypeMaintenanceButton);
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				imgBackBtn.setImageDrawable(getResources().getDrawable(R.drawable.backhighlight));				
				Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
        btnRegType.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {																							
				Intent myIntent = new Intent(view.getContext(), ExpenseTypeMaintenanceActivity.class);
	            startActivityForResult(myIntent, 0);
			}
		});
    }
}
