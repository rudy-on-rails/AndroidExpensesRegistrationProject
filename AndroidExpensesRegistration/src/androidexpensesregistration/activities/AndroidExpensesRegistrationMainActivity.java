package androidexpensesregistration.activities;

import seidingersoftware.androidexpensesregistration.R;
import seidingersoftware.androidexpensesregistration.R.id;
import seidingersoftware.androidexpensesregistration.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AndroidExpensesRegistrationMainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView view = (ImageView) findViewById(R.id.imageView1);        
        Button btnAdmintration = (Button) findViewById(R.id.AdmAccessButton);        
        Button btnRegisterExpense = (Button) findViewById(R.id.RegExpenseAccessButton);
        if (btnAdmintration != null)
        btnAdmintration.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View view) {
				 Intent myIntent = new Intent(view.getContext(), RegisterExpenseActivity.class);
	             startActivityForResult(myIntent, 0);
			}
		});
        if (btnRegisterExpense != null)
        btnRegisterExpense.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), AdministrationActivity.class);
	            startActivityForResult(myIntent, 0);				
			}
		});        
    }
}