package ua.com.alevel.controller.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.controller.AbstractController;
import ua.com.alevel.dto.filter.location.RoomFilterDto;
import ua.com.alevel.dto.profile.location.RoomProfileDto;
import ua.com.alevel.dto.table.location.RoomTableDto;
import ua.com.alevel.facade.location.RoomFacade;

@RestController
@RequestMapping(value = "/api/v1/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController extends AbstractController<RoomTableDto, RoomProfileDto, RoomFilterDto> {

    private static final String ENTITY_NAME = "room";
    private static final String URL = "/api/v1/rooms";
    private static final Logger logger = LoggerFactory.getLogger(RoomController.class);
    private final RoomFacade roomFacade;

    public RoomController(RoomFacade roomFacade) {
        super(URL, ENTITY_NAME, logger, roomFacade);
        this.roomFacade = roomFacade;
    }
}
