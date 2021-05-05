package org.example;

import org.example.exceptions.NonUniqueInstances;
import org.example.exceptions.TooManyInstances;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ConfigurationTest {

  @Test
  void allowsTenBackendInstances() {
    assertDoesNotThrow(() -> Configuration.create(tenSampleValidUris()));
  }

  @Test
  void failsOnMoreThanMaxInstances() {
    List<URI> input = new ArrayList<>(tenSampleValidUris());
    input.add(URI.create("http://localhost:8080"));
    assertThatThrownBy(() -> Configuration.create(input))
      .isInstanceOf(TooManyInstances.class);
  }

  private static List<URI> tenSampleValidUris() {
    return List.of(
      URI.create("http://localhost:8081"),
      URI.create("http://localhost:8082"),
      URI.create("http://localhost:8083"),
      URI.create("http://localhost:8084"),
      URI.create("http://localhost:8085"),
      URI.create("http://localhost:8086"),
      URI.create("http://localhost:8087"),
      URI.create("http://localhost:8088"),
      URI.create("http://localhost:8089"),
      URI.create("http://localhost:8090")
    );
  }

  @Test
  void failsOnNonUniqueInstances() {
    assertThatThrownBy(() ->
      Configuration.create(List.of(URI.create("http://localhost:8080"), URI.create("http://localhost:8080")))
    ).isInstanceOf(NonUniqueInstances.class);
  }

}
