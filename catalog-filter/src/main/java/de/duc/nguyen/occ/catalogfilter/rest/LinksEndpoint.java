package de.duc.nguyen.occ.catalogfilter.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.mapper.LinkDtoMapper;
import de.duc.nguyen.occ.catalogfilter.models.Link;
import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDto;
import de.duc.nguyen.occ.catalogfilter.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/links")
@AllArgsConstructor
public class LinksEndpoint {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<LinkDto>> getLinks(@RequestParam(required = false) String parent) throws JsonProcessingException {

        List<Link> links = catalogService.getLinks();

        if (parent != null && !parent.isEmpty()) {
            links = catalogService.getLinks(parent);
        }

        return ResponseEntity.ok(links
                .stream()
                .map(LinkDtoMapper::toLinkDto)
                .collect(Collectors.toList())
        );
    }

}
