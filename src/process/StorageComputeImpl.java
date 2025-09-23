package process;

public class StorageComputeImpl implements StorageComputeAPI {

	private final StorageComputeAPI storageCompute;

	public StorageComputeImpl(StorageComputeAPI storageCompute) {
		this.storageCompute = storageCompute;
	}


	@Override
	public DataValue readInput(String key) {
		return new DataValueImpl(-1); //unimplemented
	}

	@Override
	public boolean writeOutput(String key, DataValue value) {
		return false;
	}
