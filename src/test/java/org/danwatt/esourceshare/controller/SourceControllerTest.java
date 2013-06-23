package org.danwatt.esourceshare.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class SourceControllerTest {
	SourceController controller = new SourceController();

	@Test
	public void index() {
		assertEquals("index", controller.index());
	}
}
