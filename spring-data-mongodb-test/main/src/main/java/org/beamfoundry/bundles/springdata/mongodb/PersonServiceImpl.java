package org.beamfoundry.bundles.springdata.mongodb;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beamfoundry.bundles.springdata.mongodb.jpa.repositories.PersonRepository;
import org.beamfoundry.bundles.springdata.mongodb.mongo.repositories.PersonContactDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

//@Service("personService")

public class PersonServiceImpl implements PersonService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	PersonContactDetailsRepository personContactDetailsRepository;

	@Autowired
	private ApplicationContext appContext;
	
	@PostConstruct
	public void MongoCrossStoreTest() {
		
		@SuppressWarnings("unused")
		Object mongoDocumentBacking = appContext.getBean("mongoDocumentBacking");
	}

	@Override
	public Person findByName(String name) {
		//personRepository.flush();
		return personRepository.findByName(name);
	}

	@Override
	@Transactional
	public Person save(Person person) {
		log.debug("### SavingPerson : " + person.toString());
		return personRepository.saveAndFlush(person);
	}

}
