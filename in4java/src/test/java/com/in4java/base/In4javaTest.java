package com.in4java.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: yingjf
 * @date: 2023/4/23 11:02
 * @description:
 */
@Slf4j
public class In4javaTest {

    void contextLoads(int a ,int b) {
        log.info("test");
    }
}


@SpringBootTest
class In4javaTestTests {

    private In4javaTest in4javaTest;
    private final Logger log = LoggerFactory.getLogger(In4javaTest.class);

    @BeforeEach
    void setUp() {
        in4javaTest = new In4javaTest();
    }

    @Test
    @DisplayName("Should log info with positive integers")
    void contextLoads_withPositiveIntegers() {
        // This test assumes the success path where the method logs information without errors.
        // Since the actual logging behavior is external, we're focusing on the method's ability to execute with valid arguments.
        in4javaTest.contextLoads(5, 10);
    }

    @Test
    @DisplayName("Should log info with negative integers")
    void contextLoads_withNegativeIntegers() {
        // Testing the method with negative integers to ensure it handles all integer inputs gracefully.
        in4javaTest.contextLoads(-5, -10);
    }

    @Test
    @DisplayName("Should log info with zero")
    void contextLoads_withZero() {
        // Testing the method with zero to check its behavior with boundary values.
        in4javaTest.contextLoads(0, 0);
    }
}
