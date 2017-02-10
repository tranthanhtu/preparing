/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.tranthanhtu.sunshine.utils;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import vn.tranthanhtu.sunshine.R;

/**
 * Class for handling date conversions that are useful for Sunshine.
 */
public final class SunshineDateUtils {

    public static final String TAG = SunshineDateUtils.class.toString();

    public static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);

    public static long getNormalizedUtcDateForToday() {

        long utcNowMillis = System.currentTimeMillis();

        TimeZone currentTimeZone = TimeZone.getDefault();

        long gmtOffsetMillis = currentTimeZone.getOffset(utcNowMillis);

        long timeSinceEpochLocalTimeMillis = utcNowMillis + gmtOffsetMillis;

        long daysSinceEpochLocal = TimeUnit.MILLISECONDS.toDays(timeSinceEpochLocalTimeMillis);


        long normalizedUtcMidnightMillis = TimeUnit.DAYS.toMillis(daysSinceEpochLocal);

        return normalizedUtcMidnightMillis;
    }


    private static long elapsedDaysSinceEpoch(long utcDate) {
        return TimeUnit.MILLISECONDS.toDays(utcDate);
    }


    public static long normalizeDate(long date) {
        long daysSinceEpoch = elapsedDaysSinceEpoch(date);
        long millisFromEpochToTodayAtMidnightUtc = daysSinceEpoch * DAY_IN_MILLIS;
        return millisFromEpochToTodayAtMidnightUtc;
    }


    public static boolean isDateNormalized(long millisSinceEpoch) {
        boolean isDateNormalized = false;
        if (millisSinceEpoch % DAY_IN_MILLIS == 0) {
            isDateNormalized = true;
        }

        return isDateNormalized;
    }


    private static long getLocalMidnightFromNormalizedUtcDate(long normalizedUtcDate) {

        TimeZone timeZone = TimeZone.getDefault();

        long gmtOffset = timeZone.getOffset(normalizedUtcDate);
        long localMidnightMillis = normalizedUtcDate - gmtOffset;
        return localMidnightMillis;
    }


    public static String getFriendlyDateString(Context context, long normalizedUtcMidnight, boolean showFullDate) {


        long localDate = getLocalMidnightFromNormalizedUtcDate(normalizedUtcMidnight);

        long daysFromEpochToProvidedDate = elapsedDaysSinceEpoch(localDate);

        long daysFromEpochToToday = elapsedDaysSinceEpoch(System.currentTimeMillis());

        if (daysFromEpochToProvidedDate == daysFromEpochToToday || showFullDate) {

            String dayName = getDayName(context, localDate);
            String readableDate = getReadableDateString(context, localDate);
            if (daysFromEpochToProvidedDate - daysFromEpochToToday < 2) {

                String localizedDayName = new SimpleDateFormat("EEEE").format(localDate);
                return readableDate.replace(localizedDayName, dayName);
            } else {
                return readableDate;
            }
        } else if (daysFromEpochToProvidedDate < daysFromEpochToToday + 7) {
            /* If the input date is less than a week in the future, just return the day name. */
            return getDayName(context, localDate);
        } else {
            int flags = DateUtils.FORMAT_SHOW_DATE
                    | DateUtils.FORMAT_NO_YEAR
                    | DateUtils.FORMAT_ABBREV_ALL
                    | DateUtils.FORMAT_SHOW_WEEKDAY;

            return DateUtils.formatDateTime(context, localDate, flags);
        }
    }


    private static String getReadableDateString(Context context, long timeInMillis) {
        int flags = DateUtils.FORMAT_SHOW_DATE
                | DateUtils.FORMAT_NO_YEAR
                | DateUtils.FORMAT_SHOW_WEEKDAY;

        return DateUtils.formatDateTime(context, timeInMillis, flags);
    }

    public static String getDate(long milliSeconds) {
        SimpleDateFormat formater = new SimpleDateFormat("EEE");
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(milliSeconds);
        return formater.format(calendar.getTime());
    }

    private static String getDayName(Context context, long dateInMillis) {

        long daysFromEpochToProvidedDate = elapsedDaysSinceEpoch(dateInMillis);
        long daysFromEpochToToday = elapsedDaysSinceEpoch(System.currentTimeMillis());

        Log.d(TAG, String.format("getDayName: %s", daysFromEpochToToday));
        Log.d(TAG, "getDayName: 2" + elapsedDaysSinceEpoch(1486702800 * 1000));

        int daysAfterToday = (int) (daysFromEpochToProvidedDate - daysFromEpochToToday);

        switch (daysAfterToday) {
            case 0:
                return context.getString(R.string.today);
            case 1:
                return context.getString(R.string.tomorrow);

            default:
                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
                return dayFormat.format(dateInMillis);

        }
    }


    public static String dayStringFormat(long msecs) {
        GregorianCalendar cal = new GregorianCalendar();

        cal.setTime(new Date(msecs));

        int dow = cal.get(Calendar.DAY_OF_WEEK);

        switch (dow) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
        }

        return "Unknown";
    }
}