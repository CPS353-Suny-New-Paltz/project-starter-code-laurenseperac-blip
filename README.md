## Compute Engine Optimization

### Summary
After measuring CPU usage, it was discovered that the compute engine spent a majority of time scanning downward for 'n-1' and repeatedly calling 'isPrime(candidate)', which is a sqrt-based check. In order to fix this, 'FastComputeEngineImpl' was created. This implementation:
- uses a Sieve of Eratosthenes cache for batch workloads to allow for a fast lookup of the previous prime
- uses a Miller-Rabin primality check for larger single requests.

### How the bottleneck was identified:
- performComputation calls were instrumented with System.nanoTime() in order to measure how long each major part of the method takes.

### Benchmark results:
- Original time: 2.751 s 
- Fast time: 1.321 s
- Speedup: ~52%

Files added:
- Benchmark Test: https://github.com/CPS353-Suny-New-Paltz/project-starter-code-laurenseperac-blip/blob/main/test/benchmark/ComputeEngineBenchmarkTest.java
- Fast Compute Engine Implementation: https://github.com/CPS353-Suny-New-Paltz/project-starter-code-laurenseperac-blip/blob/main/src/conceptual/FastComputeEngineImpl.java
- Sieve cache: https://github.com/CPS353-Suny-New-Paltz/project-starter-code-laurenseperac-blip/blob/main/src/conceptual/FastComputeEngineImpl.java

