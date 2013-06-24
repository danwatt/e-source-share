package org.danwatt.esourceshare.controller;

import javax.servlet.ServletContext;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public HttpEntity<Void> post(@RequestBody Source source) {
		Source saved = sourceService.save(source);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", servletContext.getContextPath() +"/"+ saved.getKey() + "/" + saved.getRevision());
		HttpEntity<Void> response = new HttpEntity<Void>((Void) null, headers);
		return response;
	}

	@RequestMapping(value = { "/{key}" }, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Source get(@PathVariable String key) {
		return sourceService.get(key, null);
	}

	@RequestMapping(value = { "/{key}/{revision}" }, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Source get(@PathVariable String key, @PathVariable int revision) {
		return sourceService.get(key, revision);
	}
}
