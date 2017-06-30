package lcbo.controller;

import lcbo.auth.LcboUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller for handling user account sign up.
 */
@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    private static final Logger log = LoggerFactory.getLogger(SignUpController.class);

    @RequestMapping(method=POST)
    public String signup(@RequestParam(value="username") String username,
                         @RequestParam(value="password") String password,
                         Model model, RedirectAttributes attributes) {

        log.debug("signup: adding user " + username);

        try {
            userDetailsManager.createUser(new LcboUser(username, password));
        } catch (IllegalArgumentException e) {
            log.error("signup: credentials for \"" + username + "\" already exist.");
            attributes.addAttribute("error", true);
            return "redirect:/signup";
        }

        log.debug("signup: added credentials for \"" + username + "\"");

        model.addAttribute("username", username);
        model.addAttribute("password", password);

        return "forward:/login";
    }

    @RequestMapping(method=GET)
    public String signup() {
        return "redirect:/login?error";
    }

}
