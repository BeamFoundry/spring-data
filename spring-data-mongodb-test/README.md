Using Spring Data (OpenJPA) with Blueprint and Karaf
=========

The example demonstrates how to use [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
(with [OpenJPA](http://openjpa.apache.org/) as a persistence provider) deployed to Blueprint and Karaf.
The JPA and JTA management is delegated to the container via the
[Aries JPA](http://aries.apache.org/modules/jpaproject.html) and
[Aries JTA](http://aries.apache.org/modules/transactionsproject.html) respectively.

Based on [hekonsek fuse-pocs](https://github.com/hekonsek/fuse-pocs) blueprint-openjpa-springdata project.
