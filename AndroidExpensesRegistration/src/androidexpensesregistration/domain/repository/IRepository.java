package androidexpensesregistration.domain.repository;

import java.util.ArrayList;

public interface IRepository<T> {
	void save(T item);
	void delete(T item);
	T findById(int id);
	ArrayList<T> findByProperty(String[] properties, String[] propertyValues);
	ArrayList<T> all();	
}
