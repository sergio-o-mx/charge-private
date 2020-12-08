package com.mxrampage.chargeanychallenge.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class EntryGeneratorUtils {
    private static final int CHARACTER_LOWER_LIMIT = 97;
    private static final int CHARACTER_UPPER_LIMIT = 122;
    private static final int STRING_LENGTH = 10;

    public EntryGeneratorUtils() {

    }

    public final String generateRandomStringForEntryName() {
        Random random = new Random();
        StringBuilder randomEntryBuilder = new StringBuilder(STRING_LENGTH);
        for (int i = 0; i < STRING_LENGTH; i++) {
            int randomLimitedInt = CHARACTER_LOWER_LIMIT + (int)
                    (random.nextFloat() * (CHARACTER_UPPER_LIMIT - CHARACTER_LOWER_LIMIT + 1));
            randomEntryBuilder.append((char) randomLimitedInt);
        }
        return randomEntryBuilder.toString();
    }

    public final String generateDateStringForEntryDate() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        return dateFormat.format(date);
    }
}
