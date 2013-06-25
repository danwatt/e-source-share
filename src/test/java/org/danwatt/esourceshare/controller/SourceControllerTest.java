package org.danwatt.esourceshare.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

import javax.servlet.ServletContext;

import org.danwatt.esourceshare.exception.ResourceNotFoundException;
import org.danwatt.esourceshare.model.Source;
import org.danwatt.esourceshare.service.SourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;

@RunWith(MockitoJUnitRunner.class)
public class SourceControllerTest {
	@Mock
	SourceService sourceService;
	@Mock
	ServletContext servletContext;

	@InjectMocks
	SourceController controller;

	@Before
	public void setup() {
		when(servletContext.getContextPath()).thenReturn("");
	}

	@Test
	public void index() {
		assertEquals("index", controller.index());
	}

	@Test
	public void post() {
		Source source = new Source();
		source.setKey("KEY");
		source.setRevision(123);
		when(sourceService.save(source)).thenReturn(source);
		HttpEntity<Void> response = controller.post(source);
		assertEquals("/source/KEY/123", response.getHeaders().getFirst("Location"));
		assertEquals("KEY",response.getHeaders().getFirst("X-Source-Key"));
		assertEquals("123",response.getHeaders().getFirst("X-Source-Revision"));
	}

	@Test
	public void getNoRevision() {
		Source source = new Source();
		when(sourceService.get("KEY", null)).thenReturn(source);
		assertSame(source, controller.get("KEY"));
	}

	@Test
	public void getRevision() {
		Source source = new Source();
		when(sourceService.get("KEY", Integer.valueOf(1))).thenReturn(source);
		assertSame(source, controller.get("KEY", 1));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getSpecificVersionNotFound() {
		when(sourceService.get("KEY", Integer.valueOf(1))).thenReturn(null);
		controller.get("KEY", 1);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getWithoutVersionNotFound() {
		when(sourceService.get("KEY", null)).thenReturn(null);
		controller.get("KEY");
	}
}
