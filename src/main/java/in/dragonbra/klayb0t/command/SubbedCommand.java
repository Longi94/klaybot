package in.dragonbra.klayb0t.command;

import in.dragonbra.klayb0t.service.TwitchService;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

/**
 * @author lngtr
 * @since 2018-08-07
 */
@Component
public class SubbedCommand extends Command {

    @Value("${twitch.login-url}")
    private String loginUrl;

    @Value("${twitch.bot.channel}")
    private String twitchBotChannel;

    private final TwitchService twitchService;

    private final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()
            .printZeroNever()
            .appendYears()
            .appendSuffix(" year", " years")
            .appendSeparator(", ")
            .appendMonths()
            .appendSuffix(" month", " months")
            .appendSeparator(", ")
            .appendWeeks()
            .appendSuffix(" week", " weeks")
            .appendSeparator(", ")
            .appendDays()
            .appendSuffix(" day", " days")
            .appendSeparator(", ")
            .appendHours()
            .appendSuffix(" hour", " hours")
            .appendSeparator(", ")
            .appendMinutes()
            .appendSuffix(" minute", " minutes")
            .appendSeparator(", ")
            .appendSeconds()
            .appendSuffix(" second", " seconds")
            .toFormatter();

    @Autowired
    public SubbedCommand(TwitchService twitchService) {
        super("subbed", "check how long have you been subbed");
        this.twitchService = twitchService;
    }

    @Override
    public String handle(User user, String message, String[] args) {
        if (user.getLogin().equalsIgnoreCase(twitchBotChannel)) {
            return "Nope Kappa";
        }

        long start = 0;
        try {
            start = twitchService.checkSub(user.getLogin());

            if (start == -1) {
                return "I need your permission to access your subs. Go to " + loginUrl + " to grant it.";
            }

            if (start == -2) {
                return "Looks like you're not subscribed, " + user.getNick() + ".";
            }

            StringBuilder builder = new StringBuilder();

            Period period = new Period(start, System.currentTimeMillis(), PeriodType.yearMonthDayTime());

            String response = PERIOD_FORMATTER.print(period);

            builder.append(user.getNick())
                    .append(" has been subbed for ")
                    .append(response)
                    .append('!');

            return builder.toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
