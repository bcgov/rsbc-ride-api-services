package bcgov.jh.etk.jhetkcommon.dataaccess.repository;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<EventEntity, String> {
}
