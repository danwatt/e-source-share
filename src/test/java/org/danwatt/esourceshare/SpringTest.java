package org.danwatt.esourceshare;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@ContextConfiguration
@Transactional
@WebAppConfiguration
public abstract class SpringTest {
	@Configuration
	@Profile("unit")
	@Import(SpringConfig.class)
	public static class UnitTestingConfiguration {
		
	}

}
