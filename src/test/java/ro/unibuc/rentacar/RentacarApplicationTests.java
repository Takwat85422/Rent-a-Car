package ro.unibuc.rentacar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class RentacarApplicationTests {

	@Test
	void contextLoads() {
		// verifica faptul ca tot contextul Spring porneste corect (test de integrare)
	}
}