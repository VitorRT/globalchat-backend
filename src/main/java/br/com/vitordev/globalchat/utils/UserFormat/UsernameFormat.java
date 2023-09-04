package br.com.vitordev.globalchat.utils.UserFormat;

import br.com.vitordev.globalchat.utils.UserFormat.handler.TrimFormatterHandler;
import br.com.vitordev.globalchat.utils.UserFormat.handler.UsernameCorrectorHandler;
import br.com.vitordev.globalchat.utils.UserFormat.handler.UsernameFormatHandler;
import br.com.vitordev.globalchat.utils.UserFormat.handler.UsernameFormated;

public class UsernameFormat {
    public static String format(String username) {
        UsernameFormatHandler handler = new TrimFormatterHandler(
            new UsernameCorrectorHandler(
                new UsernameFormated(null)
            )
        );
        return handler.format(username);
    }
}
