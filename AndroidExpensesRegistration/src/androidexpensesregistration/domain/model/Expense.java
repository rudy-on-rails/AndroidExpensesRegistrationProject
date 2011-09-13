package androidexpensesregistration.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import android.content.ContentValues;
import androidexpensesregistration.domain.datamappers.ExpenseDataMapper;

public class Expense implements IGenericRecord {	
	
	private int Id;	
	private int quantity;
	private BigDecimal expenseValue;	
	private String Description;	
	private Date DateExpenseWasTaken;	
	private ExpenseType expenseType;
	
	
	public BigDecimal getExpenseValue() {
		return expenseValue;
	}
	public void setExpenseValue(BigDecimal expenseValue) {
		this.expenseValue = expenseValue;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	public Date getDateExpenseWasTaken() {
		return DateExpenseWasTaken;
	}
	public void setDateExpenseWasTaken(Date dateExpenseWasTaken) {
		DateExpenseWasTaken = dateExpenseWasTaken;
	}
	
	
	public ExpenseType getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	@Override
	
	public int getId() {		
		return this.Id;
	}

	@Override
	public void setId(int id) {
		this.Id = id;		
	}
	@Override
	public ContentValues GetContentValues() {
		return new ExpenseDataMapper().getContentValues(this);
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
}
