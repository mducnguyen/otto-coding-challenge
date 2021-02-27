package de.duc.nguyen.occ.catalogfilter.rest;

import de.duc.nguyen.occ.catalogfilter.mapper.LinkDtoMapper;
import de.duc.nguyen.occ.catalogfilter.models.domain.Node;
import de.duc.nguyen.occ.catalogfilter.rest.api.LinksApi;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;
import de.duc.nguyen.occ.catalogfilter.service.CatalogService;
import de.duc.nguyen.occ.catalogfilter.service.sort.SortService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class LinksEndpoint implements LinksApi {

    private final CatalogService catalogService;

    private final SortService sortService;

    @SneakyThrows
    public ResponseEntity<List<LinkDto>> getLinks(@RequestParam(name = "parent", required = false) String parent,
                                                  @RequestParam(name = "sort", required = false, defaultValue = "label:acs") String sort)  {

        List<Node> links = catalogService.getLinks();

        if (parent != null && !parent.isEmpty()) {

            links = catalogService.getLinks(parent);

        }

        if (links.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<LinkDto> linkDTOS = links.stream().map(LinkDtoMapper::toLinkDto).collect(Collectors.toList());

        sortService.sort(linkDTOS, sort);

        return ResponseEntity.ok(linkDTOS);
    }

}
