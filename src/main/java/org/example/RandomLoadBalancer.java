package org.example;

import java.net.URI;
import java.util.List;
import java.util.Random;

public class RandomLoadBalancer implements LoadBalancer {

  private final Random random;
  private final List<URI> registeredBackendInstances;

  public RandomLoadBalancer(Configuration configuration, int seed) {
    random = new Random(seed);
    registeredBackendInstances = configuration.registeredInstances;
  }

  @Override
  public URI get() {
    int randomIndex = random.nextInt(registeredBackendInstances.size());
    return registeredBackendInstances.get(randomIndex);
  }

}
