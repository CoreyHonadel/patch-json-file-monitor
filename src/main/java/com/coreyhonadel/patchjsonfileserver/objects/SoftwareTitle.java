package com.coreyhonadel.patchjsonfileserver.objects;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by coreyhonadel on 2/28/17.
 */
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoftwareTitle {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	private String name;
	@JsonProperty("id")
	private String nameId;
	private String publisher;
	@JsonFormat(pattern = DATE_TIME_FORMAT)
	private Date lastModified;
	private String currentVersion;
	@JsonIgnore
	private String data;
	@JsonIgnore
	private String fileName;

	public SoftwareTitle() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		SoftwareTitle that = (SoftwareTitle) o;

		return new EqualsBuilder()
				.append(name, that.name)
				.append(nameId, that.nameId)
				.append(publisher, that.publisher)
				.append(lastModified, that.lastModified)
				.append(currentVersion, that.currentVersion)
				.append(data, that.data)
				.append(fileName, that.fileName)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(name)
				.append(nameId)
				.append(publisher)
				.append(lastModified)
				.append(currentVersion)
				.append(data)
				.append(fileName)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("name", name)
				.append("nameId", nameId)
				.append("publisher", publisher)
				.append("lastModified", lastModified)
				.append("currentVersion", currentVersion)
				// .append("data", data) Do not include this as it will inflate the logs
				.append("fileName", fileName)
				.toString();
	}
}
