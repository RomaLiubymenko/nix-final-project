package ua.com.alevel.mapper.location;

import org.mapstruct.*;
import ua.com.alevel.dto.profile.location.RoomProfileDto;
import ua.com.alevel.dto.table.location.RoomTableDto;
import ua.com.alevel.mapper.CommonMapper;
import ua.com.alevel.mapper.educationalprocess.LessonMapper;
import ua.com.alevel.persistence.entity.location.Room;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                LessonMapper.class
        }
)
public interface RoomMapper extends CommonMapper<Room, RoomTableDto, RoomProfileDto> {

    @Override
    RoomTableDto toTableDto(Room entity);

    @Override
    @Mapping(target = "lessons", ignore = true)
    Room toEntity(RoomProfileDto profileDto);

    @Override
    @Mapping(target = "lessons", qualifiedByName = "forRoomProfileDto")
    RoomProfileDto toProfileDto(Room entity);

    @Named("forLessonProfileDto")
    @Mapping(target = "lessons", ignore = true)
    RoomProfileDto roomToRoomProfileDtoForLessonProfileDto(Room entity);

}
