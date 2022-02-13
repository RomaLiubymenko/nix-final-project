package ua.com.alevel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.AbstractFilterDto;
import ua.com.alevel.dto.AbstractProfileDto;
import ua.com.alevel.dto.AbstractTableDto;
import ua.com.alevel.validation.ValidUuid;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Validated
public interface CommonController<
        FILTER_DTO extends AbstractFilterDto,
        TABLE_DTO extends AbstractTableDto,
        PROFILE_DTO extends AbstractProfileDto> {

    @Operation(summary = "Create a new entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entry is created"),
            @ApiResponse(responseCode = "400", description = "Entry already have uuid")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PROFILE_DTO> create(@Valid @RequestBody PROFILE_DTO profileDto);

    @Operation(summary = "Update a entry by own uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entry was updated"),
            @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    @PutMapping(path = "/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PROFILE_DTO> update(@Valid @RequestBody PROFILE_DTO profileDto, @ValidUuid @PathVariable UUID uuid);

    @Operation(summary = "Delete a entry")
    @ApiResponse(responseCode = "204", description = "Entry was deleted")
    @DeleteMapping("/{uuid}")
    ResponseEntity<Void> delete(@ValidUuid @PathVariable UUID uuid);

    @Operation(summary = "Delete a entries")
    @ApiResponse(responseCode = "204", description = "Entries was deleted")
    @DeleteMapping("/uuids")
    ResponseEntity<Void> deleteByUuids(@RequestParam(value = "uuid") Set<UUID> uuids);

    @Operation(summary = "Get a entry by its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entry is found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            }),
            @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    @GetMapping("/{uuid}")
    ResponseEntity<PROFILE_DTO> findByUuid(@ValidUuid @PathVariable UUID uuid);

    @Operation(
            summary = "Returns an entries",
            parameters = {
                    @Parameter(
                            name = "query",
                            description = "The query to search for"
                    ),
                    @Parameter(
                            name = "filter",
                            description = "An entity that is a set of fields for searching entries"
                    ),
                    @Parameter(
                            name = "pageable",
                            description = "Pagination information"
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Entries are existing", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    })
            }
    )
    @GetMapping
    ResponseEntity<List<TABLE_DTO>> findAll(@RequestParam(required = false) String query, FILTER_DTO filter, Pageable pageable);
}
