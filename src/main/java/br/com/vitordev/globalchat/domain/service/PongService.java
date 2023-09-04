package br.com.vitordev.globalchat.domain.service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Instant;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class PongService {
    private Instant start = Instant.now();

    public double pong() {
        Instant end = Instant.now();
        long pingTime = end.toEpochMilli() - start.toEpochMilli();
        return formatPing(pingTime);
    }


    private double formatPing(long ping) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
        return Double.valueOf(decimalFormat.format(ping));
    }
}
