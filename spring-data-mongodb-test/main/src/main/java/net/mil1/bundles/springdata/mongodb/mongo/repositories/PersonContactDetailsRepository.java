package net.mil1.bundles.springdata.mongodb.mongo.repositories;

import net.mil1.bundles.springdata.mongodb.PersonDetails;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonContactDetailsRepository extends PagingAndSortingRepository<PersonDetails, Long> {
	
	/*
	public PersonContactDetails findByEntityId(ObjectId id);
	
	public UserAccountDetails findByUsername(String username);
	
	@Query("{ '_entity_id' : ?0 }")
	public UserAccountDetails findByUserId(String userId);
	
	@Query("{ 'socialConnections' : { "
			+ "		$elemMatch : { 	'providerId' : ?0, "
			+ "						'providerUserId' : ?1 } } }")
	public List<UserAccountDetails> findByProvider(String providerId, String providerUserId);
	
	@Query("{ '_entity_id' : ?0, "
			+ "'socialConnections' : { "
			+ "		$elemMatch : { 	'providerId' : ?1, "
			+ "						'providerUserId' : ?2 } } }")	
	public UserAccountDetails findByUserIdAndProvider(String userId, String providerId, String providerUserId);

	@Query("{ '_entity_id' : ?0, "
			+ "'socialConnections' : { "
			+ "		$elemMatch : { 	'providerId' : ?1  } } }")	
	public UserAccountDetails findByUserIdAndProviderId(String userId, String providerId);
	*/
}
