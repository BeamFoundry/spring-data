package net.mil1.bundles.springdata.mongodb.test;

import javax.annotation.PostConstruct;

import net.mil1.bundles.springdata.mongodb.Person;
import net.mil1.bundles.springdata.mongodb.PersonDetails;
import net.mil1.bundles.springdata.mongodb.PersonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

public class PersonServiceTest {
	
	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	PersonService personService;
	
	//@Autowired
	//MongoChangeSetPersister mongoChangeSetPersister;
	
	@Autowired
	private ApplicationContext appContext;
	
	@PostConstruct
	public void MongoCrossStoreTest() {
		
		@SuppressWarnings("unused")
		Object mongoDocumentBacking = appContext.getBean("mongoDocumentBacking");

    	Person person = new Person();
    	person.setName("John");
    	//person = personService.save(person);
    	PersonDetails details = new PersonDetails();
    	details.setEmail("john@moya.ship");
    	person.setDetails(details);
    	//person.getDetails().setEmail("john@moya.ship");
    	log.info("MongoCrossStoreTest : Saving Person :"+person.toString());
    	person = personService.save(person);
    	person = personService.findByName(person.getName());
    	log.info("MongoCrossStoreTest : Person saved:"+person.toString());
	}
}
