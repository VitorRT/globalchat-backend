package br.com.vitordev.globalchat.utils.UserFormat.handler;

public class UsernameFormated extends UsernameFormatHandler {

    public UsernameFormated(UsernameFormatHandler next) {
        super(null);
    }

    @Override
    public String format(String usernameEntrie) {
        String username = "@" + usernameEntrie;
        return username;
    }

}
