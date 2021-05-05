package org.example;

import org.example.exceptions.NonUniqueInstances;
import org.example.exceptions.TooManyInstances;

import java.net.URI;
import java.util.List;

public class Configuration {

  final List<URI> registeredInstances;

  static Configuration create(List<URI> backendInstances) {
    return new Configuration(backendInstances);
  }

  private Configuration(List<URI> instances) {
    if (isMoreThanMax(instances))
      throw new TooManyInstances();
    if (hasDuplicates(instances))
      throw new NonUniqueInstances();
    registeredInstances = instances;
  }

  private static boolean isMoreThanMax(List<URI> instances) {
    return instances.size() > 10;
  }

  private static boolean hasDuplicates(List<URI> instances) {
    return instances.size() != instances.stream().distinct().count();
  }

}
