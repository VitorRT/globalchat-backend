package br.com.vitordev.globalchat.utils.UserFormat.handler;

public class UsernameCorrectorHandler extends UsernameFormatHandler {

    public UsernameCorrectorHandler(UsernameFormatHandler next) {
        super(next);
    }

    @Override
    public String format(String usernameEntrie) {
        String username = usernameEntrie;
        username = username.replaceAll("\\s+", " ");
        username = username.replace(" ", "_");
        return super.next.format(username);
    }
    
}
