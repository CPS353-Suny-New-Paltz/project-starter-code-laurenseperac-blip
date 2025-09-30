package integrationinfra;

import java.util.List;

public class InMemoryOutputConfig {
	private final List<String> output;
	public InMemoryOutputConfig(List<String> output) {
		this.output = output;
	}
	public List<String> getOutput() {
		return output;
	}
}