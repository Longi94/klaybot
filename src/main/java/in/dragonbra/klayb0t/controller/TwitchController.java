package in.dragonbra.klayb0t.controller;

import in.dragonbra.klayb0t.service.TwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return "redirect:" + twitchService.getLoginUrl(session);
    }

    @GetMapping("/authorize")
    @ResponseBody
    public String authorize(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        twitchService.authorize(request);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        return "Done!";
    }
}
