package process;

public class StorageComputeAPIPrototype {
	
	public void prototype(StorageComputeAPI storage) {
		DataValue input = storage.readInput(null);
		
		storage.writeOutput(null, input);
		System.out.println("Data flow completed. Value: " + input.getValue());
	}
	
}
