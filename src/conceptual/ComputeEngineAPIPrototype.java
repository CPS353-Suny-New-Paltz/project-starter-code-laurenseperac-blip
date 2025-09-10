package conceptual;

public class ComputeEngineAPIPrototype implements ComputeEngineAPI {
	@Override
	@ConceptualAPIPrototype
	public ComputeResult performComputation(ComputeRequest request) {
		int input = request.getInput();
		int output = largestPrimeSmallerThan(input);
		
		return new ComputeResult() {
			@Override
			public int getOutput() {
				return output;
			}
		};
	}
	
	private int largestPrimeSmallerThan(int n) {
		if (n <= 2) return -1;
		for (int candidate = n -1; candidate > 1; candidate--) {
			if (isPrime(candidate)) return candidate; 
		}
		return -1;
	}
	
	private boolean isPrime(int x) {
		if (x <= 1) return false;
		if (x == 2) return true;
		if (x % 2 == 0) return false;
		for (int i = 3; i * i <= x; i += 2) {
			if (x % i == 0) return false;
		}
		return true;
	}
}
