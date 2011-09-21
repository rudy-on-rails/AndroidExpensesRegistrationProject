package androidexpensesregistration.activities;

import seidingersoftware.androidexpensesregistration.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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
        btnReportsButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(view.getContext(), ReportsMainActivity.class);
	            startActivityForResult(myIntent, 0);
			}
		});
    }
}
