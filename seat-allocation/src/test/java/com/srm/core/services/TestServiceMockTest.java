package com.srm.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.srm.core.repository.TestRepository;



@SpringBootTest
public class TestServiceMockTest {

  @Mock
  private TestRepository testRepository;

  @InjectMocks // auto inject testRepository
  private TestService testService = new TestServiceImpl();

  @BeforeEach
  void setMockOutput() {
      when(testRepository.get()).thenReturn("Test Mockito From Repository");
  }

  @DisplayName("Test Mock testService + testoRepository")
  @Test
  void testGet() {
      assertEquals("Test Mockito From Repository",testService.get());
  }

}