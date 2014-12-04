package net.mil1.bundles.springdata.mongodb;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.mongodb.crossstore.RelatedDocument;

@Entity
public class Person implements Serializable{

	private static final long serialVersionUID = -3940878008599159885L;

	@Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String name;
    
    @RelatedDocument
    private PersonDetails details;
    
    public Person() { }
    
    public static Person createPerson() {
    	Person person = new Person();
    	person.setDetails(new PersonDetails());
    	return person;
    }
    
    public static Person createPerson(String name) {
    	Person person = createPerson();
    	person.setName(name);
    	return person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public PersonDetails getDetails() {
		return details;
	}

	public void setDetails(PersonDetails details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}