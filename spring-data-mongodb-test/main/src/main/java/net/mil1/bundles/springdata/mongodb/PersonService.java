package net.mil1.bundles.springdata.mongodb;


public interface PersonService {
	
	Person findByName(String name);
    
    Person save(Person person);
}
