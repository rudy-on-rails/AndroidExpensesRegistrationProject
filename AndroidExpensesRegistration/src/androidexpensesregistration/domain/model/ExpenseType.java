package androidexpensesregistration.domain.model;


import android.content.ContentValues;
import androidexpensesregistration.domain.datamappers.ExpenseTypeDataMapper;
import androidexpensesregistration.domain.types.TimeInterval;

public class ExpenseType implements IGenericRecord{
	private int Id;	
	private String Name;
	private float value;
	private TimeInterval estimatedTimeInterval;
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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
		return new ExpenseTypeDataMapper().getContentValues(this);
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.Name;
	}

	public TimeInterval getEstimatedTimeInterval() {
		return estimatedTimeInterval;
	}

	public void setEstimatedTimeInterval(String startTime, String endTime) throws IllegalArgumentException {
		this.estimatedTimeInterval = new TimeInterval(startTime, endTime);	
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime
				* result
				+ ((estimatedTimeInterval == null) ? 0 : estimatedTimeInterval
						.hashCode());
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpenseType other = (ExpenseType) obj;
		if (Id != other.Id)
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (estimatedTimeInterval == null) {
			if (other.estimatedTimeInterval != null)
				return false;
		} else if (!estimatedTimeInterval.equals(other.estimatedTimeInterval))
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}
}
