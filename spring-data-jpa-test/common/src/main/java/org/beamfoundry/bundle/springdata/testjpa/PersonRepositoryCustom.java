package org.beamfoundry.bundles.springdata.testjpa;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PersonRepositoryCustom {

    void rollbackAfterSave(Person person);

    void commitAfterSave(Person person);

}