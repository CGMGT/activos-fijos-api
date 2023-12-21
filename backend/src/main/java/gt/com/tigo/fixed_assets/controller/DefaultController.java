package gt.com.tigo.fixed_assets.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/default")
public class DefaultController {

    @GetMapping("/sayHello")
    public ResponseEntity sayHello() {
        return ResponseEntity.ok("Hello world!!!");
    }

}
