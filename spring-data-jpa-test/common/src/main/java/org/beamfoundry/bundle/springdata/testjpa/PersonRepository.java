package org.beamfoundry.bundles.springdata.testjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {

    Person findByName(String name);

}
