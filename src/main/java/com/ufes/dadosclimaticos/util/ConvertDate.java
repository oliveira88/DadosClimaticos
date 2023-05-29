
package com.ufes.dadosclimaticos.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ConvertDate {
        
    public static LocalDate stringToLocalDate(String dateString) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, dtf);
    }
      
    public static LocalDate stringToLocalDate(String dateString, String format) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateString, dtf);
    }

    public static String localDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
    
}
