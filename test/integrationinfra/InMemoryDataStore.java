package integrationinfra;

import java.util.List;

import process.DataValue;
import process.DataValueImpl;
import process.MultiDataValue;
import process.MultiDataValueImpl;
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

	@Override
	public MultiDataValue readAllInputs(String filePath) {
		MultiDataValueImpl multi = new MultiDataValueImpl(inputConfig.getInput());
		
		inputConfig.getInput().clear();
		
		return multi;
	}

	@Override
	public boolean writeAllOutputs(String filePath, List<Integer> values) {
		for (Integer val : values) {
			outputConfig.getOutput().add("Value " + val);
		}
		
		return true;
	}

}