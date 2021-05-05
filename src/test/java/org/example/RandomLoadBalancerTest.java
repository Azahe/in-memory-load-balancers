package org.example;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLoadBalancerTest {

  @Test
  void invocationsOfGetReturnDifferentInstance() {
    var loadBalancer = new RandomLoadBalancer(sampleConfiguration(), 12312);
    URI first = loadBalancer.get();
    URI second = loadBalancer.get();
    URI third = loadBalancer.get();
    assertThat(first)
      .isNotEqualTo(second)
      .isEqualTo(third);
  }

  static Configuration sampleConfiguration() {
    return Configuration.create(
      List.of(URI.create("http://localhost:8081"), URI.create("http://localhost:8082"))
    );
  }

}