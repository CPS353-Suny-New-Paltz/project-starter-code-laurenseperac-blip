package conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class ComputeEngineImpl implements ComputeEngineAPI {

	private final ComputeEngineAPI computeEngine;
	
	public ComputeEngineImpl() {
        this.computeEngine = null;
    }

	public ComputeEngineImpl(ComputeEngineAPI computeEngine) {
		this.computeEngine = computeEngine;
	}

	@Override
	public ComputeResult performComputation(ComputeRequest request) {
		if (request == null) {
			return new ComputeResultImpl(-1);
		}
		try {
			int n = request.getInput();
			if (n <= 2) {
				return new ComputeResultImpl(-1);
			}
			for (int candidate = n - 1; candidate >= 2; candidate--) {
				if (isPrime(candidate)) {
					return new ComputeResultImpl(candidate);
				}
			}
			return new ComputeResultImpl(-1);
		} catch (Exception e) {
			return new ComputeResultImpl(-1);
		}
	}
	
	
	// does not need validation because it's a private helper method
	private boolean isPrime(int x) {
		if(x < 2) { 
			return false;
		}
		if (x % 2 == 0) {
			return x == 2;
		}
		int r = (int) Math.sqrt(x);
		for (int i = 3; i <= r; i+= 2) {
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}
}