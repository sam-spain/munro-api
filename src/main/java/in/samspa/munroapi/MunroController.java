package in.samspa.munroapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class MunroController {

    private final String DEFAULT_MAX_HEIGHT = "999999";

    private final MunroService munroService;

    public MunroController(MunroService munroService) {
        this.munroService = munroService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Munro> getData(@RequestParam(defaultValue = "999999") Double maxHeight,
                               @RequestParam(defaultValue = "1") Double minHeight,
                               @RequestParam(defaultValue = "all") String categoryFilter,
                               @RequestParam(required = false) List<String> sort,
                               @RequestParam(defaultValue = "9999") Integer maxResults) {

        try {
            return munroService.findData(new MunroRequest.Builder()
                    .withMaxHeight(maxHeight)
                    .withMinHeight(minHeight)
                    .withCategoryFilter(categoryFilter)
                    .withSorting(sort)
                    .withMaxResults(maxResults)
                    .build());
        } catch (BadApiQueryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
