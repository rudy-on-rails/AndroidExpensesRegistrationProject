package androidexpensesregistration.domain.adapters;

import java.util.ArrayList;
import seidingersoftware.androidexpensesregistration.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidexpensesregistration.domain.model.ExpenseType;

public class ExpenseTypeAdapter extends BaseAdapter {
		private  ArrayList<ExpenseType> allElementDetails;

		private LayoutInflater mInflater;

		public ExpenseTypeAdapter(Context context, ArrayList<ExpenseType> results) {
		    allElementDetails = results;
		    mInflater = LayoutInflater.from(context);
		}

		public int getCount() {
		    return allElementDetails.size();        
		}

		public Object getItem(int position) {
		    return allElementDetails.get(position);
		}

		public long getItemId(int position) {
		    return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) 
		{
		    convertView = mInflater.inflate(R.layout.expensetypegridlayout, null);		
		    TextView descriptionTextView = (TextView) convertView.findViewById(R.id.DescriptionText);
		    TextView defaultValueTextView = (TextView) convertView.findViewById(R.id.DefaultValue);		    
		    TextView timeintervalTextView = (TextView) convertView.findViewById(R.id.timeInterval);
		    descriptionTextView.setText(allElementDetails.get(position).getName());
		    defaultValueTextView.setText(String.valueOf(allElementDetails.get(position).getValue()));
		    if (allElementDetails.get(position).getEstimatedTimeInterval() != null){		    	
		    	timeintervalTextView.setText(allElementDetails.get(position).getEstimatedTimeInterval().toString());
		    }
		    else {
				timeintervalTextView.setText("");
			}
		    convertView.setPadding(2, 2, 2, 2);
		    return convertView;    
		}	
}
