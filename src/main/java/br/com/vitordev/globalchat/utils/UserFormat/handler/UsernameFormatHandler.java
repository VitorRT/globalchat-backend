package br.com.vitordev.globalchat.utils.UserFormat.handler;

public abstract class UsernameFormatHandler {
    protected UsernameFormatHandler next;

    public UsernameFormatHandler(UsernameFormatHandler next) {
        this.next = next;
    }

    public abstract String format(String usernameEntrie);
}
