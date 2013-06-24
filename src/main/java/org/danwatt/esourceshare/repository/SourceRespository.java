package org.danwatt.esourceshare.repository;

import org.danwatt.esourceshare.model.Source;
import org.springframework.stereotype.Repository;

@Repository
public class SourceRespository {

	public boolean save(String key, Source source) {
		
		return false;
	}

	public Source getLatestFor(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Source getSpecificRevisionFor(String key, int revision) {
		// TODO Auto-generated method stub
		return null;
	}

}
