package ua.com.alevel.facade.location;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.filter.location.RoomFilterDto;
import ua.com.alevel.dto.profile.location.RoomProfileDto;
import ua.com.alevel.dto.table.location.RoomTableDto;
import ua.com.alevel.facade.AbstractFacade;
import ua.com.alevel.mapper.location.RoomMapper;
import ua.com.alevel.persistence.entity.location.Room;
import ua.com.alevel.service.location.RoomService;
import ua.com.alevel.specification.location.RoomSpecificationBuilder;

@Service
public class RoomFacade extends AbstractFacade<Room, RoomFilterDto, RoomTableDto, RoomProfileDto> {

    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final RoomSpecificationBuilder roomSpecificationBuilder;

    public RoomFacade(RoomService roomService,
                      RoomMapper roomMapper,
                      RoomSpecificationBuilder roomSpecificationBuilder) {
        super(roomService, roomMapper, roomSpecificationBuilder);
        this.roomService = roomService;
        this.roomMapper = roomMapper;
        this.roomSpecificationBuilder = roomSpecificationBuilder;
    }
}
