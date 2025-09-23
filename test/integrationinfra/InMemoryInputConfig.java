package integrationinfra;

import java.util.List;

public class InMemoryInputConfig {
	private final List<Integer> input;
	public InMemoryInputConfig(List<Integer> input) {
		this.input = input;
	}
	public List<Integer> getInput() {
		return input;
	}

}
