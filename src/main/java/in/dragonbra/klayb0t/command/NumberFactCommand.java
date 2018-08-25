package in.dragonbra.klayb0t.command;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.pircbotx.User;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author lngtr
 * @since 2018-08-17
 */
public class NumberFactCommand extends Command {

    private static final String URL = "http://numbersapi.com/";

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?[0-9]+");

    private final OkHttpClient client = new OkHttpClient();

    public NumberFactCommand() {
        super("fact", "");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        if (args.length < 1) {
            return null;
        }

        if (!NUMBER_PATTERN.matcher(args[0]).matches()) {
            return args[0] + " is not an integer ya dingus.";
        }

        Request request = new Request.Builder()
                .get()
                .url(URL + args[0])
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.code() == 200 && response.body() != null) {
                return response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
