package org.beamfoundry.bundles.springdata.mongodb;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PersonDetails {

	@Id
    private ObjectId id;
	
	//@Field("_entity_id")
	//@Indexed(unique = true)
	//private ObjectId entityId;
	
	private String email;
	
	//private Map<String,String> phoneNumbers = new HashMap<String,String>();

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	public Map<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<String, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}*/
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
