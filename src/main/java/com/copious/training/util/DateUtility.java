package com.copious.training.util;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;


public class DateUtility {

    private DateUtility() {
    }

    public static LocalDate setStartDateToSaturday(LocalDate date) {
        LocalDate nDate;

        if (date.getDayOfWeek() < DateTimeConstants.SATURDAY) {
            nDate = date.minusWeeks(1).withDayOfWeek(DateTimeConstants.SATURDAY);
        } else {
            nDate = date.withDayOfWeek(DateTimeConstants.SATURDAY);
        }

        return nDate;
    }

    public static LocalDate setEndDateToFriday(LocalDate date) {
        LocalDate nDate;

        if (date.getDayOfWeek() < DateTimeConstants.FRIDAY) {
            nDate = date.minusWeeks(1).withDayOfWeek(DateTimeConstants.FRIDAY);
        } else {
            nDate = date.withDayOfWeek(DateTimeConstants.FRIDAY);
        }

        return nDate;
    }

    public static LocalDate getNextFridayOfWeek(LocalDate date) {
        LocalDate nDate;

        if (date.getDayOfWeek() < DateTimeConstants.FRIDAY) {
            nDate = date.withDayOfWeek(DateTimeConstants.FRIDAY);
        } else if (date.getDayOfWeek() == DateTimeConstants.FRIDAY) {
            nDate = date;
        } else {
            nDate = date.plusWeeks(1).withDayOfWeek(DateTimeConstants.FRIDAY);
        }

        return nDate;
    }

    public static LocalDate resolveSystemDate(LocalDate date) {
        return date == null ? LocalDate.now() : date;
    }

    public static boolean compareDates(LocalDate effectiveFrom, LocalDate effectiveTo) {

        if (effectiveTo.isBefore(effectiveFrom)) {
            return false;
        } else {
            return true;
        }
    }
}


