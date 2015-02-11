package org.beamfoundry.bundles.springdata.testjpa.pax;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.vmOption;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import javax.inject.Inject;

import org.beamfoundry.bundles.springdata.testjpa.Person;
import org.beamfoundry.bundles.springdata.testjpa.PersonRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;

@RunWith(PaxExam.class)
public class Eclipselink24Test extends Assert {

    // Fixtures

    @Inject
    PersonRepository personService;

    @Configuration
    public Option[] commonOptions() {
        switchPlatformEncodingToUTF8();
        return new Option[]{
                karafDistributionConfiguration()
                        .frameworkUrl(
                                maven().groupId("org.apache.karaf")
                                        .artifactId("apache-karaf")
                                        .type("zip")
                                        .version("3.0.3"))
                        .karafVersion("3.0.3")
                        .name("Apache Karaf")
                        .unpackDirectory(new File("target/pax"))
                        .useDeployFolder(false),
                keepRuntimeFolder(),
                vmOption("-Dfile.encoding=UTF-8"),
                configureConsole().ignoreLocalConsole(),
                logLevel(LogLevel.INFO),

                features(
                        maven().groupId("org.beamfoundry.bundles.spring-osgi").
                        		artifactId("org.beamfoundry.bundles.spring-osgi.features").
                                type("xml").classifier("features").version("LATEST"),
                        "spring-dm"),
                        
                features(
                        maven().groupId("org.beamfoundry.bundles.spring-data-jpa.test").
                        		artifactId("org.beamfoundry.bundles.spring-data-jpa.test.features").
                                type("xml").classifier("features").version("LATEST"),
                        "spring-data_eclipselink24"),

                mavenBundle().groupId("org.beamfoundry.bundles.spring-data-jpa.test").
                    	artifactId("org.beamfoundry.bundles.spring-data-jpa.test-datasource").versionAsInProject(),
                    	
                mavenBundle().groupId("org.beamfoundry.bundles.spring-data-jpa.test").
                    	artifactId("org.beamfoundry.bundles.spring-data-jpa.test-eclipselink24").versionAsInProject()
        };
    }

    // Tests

    @Test
    public void shouldSavePerson() {
        // Given
        Person person = new Person("John");

        // When
        personService.save(person);

        // Then
        Person loadedPerson = personService.findByName(person.getName());
        assertEquals(person.getName(), loadedPerson.getName());
    }

    /*
    @Test
    public void shouldRollbackSave() {
        // Given
        Person person = new Person("Henry");

        // When
        try {
            personService.rollbackAfterSave(person);
        } catch (CustomRollbackException e) {
        }

        // Then
        Person loadedPerson = personService.findByName(person.getName());
        assertNull(loadedPerson);
    }

    @Test
    public void shouldCommitPerson() {
        // Given
        Person person = new Person("Fred");

        // When
        personService.commitAfterSave(person);

        // Then
        Person loadedPerson = personService.findByName(person.getName());
        assertNotNull(loadedPerson);
        assertEquals(person.getName(), loadedPerson.getName());
    }*/

    // Helpers

    /**
     * Workaround for PAXEXAM-595 .
     */
    private void switchPlatformEncodingToUTF8() {
        try {
            System.setProperty("file.encoding", "UTF-8");
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}
