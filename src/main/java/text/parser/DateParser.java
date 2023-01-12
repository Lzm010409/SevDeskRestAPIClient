package text.parser;

import java.util.Calendar;

public class DateParser {

    public Calendar parseDate(String Date) {
        char[] charArray = Date.toCharArray();
        String date = Date.replace("&nbsp", " ");
        StringBuilder builder = new StringBuilder();
        char point = '.';
        char tab = ' ';
        int length = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i]) && length < 2) {
                builder.append(charArray[i]);
                charArray[i] = ' ';
                length += 1;
            }
            if (length >= 2) {

                break;
            }
        }
        length = 0;
        int day = Integer.parseInt(builder.toString());
        builder.setLength(0);

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i]) && length < 2) {
                builder.append(charArray[i]);
                charArray[i] = ' ';
                length += 1;
            }
            if (length >= 2) {
                break;
            }
        }
        length = 0;
        int month = Integer.parseInt(builder.toString());
        builder.setLength(0);

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i]) && length < 4) {
                builder.append(charArray[i]);
                charArray[i] = ' ';
                length += 1;
            }
            if (length >= 4) {
                break;
            }
        }
        int year = Integer.parseInt(builder.toString());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.YEAR,year);



        return calendar;
    }
}
