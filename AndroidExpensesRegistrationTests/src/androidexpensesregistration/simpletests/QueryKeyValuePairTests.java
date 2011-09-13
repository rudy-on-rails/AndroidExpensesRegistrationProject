package androidexpensesregistration.simpletests;

import androidexpensesregistration.domain.types.QueryKeyValuePair;
import junit.framework.TestCase;

public class QueryKeyValuePairTests extends TestCase {	
	@Override
	protected void setUp() throws Exception {		
		super.setUp();		
	}
	
	private QueryKeyValuePair createKeyValue(int times){
		QueryKeyValuePair queryKeyValuePair = new QueryKeyValuePair();
		for (int i = 0; i < times; i++) {					
			queryKeyValuePair.AddKeyValue("Chave" + String.valueOf(i),	"Valor" + String.valueOf(i));			
		}
		return queryKeyValuePair;
	}
	
	public void testAddKeyValue() {
		QueryKeyValuePair queryKeyValuePair = new QueryKeyValuePair(); 
		assertEquals(0, queryKeyValuePair.pairsCount());
		queryKeyValuePair = createKeyValue(3);
		assertEquals(3, queryKeyValuePair.pairsCount());
	}

	public void testReturnKeysPropertyStringOneKeyValueAdded() {
		QueryKeyValuePair queryKeyValuePair = createKeyValue(1);
		assertEquals("Chave0 = ?", queryKeyValuePair.returnKeysPropertyString());		
	}	
	
	public void testReturnKeysPropertyStringTwoKeyValuesAdded() {
		QueryKeyValuePair queryKeyValuePair = createKeyValue(2);
		assertEquals("Chave0 = ? AND Chave1 = ?", queryKeyValuePair.returnKeysPropertyString());
	}
	
	public void testReturnKeysPropertyStringThreeKeyValuesAdded() {
		QueryKeyValuePair queryKeyValuePair = createKeyValue(3);
		assertEquals("Chave0 = ? AND Chave1 = ? AND Chave2 = ?", queryKeyValuePair.returnKeysPropertyString());
	}

	public void testReturnAttributesStringArrayThreeKeyValuesAdded() {
		QueryKeyValuePair queryKeyValuePair = createKeyValue(3);
		String[] returnedArray = queryKeyValuePair.returnAttributesStringArray();
		assertEquals(3, returnedArray.length);
		for (int i = 0; i < returnedArray.length; i++) {
			assertEquals(returnedArray[i], "Valor" + String.valueOf(i));
		}		
	}	
}
