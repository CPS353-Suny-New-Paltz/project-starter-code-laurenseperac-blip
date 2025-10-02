package conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class ComputeEngineImpl implements ComputeEngineAPI {

	private final ComputeEngineAPI computeEngine;
	
	public ComputeEngineImpl() {
        this.computeEngine = null;
    }

	public ComputeEngineImpl(ComputeEngineAPI computeEngine) {
		this.computeEngine = null;
	}

	@Override
	public ComputeResult performComputation(ComputeRequest request) {
		int n = request.getInput();
		for(int candidate = n -1; candidate >= 2; candidate--) {
			if (isPrime(candidate)) {
				return new ComputeResultImpl(candidate);
			}
		}
		return new ComputeResultImpl(-1); 
	}
	
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