package org.danwatt.esourceshare.controller;

import javax.servlet.ServletContext;

import org.danwatt.esourceshare.exception.ResourceNotFoundException;
import org.danwatt.esourceshare.model.Source;
import org.danwatt.esourceshare.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SourceController {
	@Autowired
	SourceService sourceService;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/source" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public HttpEntity<Void> post(@RequestBody Source source) {
		Source saved = sourceService.save(source);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", servletContext.getContextPath() +"/source/"+ saved.getKey() + "/" + saved.getRevision());
		headers.add("X-Source-Key", saved.getKey());
		headers.add("X-Source-Revision", Integer.toString(saved.getRevision()));
		HttpEntity<Void> response = new HttpEntity<Void>((Void) null, headers);
		return response;
	}

	@RequestMapping(value = { "/source/{key}" }, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Source get(@PathVariable String key) {
		Source s = sourceService.get(key, null);
		if (s == null ){
			throw new ResourceNotFoundException();
		}
		return s;
	}

	@RequestMapping(value = { "/source/{key}/{revision}" }, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Source get(@PathVariable String key, @PathVariable int revision) {
		Source s = sourceService.get(key, revision);
		if (s == null ){
			throw new ResourceNotFoundException();
		}
		return s;
	}
}
