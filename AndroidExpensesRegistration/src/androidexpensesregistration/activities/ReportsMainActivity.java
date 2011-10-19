package androidexpensesregistration.activities;

import java.util.Collection;

import seidingersoftware.androidexpensesregistration.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidexpensesregistration.domain.repository.ExpensesRepository;
import androidexpensesregistration.helpers.ExpensePerTypeRecordHelper;

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
	    Collection<ExpensePerTypeRecordHelper> expensePerTypeRecordHelpers = expensesRepository.getExpensesPerType();
	    LinearLayout linearLayoutDetails = (LinearLayout) findViewById(R.id.linearLayoutDetails);
	    for (ExpensePerTypeRecordHelper expensePerTypeRecordHelper : expensePerTypeRecordHelpers) {
	    	LinearLayout linearLayout = new LinearLayout(this);
			TextView expenseTypeDescription = new TextView(this);
			TextView totalExpentValue = new TextView(this);	
			expenseTypeDescription.setTypeface(Typeface.MONOSPACE);
			expenseTypeDescription.setTextColor(Color.BLACK);
			totalExpentValue.setTextColor(Color.WHITE);
			totalExpentValue.setTypeface(Typeface.MONOSPACE);
			totalExpentValue.setPadding(5, 0, 0, 0);
			expenseTypeDescription.setText(expensePerTypeRecordHelper.getExpenseTypeDescription());
			totalExpentValue.setText(getCurrencyString().concat(" ").concat(String.valueOf(expensePerTypeRecordHelper.getExpenseTypeTotalValueExpent())));
			linearLayout.addView(expenseTypeDescription);
			linearLayout.addView(totalExpentValue);
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
			linearLayout.setPadding(0, 5, 0, 0);			
			linearLayoutDetails.addView(linearLayout);
		}
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
