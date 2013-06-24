package org.danwatt.esourceshare.model;

import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Source {
	@JsonIgnore
	private int id;
	private String key;
	private int revision;
	private String hash;
	private String source;
	private String language;
	private Set<String> tags = new TreeSet<String>();
	private DateTime createdAt = new DateTime();

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public String getHash() {
		return hash;
	}

	public int getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getLanguage() {
		return language;
	}

	public int getRevision() {
		return revision;
	}

	public String getSource() {
		return source;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
