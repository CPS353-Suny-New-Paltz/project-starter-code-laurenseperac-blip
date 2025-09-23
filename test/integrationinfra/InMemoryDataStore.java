package integrationinfra;

import process.DataValue;
import process.DataValueImpl;
import process.StorageComputeAPI;

public class InMemoryDataStore implements StorageComputeAPI {
	
	private final InMemoryInputConfig inputConfig;
	private final InMemoryOutputConfig outputConfig;
	
	public InMemoryDataStore(InMemoryInputConfig inputConfig, InMemoryOutputConfig outputConfig) {
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
	}
	
	@Override
	public DataValue readInput(String source) {
		if (inputConfig.getInput().isEmpty()) {
			return new DataValueImpl(-1);
		}
		return new DataValueImpl(inputConfig.getInput().remove(0));
	}
	
	@Override
	public boolean writeOutput(String destination, DataValue data) {
		outputConfig.getOutput().add("Value " + data.getValue());
		return true;
	}

}
