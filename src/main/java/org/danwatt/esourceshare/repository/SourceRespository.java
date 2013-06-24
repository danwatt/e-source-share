package org.danwatt.esourceshare.repository;

import org.danwatt.esourceshare.model.Source;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SourceRespository {
	@Autowired
	SessionFactory sessionFactory;

	public boolean save(String key, Source source) {
		source.setKey(key);
		getCurrentSession().saveOrUpdate(source);
		return true;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Source getLatestFor(String key) {
		return (Source) getCurrentSession().createQuery("from Source where key=:key order by revision desc").setMaxResults(1)
				.setString("key", key).uniqueResult();
	}

	public Source getSpecificRevisionFor(String key, int revision) {
		return (Source) getCurrentSession().createQuery("from Source where key=:key and revision=:revision").setMaxResults(1)
				.setInteger("revision", revision).setString("key", key).uniqueResult();
	}

}
