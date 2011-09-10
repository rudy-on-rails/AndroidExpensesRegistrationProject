package androidexpensesregistration.domain.types;

import java.util.LinkedHashMap;

public class QueryKeyValuePair {
	private LinkedHashMap<String, String> keyValueMap;
	
	public QueryKeyValuePair(){
		keyValueMap = new LinkedHashMap<String, String>();
	}
	
	public void AddKeyValue(String key, String value){
		this.keyValueMap.put(key, value);
	}
	
	public String returnKeysPropertyString(){
		StringBuilder sBuilder = new StringBuilder();
		int count = 0;
		for (String key : this.keyValueMap.keySet()) {
			if (count == 0){
				sBuilder.append(key);				
			}						
			else{
				sBuilder.append(" AND ");
				sBuilder.append(key);
			}				
			sBuilder.append(" = ?");
			count ++;
		}		
		return sBuilder.toString();
	}
	
	public String[] returnAttributesStringArray(){		
		return this.keyValueMap.values().toArray(new String[this.keyValueMap.size()]);		
	}
	
	public int pairsCount(){
		return this.keyValueMap.size();
	}
}
