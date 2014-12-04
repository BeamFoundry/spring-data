package net.mil1.bundles.springdata.mongodb;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionConfigurationFilePutOption;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel;
import net.mil1.bundles.springdata.mongodb.PersonService;

@RunWith(PaxExam.class)
public class MongoDBTest extends Assert {

	protected final Log log = LogFactory.getLog(getClass());
    // Fixtures

    @Inject
    PersonService personService;

    @Configuration
    public Option[] commonOptions() {
        switchPlatformEncodingToUTF8();
        return new Option[]{
                karafDistributionConfiguration()
                        .frameworkUrl(
                                maven().groupId("org.apache.karaf")
                                        .artifactId("apache-karaf")
                                        .type("zip")
                                        .version("3.0.2"))
                        .karafVersion("3.0.2")
                        .name("Apache Karaf")
                        .unpackDirectory(new File("target/pax"))
                        .useDeployFolder(false),
                keepRuntimeFolder(),
                vmOption("-Dfile.encoding=UTF-8"),
                configureConsole().ignoreLocalConsole(),
                logLevel(LogLevel.INFO),
                features(
                        maven().groupId("org.apache.karaf.features").artifactId("enterprise").type("xml")
                                .classifier("features").version("3.0.2"),
                        "transaction", "jndi", "jdbc"),
                        
                mavenBundle().groupId("org.hsqldb").artifactId("hsqldb").versionAsInProject(),
                
                features(
                        maven().groupId("net.mil1.bundles.spring-osgi").
                        		artifactId("net.mil1.bundles.spring-osgi.features").
                                type("xml").classifier("features").version("LATEST"),
                        "spring-dm"),
                features(
                        maven().groupId("net.mil1.bundles.spring-data.test-jpa").
                        		artifactId("net.mil1.bundles.spring-data.test-jpa.features").
                                type("xml").classifier("features").version("LATEST"),
                        "spring-data_hibernate42"),
                features(
                        maven().groupId("net.mil1.bundles.spring-data").
                        		artifactId("net.mil1.bundles.spring-data.features").
                                type("xml").classifier("features").version("LATEST"),
                        "spring-data-mongodb", "spring-data-mongodb-cross-store"),
                        
                mavenBundle().groupId("net.mil1.bundles.spring-data").
            	artifactId("net.mil1.bundles.spring-data.test-mongodb-adapter").
            		versionAsInProject(),
                mavenBundle().groupId("net.mil1.bundles.spring-data").
                	artifactId("net.mil1.bundles.spring-data.test-mongodb-main").
                		versionAsInProject()
        };
    }

    // Tests

    @Test
    public void shouldSavePerson() throws InterruptedException {
        // Given
    	Person person = Person.createPerson();
    	person.setName("John");
    	person.getDetails().setEmail("john@moya.ship");
    	
        // When
        personService.save(person);

        // Then
        Person loadedPerson = personService.findByName(person.getName());
        assertEquals(person.getName(), loadedPerson.getName());
        assertEquals(person.getDetails().getEmail(), loadedPerson.getDetails().getEmail());
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
    }
	*/
    
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
