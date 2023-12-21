package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public RedirectView auth() {
        return new RedirectView(this.authService.getAuthorizationUrl());
    }

    @GetMapping("/getRedirectUrl")
    public String getRedirectUrl() {
        return this.authService.getAuthorizationUrl();
    }

    @GetMapping("/callback")
    public String callback(@RequestParam String code) {
        try {
            return this.authService.getToken(code);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return "ERROR";
        }
    }

}
