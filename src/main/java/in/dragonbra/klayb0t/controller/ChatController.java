package in.dragonbra.klayb0t.controller;

import in.dragonbra.klayb0t.bot.TwitchBot;
import in.dragonbra.klayb0t.model.PostChatBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/chat")
public class ChatController {

    private final TwitchBot bot;

    public ChatController(TwitchBot bot) {
        this.bot = bot;
    }

    @PostMapping
    public void postChat(@RequestBody PostChatBody body) {
        bot.sendMessage(body.getMessage());
    }
}
