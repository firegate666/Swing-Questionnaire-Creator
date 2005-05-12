package de.sdavids.dataobjects.common;
// Imports expanded by Importifier - http://www.javadude.com/tools/importifier     // $IMPORTIFIER-1$
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuffer;
import de.sdavids.util.*;

public class Time {
    private final int hours_;
    private final int minutes_;
    private final int seconds_;

public Time(int hours, int minutes, int seconds) throws IllegalArgumentException {
    checkConstraints(hours, minutes,seconds);
    hours_ = hours;
    minutes_ = minutes;
    seconds_ = seconds;
}


void checkConstraints(int hours, int minutes, int seconds) throws IllegalArgumentException {
    if ((hours < 0)
        || (seconds < 0) || (seconds > 59)
        || (minutes < 0) || (minutes > 59)) throw new IllegalArgumentException();
}


public final int getHours() {
    return hours_;
}


public final int getMinutes() {
    return minutes_;
}


public final int getSeconds() {
    return seconds_;
}


public String toString() {
    StringBuffer result = new StringBuffer();

    if (hours_ / 10 == 0) result.append("0");
    result.append(hours_);
    result.append(":");
    if (minutes_ / 10 == 0) result.append("0");
    result.append(minutes_);
    result.append(":");
    if (seconds_ / 10 == 0) result.append("0");
    result.append(seconds_);

    return result.toString();
}
}