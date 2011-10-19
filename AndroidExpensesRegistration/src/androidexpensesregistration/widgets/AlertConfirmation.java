package androidexpensesregistration.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertConfirmation {		
	public static void showConfirmDialog(Context context, DialogInterface.OnClickListener confirmListener, DialogInterface.OnClickListener cancelListener) {		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage("Confirma Operacão?")
		       .setCancelable(false)
		       .setPositiveButton("Sim", confirmListener)
		       .setNegativeButton("Não", cancelListener);				
		AlertDialog alert = builder.create();
		alert.show();		
	}			
}
