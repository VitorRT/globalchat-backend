package br.com.vitordev.globalchat.utils.UserFormat.handler;


public class TrimFormatterHandler extends UsernameFormatHandler {

    public TrimFormatterHandler(UsernameFormatHandler next) {
        super(next);
    }

    @Override
    public String format(String usernameEntrie) {
        String username = usernameEntrie;
        username = username.trim().toLowerCase();
        if (username.startsWith("@")) {
            username = username.substring(1);
        }
        return super.next.format(username);
    }
    
}
