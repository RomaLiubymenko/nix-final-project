package ua.com.alevel.service.location;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crudhelper.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.location.Room;
import ua.com.alevel.persistence.repository.location.RoomRepository;
import ua.com.alevel.service.AbstractService;

@Service
public class RoomService extends AbstractService<Room, RoomRepository> {

    private final RoomRepository roomRepository;
    private final CrudRepositoryHelper<Room, RoomRepository> crudRepositoryHelper;

    public RoomService(RoomRepository roomRepository, CrudRepositoryHelper<Room, RoomRepository> crudRepositoryHelper) {
        super(roomRepository, crudRepositoryHelper);
        this.roomRepository = roomRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }
}
