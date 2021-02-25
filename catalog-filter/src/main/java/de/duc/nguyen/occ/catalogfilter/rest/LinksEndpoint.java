package de.duc.nguyen.occ.catalogfilter.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.mapper.DtoMapper;
import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDto;
import de.duc.nguyen.occ.catalogfilter.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/links")
@AllArgsConstructor
public class LinksEndpoint {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<LinkDto>> getLinks() throws JsonProcessingException {
        return ResponseEntity.ok(catalogService.getLinks().stream().map(DtoMapper::toLinkDto).collect(Collectors.toList()));
    }

}
