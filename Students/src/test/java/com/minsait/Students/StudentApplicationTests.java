package com.minsait.Students;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
    void testMainMethod() {
		StudentsApplication.main(new String[] {});
    }

}
