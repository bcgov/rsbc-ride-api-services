package bcgov.jh.etk.jhetkcommon.dataaccess.repository;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.ErrorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorRepository extends MongoRepository<ErrorEntity, String> {
}
