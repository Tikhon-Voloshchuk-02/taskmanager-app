package task.app.taskmanager.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import task.app.taskmanager.model.AppUser;

import java.util.Optional;
public interface AppUserRepository extends MongoRepository<AppUser, String>{
    Optional<AppUser> findByUsername (String username);
}
