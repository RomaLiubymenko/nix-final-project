package ua.com.alevel.persistence.repository.location;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.location.Room;
import ua.com.alevel.persistence.repository.CommonRepository;

@Repository
public interface RoomRepository extends CommonRepository<Room> {
}
