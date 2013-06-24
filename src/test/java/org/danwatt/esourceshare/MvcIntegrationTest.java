package org.danwatt.esourceshare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.danwatt.esourceshare.model.Source;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MvcIntegrationTest extends SpringTest {
	@Autowired
	WebApplicationContext wac;

	MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void postAndGet() throws Exception {
		Source source = new Source();
		source.setSource("Code");

		MvcResult result = mvc.perform(post("/").content(objectMapper.writeValueAsBytes(source)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();
		String createdLocation = result.getResponse().getHeader("Location");
		assertTrue(createdLocation.matches("\\/[A-Za-z0-9]{1,6}\\/1"));
		MvcResult getResponse = mvc.perform(get(createdLocation).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		Source retrieved = objectMapper.readValue(getResponse.getResponse().getContentAsByteArray(), Source.class);
		assertEquals(source.getSource(), retrieved.getSource());
	}

	@Test
	public void getNotFound() throws Exception {
		mvc.perform(get("/nofind/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
	}
}
