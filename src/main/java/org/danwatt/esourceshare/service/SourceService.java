package org.danwatt.esourceshare.service;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.danwatt.esourceshare.model.Source;
import org.danwatt.esourceshare.repository.SourceRespository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SourceService {

	public static final int MAX_KEY = 60466176;

	Random random = new SecureRandom();

	@Autowired
	SourceRespository sourceRepository;

	@Transactional(readOnly=false)
	public Source save(Source source) {
		boolean saved = false;
		String key = null;
		source.setRevision(source.getRevision() + 1);
		source.setHash(DigestUtils.sha1Hex(source.getSource()));
		source.setCreatedAt(new DateTime());
		while (!saved) {
			key = Integer.toString(random.nextInt(MAX_KEY), 36);
			saved = sourceRepository.save(key, source);
		}
		source.setKey(key);
		return source;
	}

	@Transactional(readOnly=true)
	public Source get(String key, Integer revision) {
		if (null == revision) {
			return sourceRepository.getLatestFor(key);
		} else {
			return sourceRepository.getSpecificRevisionFor(key, revision);
		}

	}

}
