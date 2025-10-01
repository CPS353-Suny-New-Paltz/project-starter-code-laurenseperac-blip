package process;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
	DataValue readInput(String filePath);
	boolean writeOutput(String filePath, DataValue value);
}
