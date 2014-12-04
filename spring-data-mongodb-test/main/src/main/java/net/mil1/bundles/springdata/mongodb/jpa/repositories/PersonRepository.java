package net.mil1.bundles.springdata.mongodb.jpa.repositories;

import net.mil1.bundles.springdata.mongodb.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);

}
