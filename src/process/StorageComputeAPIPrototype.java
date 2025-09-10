package process;

public class StorageComputeAPIPrototype implements StorageComputeAPI {
	@Override
	@ProcessAPIPrototype
	public DataValue readInput(String source) {
		return new DataValue() {
			@Override
			public int getValue() {
				return 5; //random num
			}
		};
	}
	
	@Override
	@ProcessAPIPrototype
	public void writeOutput(String destination, DataValue data) {
		System.out.println("Prototype writing " + data.getValue() + " to " + destination);
	}
}
