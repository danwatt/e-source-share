package org.danwatt.esourceshare.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.danwatt.esourceshare.model.Source;
import org.danwatt.esourceshare.repository.SourceRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SourceServiceTest {

	@Mock
	SourceRespository repository;

	@Mock
	Random random;

	@InjectMocks
	SourceService service;

	private Source source;

	@Before
	public void setup() {
		source = new Source();
		when(random.nextInt(SourceService.MAX_KEY)).thenReturn(1, 2, 3);
	}

	@Test
	public void saveNew() {
		when(repository.save("1", source)).thenReturn(true);
		service.save(source);
		verify(repository).save(Integer.toString(1, 36), source);
		assertEquals("1", source.getKey());
		assertEquals(1, source.getRevision());
	}

	@Test
	public void saveWhenKeygenCollides() {
		when(repository.save("1", source)).thenReturn(false);
		when(repository.save("2", source)).thenReturn(false);
		when(repository.save("3", source)).thenReturn(true);
		service.save(source);
		verify(repository).save(Integer.toString(1, 36), source);
		verify(repository).save(Integer.toString(2, 36), source);
		verify(repository).save(Integer.toString(3, 36), source);
	}

	@Test
	public void getLatestRevision() {
		when(repository.getLatestFor("key")).thenReturn(source);
		assertSame(source, service.get("key", null));
	}

	@Test
	public void getSpecificRevision() {
		when(repository.getSpecificRevisionFor("key", 1)).thenReturn(source);
		assertSame(source, service.get("key", Integer.valueOf(1)));
	}
}
