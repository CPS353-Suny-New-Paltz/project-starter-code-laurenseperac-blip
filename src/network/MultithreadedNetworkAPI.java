package network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import conceptual.ComputeEngineImpl;
import process.StorageComputeImpl;

public class MultithreadedNetworkAPI implements UserComputeAPI {

	private static final int MAX_THREADS = 4;
	
	private final UserComputeImpl delegate;
	private final ExecutorService executor;
	
	public MultithreadedNetworkAPI() {
		this.delegate = new UserComputeImpl(new ComputeEngineImpl(), new StorageComputeImpl());
		this.executor = Executors.newFixedThreadPool(MAX_THREADS);
	}
	
	@Override
	public JobResponse submitJob(JobRequest request) {
		try {
			return delegate.submitJob(request);
		} catch (Exception e) {
			return new JobResponseImpl(false, "submitJob failed: " + e.getMessage());
		}
	}
	
	public List<String> processRequests(List<String> requests) {
		if (requests == null) {
			return new ArrayList<>();
		}
		
		List<Callable<String>> callables = new ArrayList<>();
		for (String req : requests) {
			callables.add(() -> {
				return "processed:" + req;
			});
		}
		
		try {
			List<Future<String>> futures = executor.invokeAll(callables);
			List<String> results = new ArrayList<>(futures.size());
			for(Future<String> f : futures) {
				try {
					results.add(f.get());
				} catch (ExecutionException ee) {
					results.add("error:" + ee.getCause().getMessage());
				}
			}
			return results;
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			List<String> empty = new ArrayList<>();
			return empty;
		}
	}
	
	public void shutdown() {
		executor.shutdown();
		try {
			if(!executor.awaitTermination(2, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
}
