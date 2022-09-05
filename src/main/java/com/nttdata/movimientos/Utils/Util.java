package com.nttdata.movimientos.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.nttdata.movimientos.Utils.Constants.DATE_FORMAT;

public class Util {

    public static Date parseDate(String dateStr) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(dateStr);
    }
}
