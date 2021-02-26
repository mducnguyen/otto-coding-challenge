package de.duc.nguyen.occ.catalogfilter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.duc.nguyen.occ.catalogfilter.models.domain.Catalog;
import de.duc.nguyen.occ.catalogfilter.rest.model.LinkDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class TestUtils {
    public static String getTestCatalogJson() {
        return "{\n" +
                "  \"navigationEntries\": [{\n" +
                "    \"type\": \"section\",\n" +
                "    \"label\": \"Sortiment\",\n" +
                "    \"children\": [{\n" +
                "      \"type\": \"node\",\n" +
                "      \"label\": \"Alter\",\n" +
                "      \"children\": [{\n" +
                "        \"type\": \"node\",\n" +
                "        \"label\": \"Baby & Kleinkind\",\n" +
                "        \"children\": [{\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"0-6 Monate\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/0-6-months\\/\"\n" +
                "        }, {\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"7-12 Monate\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/7-12-months\\/\"\n" +
                "        }, {\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"13-24 Monate\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/13-24-months\\/\"\n" +
                "        }]\n" +
                "      }, {\n" +
                "        \"type\": \"node\",\n" +
                "        \"label\": \"Kindergarten\",\n" +
                "        \"children\": [{\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"2-3 Jahre\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/24-47-months\\/\"\n" +
                "        }, {\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"4-5 Jahre\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/48-71-months\\/\"\n" +
                "        }]\n" +
                "      }]\n" +
                "    }]\n" +
                "  }]\n" +
                "}";
    }


    public static String getTestInvalidCatalogJson() {
        return "{\n" +
                "    \"type\": \"section\",\n" +
                "    \"label\": \"Sortiment\",\n" +
                "    \"children\": [{\n" +
                "      \"type\": \"node\",\n" +
                "      \"label\": \"Alter\",\n" +
                "      \"children\": [{\n" +
                "        \"type\": \"node\",\n" +
                "        \"label\": \"Baby & Kleinkind\",\n" +
                "        \"children\": [{\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"0-6 Monate\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/0-6-months\\/\"\n" +
                "        }, {\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"7-12 Monate\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/7-12-months\\/\"\n" +
                "        }, {\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"13-24 Monate\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/13-24-months\\/\"\n" +
                "        }]\n" +
                "      }, {\n" +
                "        \"type\": \"node\",\n" +
                "        \"label\": \"Kindergarten\",\n" +
                "        \"children\": [{\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"2-3 Jahre\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/24-47-months\\/\"\n" +
                "        }, {\n" +
                "          \"type\": \"link\",\n" +
                "          \"label\": \"4-5 Jahre\",\n" +
                "          \"url\": \"http:\\/\\/www.mytoys.de\\/48-71-months\\/\"\n" +
                "        }]\n" +
                "      }]\n" +
                "    }]\n" +
                "  }]\n" +
                "}";
    }

    public static Catalog getTestCatalog() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(getTestCatalogJson());
        Catalog catalog = mapper.treeToValue(jsonNode, Catalog.class);
        catalog.initParentForNodes();

        return catalog;
    }

    public static boolean isJsonString(String jsonStr) {
        try {
            new JSONObject(jsonStr);
        } catch (JSONException ex) {
            try {
                new JSONArray(jsonStr);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static boolean istSortedLabelDesc(List<LinkDto> links) {

        LinkDto lastLink;
        LinkDto currentLink = null;

        Iterator<LinkDto> iterator = links.iterator();

        if (iterator.hasNext()) {
            currentLink = iterator.next();
        }

        while (iterator.hasNext()) {
            lastLink = currentLink;
            currentLink = iterator.next();

            if (lastLink.getLabel().compareTo(currentLink.getLabel()) < 0) {
                return false;
            }
        }

        return true;
    }

    public static boolean istSortedUrlAsc(List<LinkDto> links) {

        LinkDto lastLink;
        LinkDto currentLink = null;

        Iterator<LinkDto> iterator = links.iterator();

        if (iterator.hasNext()) {
            currentLink = iterator.next();
        }

        while (iterator.hasNext()) {
            lastLink = currentLink;
            currentLink = iterator.next();

            if (lastLink.getUrl().compareTo(currentLink.getUrl()) >= 0) {
                return false;
            }
        }

        return true;
    }

}
