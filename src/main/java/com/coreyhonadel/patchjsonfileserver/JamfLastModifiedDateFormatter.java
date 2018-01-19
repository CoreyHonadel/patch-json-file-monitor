package com.coreyhonadel.patchjsonfileserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * Created by coreyhonadel on 3/24/17.
 */
@Component
public class JamfLastModifiedDateFormatter extends SimpleDateFormat {

	private String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public String convert(Date date) {
		return this.format(date);
	}

	@PostConstruct
	public void setPattern() {
		super.setTimeZone(TimeZone.getTimeZone("UTC"));
		super.applyPattern(pattern);
	}

}
