package androidexpensesregistration.activities;

import seidingersoftware.androidexpensesregistration.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidexpensesregistration.domain.repository.ExpensesRepository;

public class ReportsMainActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportsmain);
        final ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				backButton.setImageDrawable(getResources().getDrawable(R.drawable.backhighlight));				
				Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
        TextView totalOfExpensesTextView = (TextView) findViewById(R.id.totalExpensesValueText);
        ExpensesRepository expensesRepository = new ExpensesRepository(getApplicationContext());
        setTotalAmountOfExpensesFor(totalOfExpensesTextView, expensesRepository);
        setExpenseTotalsPerType(expensesRepository);
    }

	private void setExpenseTotalsPerType(ExpensesRepository expensesRepository) {
		
	}

	private void setTotalAmountOfExpensesFor(TextView totalOfExpensesTextView, ExpensesRepository expensesRepository) {				
		totalOfExpensesTextView.setText(getCurrencyString().concat(" ").concat(String.valueOf(expensesRepository.getAllExpensesSum())));
		expensesRepository = null;
	}

	private String getCurrencyString() {
		//TODO - Possibilitar troca de moeda padrão
		return "R$";
	}
}
