package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SampleTest {

  @Test
  void saysHello() {
    String actual = new Sample().hello();
    assertThat(actual).isEqualTo("Hello!");
  }

}