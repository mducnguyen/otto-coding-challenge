package de.duc.nguyen.occ.catalogfilter.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.duc.nguyen.occ.catalogfilter.mapper.LinkDtoMapper;
import de.duc.nguyen.occ.catalogfilter.models.Link;
import de.duc.nguyen.occ.catalogfilter.models.dto.LinkDTO;
import de.duc.nguyen.occ.catalogfilter.service.CatalogService;
import de.duc.nguyen.occ.catalogfilter.service.sort.SortService;
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

    private final SortService sortService;

    @GetMapping
    public ResponseEntity<List<LinkDTO>> getLinks(@RequestParam(name = "parent", required = false) String parent,
                                                  @RequestParam(name = "sort", required = false, defaultValue = "label:acs") String sort) throws JsonProcessingException {

        List<Link> links = catalogService.getLinks();

        if (parent != null && !parent.isEmpty()) {

            links = catalogService.getLinks(parent);

        }

        if (links.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<LinkDTO> linkDTOS = links.stream().map(LinkDtoMapper::toLinkDto).collect(Collectors.toList());

        sortService.sort(linkDTOS, sort);

        return ResponseEntity.ok(linkDTOS);
    }

}
