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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Munro> getData() {
        Munro munro = new Munro("Sam", 2.5D, "MUN", "000112");
        return Arrays.asList(munro, munro);
    }

}
