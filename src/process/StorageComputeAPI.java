package process;

public interface StorageComputeAPI {
	DataValue readInput(String source);
	void writeOutput(String destination, DataValue data);
}
