package lcbo.auth;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * LcboUser specific {@link org.springframework.data.repository.Repository} interface.
 */
public interface UserRepository extends MongoRepository<LcboUser, String> {

    LcboUser findByUsername(String username);

}
