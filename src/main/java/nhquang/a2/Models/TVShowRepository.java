package nhquang.a2.Models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVShowRepository extends MongoRepository<TVShow, String> {

}
