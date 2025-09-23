package process;

import project.annotations.ProcessAPIPrototype;

public class StorageComputeAPIPrototype {
	
	@ProcessAPIPrototype
public class StorageComputeAPIPrototype {
	
	public void prototype(StorageComputeAPI storage) {
		DataValue input = storage.readInput(null);
		
		storage.writeOutput(null, input);
		System.out.println("Data flow completed. Value: " + input.getValue());
	}
	
}
