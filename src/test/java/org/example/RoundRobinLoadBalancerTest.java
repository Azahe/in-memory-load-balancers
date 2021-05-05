package org.example;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoundRobinLoadBalancerTest {

  @Test
  void returnsInstancesSequentially() {
    Configuration configuration = Configuration.create(List.of(
      URI.create("http://first"), URI.create("http://second"), URI.create("http://third")
    ));
    LoadBalancer tested = new RoundRobinLoadBalancer(configuration);

    assertThat(tested.get()).isEqualTo(URI.create("http://first"));
    assertThat(tested.get()).isEqualTo(URI.create("http://second"));
    assertThat(tested.get()).isEqualTo(URI.create("http://third"));
  }

  @Test
  void askingForMoreThanConfiguredWrapsBackToFirst() {
    Configuration configuration = Configuration.create(List.of(
      URI.create("http://first"), URI.create("http://second")
    ));
    LoadBalancer tested = new RoundRobinLoadBalancer(configuration);

    assertThat(tested.get()).isEqualTo(URI.create("http://first"));
    assertThat(tested.get()).isEqualTo(URI.create("http://second"));
    assertThat(tested.get()).isEqualTo(URI.create("http://first"));
  }

}
