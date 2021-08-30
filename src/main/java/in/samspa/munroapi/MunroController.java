package in.samspa.munroapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MunroController {

    @GetMapping()
    public @ResponseBody String getData() {
        return "hello";
    }

}
