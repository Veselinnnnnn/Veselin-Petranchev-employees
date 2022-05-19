package com.company.models;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Projects {
    private final int projectId;
    private final Date newDateFrom;
    private final Date newDateTo;


    // constructor for class Projects which convert String input to date
    public Projects(int projectId, String dateFrom, String dateTo) throws ParseException {
        String pattern = "yyyy-MM-dd";

        this.projectId = projectId;

        // There are bugs with convertingToDate from string with different formats
        //this.newDateFrom = convertToDate(dateFrom);
        //this.newDateTo = convertToDate(dateTo);

        // converting String to date with SimpleDateFormat
        newDateFrom = new SimpleDateFormat(pattern).parse(dateFrom);

        // I really don't know why I can not compare/equals String null so i had to improvise
        if(dateTo.charAt(0) == 'N'){
            newDateTo = new Date();
        }else {
            newDateTo = new SimpleDateFormat(pattern).parse(dateTo);
        }
    }

    // getter for projectId
    public int getProjectId() {
        return projectId;
    }

    // getter for projectId
    public Date getNewDateFrom() {
        return newDateFrom;
    }

    //getter for date
    public Date getNewDateTo() {
        return newDateTo;
    }

    // This is a method which will converts String to date in any format but
    // there is a problem with parsing string or pattern into function DateUtils.parseDate()
    public Date convertToDate(String date) {
        if (date.isBlank() || date.isEmpty() || date.charAt(0)=='N') {
            return new Date();
        }
        Date temp = new Date();
        String[] patterns =
                {
                        "yyyy.MM.dd G 'at' HH:mm:ss z",
                        "EEE, MMM d, ''yy",
                        "h:mm a",
                        "hh 'o''clock' a, zzzz",
                        "K:mm a, z",
                        "yyyyy.MMMMM.dd GGG hh:mm aaa",
                        "EEE, d MMM yyyy HH:mm:ss Z",
                        "yyMMddHHmmssZ",
                        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                        "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                        "YYYY-'W'ww-u",
                        "EEE, dd MMM yyyy HH:mm:ss z",
                        "EEE, dd MMM yyyy HH:mm zzzz",
                        "yyyy-MM-dd'T'HH:mm:ssZ",
                        "yyyy-MM-dd'T'HH:mm:ss.SSSzzzz",
                        "yyyy-MM-dd'T'HH:mm:sszzzz",
                        "yyyy-MM-dd'T'HH:mm:ss z",
                        "yyyy-MM-dd'T'HH:mm:ssz",
                        "yyyy-MM-dd'T'HH:mm:ss",
                        "yyyy-MM-dd'T'HHmmss.SSSz",
                        "yyyy-MM-dd",
                        "yyyyMMdd",
                        "dd/MM/yy",
                        "dd/MM/yyyy"
                };
        try {
            temp = DateUtils.parseDate(date, patterns);
        } catch (ParseException e) {e.printStackTrace();}
        return temp;
    }

}
