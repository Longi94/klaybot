package in.dragonbra.klayb0t.controller;

import in.dragonbra.klayb0t.service.TwitchService;
import in.dragonbra.klayb0t.util.SessionAttributeNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lngtr
 * @since 2017-12-31
 */
@Controller
@RequestMapping("/twitch/klaybot")
public class TwitchController {

    private final TwitchService twitchService;

    @Autowired
    public TwitchController(TwitchService twitchService) {
        this.twitchService = twitchService;
    }

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam(required = false) String returnUrl) {
        session.setAttribute(SessionAttributeNames.RETURN_URL, returnUrl);
        return "redirect:" + twitchService.getLoginUrl(session);
    }

    @GetMapping("/authorize")
    public String authorize(HttpServletRequest request, HttpSession session) {
        twitchService.authorize(request);

        String returnUrl = (String) session.getAttribute(SessionAttributeNames.RETURN_URL);
        session.removeAttribute(SessionAttributeNames.RETURN_URL);

        return returnUrl == null ? "redirect:/" : "redirect:" + returnUrl;
    }
}
