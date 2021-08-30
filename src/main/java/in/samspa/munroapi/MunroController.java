package in.samspa.munroapi;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class MunroController {

    private final MunroService munroService;

    public MunroController(MunroService munroService) {
        this.munroService = munroService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Munro> getData() {
        return munroService.findData(null);
    }

}
