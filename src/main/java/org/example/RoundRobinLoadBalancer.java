package org.example;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundRobinLoadBalancer implements LoadBalancer {

  private final List<URI> registeredInstances;

  public RoundRobinLoadBalancer(Configuration configuration) {
    registeredInstances = new ArrayList<>(configuration.registeredInstances);
  }

  @Override
  public URI get() {
    URI first = registeredInstances.get(0);
    Collections.rotate(registeredInstances, -1);
    return first;
  }

}
