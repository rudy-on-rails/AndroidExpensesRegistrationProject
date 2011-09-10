package androidexpensesregistration.domain.repository;

import java.util.ArrayList;

import androidexpensesregistration.domain.types.QueryKeyValuePair;

public interface IRepository<T> {
	void save(T item);
	void delete(T item);
	T findById(int id);
	ArrayList<T> findByProperty(QueryKeyValuePair queryKeyValuePair);
	ArrayList<T> all();	
}
