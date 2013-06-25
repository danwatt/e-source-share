package org.danwatt.esourceshare.repository;

import static org.junit.Assert.assertEquals;

import org.danwatt.esourceshare.SpringTest;
import org.danwatt.esourceshare.model.Source;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SourceRepositoryTest extends SpringTest{

	@Autowired
	SourceRespository sourceRepository;

	@Test
	public void saveAndGet() {
		Source s = new Source();
		s.setSource("code");
		s.setCreatedAt(new DateTime());
		sourceRepository.save("key", s);
		Source fetched = sourceRepository.getLatestFor("key");
		assertEquals("code",fetched.getSource());
	}
}
