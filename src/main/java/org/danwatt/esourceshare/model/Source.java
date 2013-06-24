package org.danwatt.esourceshare.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "source")
public class Source {
	@JsonIgnore
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "external_key", insertable = true, updatable = false, length = 6)
	private String key;

	@Column(name = "revision", insertable = true, updatable = false)
	private int revision;
	
	@Column(name = "hash", length = 40)
	private String hash;
	
	@Column(name = "source", length = Integer.MAX_VALUE)
	private String source;
	
	@Column(name = "langauge", length = 64)
	private String language;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Set<String> tags = new TreeSet<String>();
	
	@Column(name = "created_at", nullable = false)
	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
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
