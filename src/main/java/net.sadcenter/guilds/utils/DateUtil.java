package net.sadcenter.guilds.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {
    public static long getTime(final String string) {
        if (string == null || string.isEmpty()) {
            return 0L;
        }
        final Stack<Character> type = new Stack<>();
        final StringBuilder value = new StringBuilder();
        boolean calc = false;
        long time = 0L;
        for (char c : string.toCharArray()) {
            switch (c) {
                case 'd':
                case 'h':
                case 'm':
                case 's': {
                    if (!calc) {
                        type.push(c);
                    }
                    try {
                        final long i = Integer.parseInt(value.toString());
                        switch (type.pop()) {
                            case 'd': {
                                time += i * 86400000L;
                                break;
                            }
                            case 'h': {
                                time += i * 3600000L;
                                break;
                            }
                            case 'm': {
                                time += i * 60000L;
                                break;
                            }
                            case 's': {
                                time += i * 1000L;
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        return time;
                    }
                    type.push(c);
                    calc = true;
                    break;
                }
                default: {
                    value.append(c);
                    break;
                }
            }
        }
        return time;
    }

    public static @NotNull
    String getDate(final @NotNull Date date) {
        final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(date.getTime());
    }

    public static @NotNull
    String getDate(final long time) {
        final Date date = new Date(time);
        final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(date.getTime());
    }

    public static int getSeconds(final String string) {
        if (string == null || string.isEmpty()) {
            return 0;
        }
        final Stack<Character> type = new Stack<>();
        final StringBuilder value = new StringBuilder();
        boolean calc = false;
        int time = 0;
        char[] charArray;
        for (int length = (charArray = string.toCharArray()).length, j = 0; j < length; ++j) {
            final char c = charArray[j];
            switch (c) {
                case 'd':
                case 'h':
                case 'm':
                case 's': {
                    if (!calc) {
                        type.push(c);
                    }
                    try {
                        final long i = Integer.parseInt(value.toString());
                        switch (type.pop()) {
                            case 'd': {
                                time += (int) (i * 86400L);
                                break;
                            }
                            case 'h': {
                                time += (int) (i * 3600L);
                                break;
                            }
                            case 'm': {
                                time += (int) (i * 60L);
                                break;
                            }
                            case 's': {
                                time += (int) (i);
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        return time;
                    }
                    type.push(c);
                    calc = true;
                    break;
                }
                default: {
                    value.append(c);
                    break;
                }
            }
        }
        return time;
    }

    public static @NotNull String getDate(final int seconds) {
        final Calendar c = new GregorianCalendar();
        c.add(Calendar.SECOND, seconds);
        final SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(c.getTime());
    }

    public static long getLong(final int time) {
        final Calendar c = new GregorianCalendar();
        c.add(Calendar.SECOND, time);
        return c.getTimeInMillis() - System.currentTimeMillis();
    }

    public static @NotNull String getDurationBreakdown(long millis) {
        if (millis == 0L) {
            return "0 >";
        }
        final long days = TimeUnit.MILLISECONDS.toDays(millis);
        if (days > 0L) {
            millis -= TimeUnit.DAYS.toMillis(days);
        }
        final long hours = TimeUnit.MILLISECONDS.toHours(millis);
        if (hours > 0L) {
            millis -= TimeUnit.HOURS.toMillis(hours);
        }
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        if (minutes > 0L) {
            millis -= TimeUnit.MINUTES.toMillis(minutes);
        }
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        if (seconds > 0L) {
            millis -= TimeUnit.SECONDS.toMillis(seconds);
        }
        final StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days);
            final long i = days % 10L;
            if (i == 1L) {
                sb.append(" dzien ");
            } else {
                sb.append(" dni ");
            }
        }
        if (hours > 0L) {
            sb.append(hours);
            final long i = hours % 10L;
            if (i == 1L) {
                sb.append(" godzina ");
            } else if (i > 6L) {
                sb.append(" godzin ");
            } else {
                sb.append(" godziny ");
            }
        }
        if (minutes > 0L) {
            sb.append(minutes);
            final long i = minutes % 10L;
            if (i == 1L) {
                sb.append(" minuta ");
            } else if (i > 6L) {
                sb.append(" minut ");
            } else {
                sb.append(" minuty ");
            }
        }
        if (seconds > 0L) {
            sb.append(seconds);
            final long i = seconds % 10L;
            if (i == 1L) {
                sb.append(" sekunda ");
            } else if (i > 6L) {
                sb.append(" sekund ");
            } else {
                sb.append(" sekundy ");
            }
        }
       /* if (millis > 0L) {
            sb.append(millis);
            final long i = millis % 10L;
            if (i == 1L) {
                sb.append(" milisekunda ");
            } else if (i < 5L) {
                sb.append(" milisekundy ");
            } else {
                sb.append(" milisekund ");
            }
        }

        */
        return sb.toString();
    }

    public static @NotNull
    String getDurationBreakdownShort(long millis) {
        if (millis == 0L) {
            return "0";
        }
        final long days = TimeUnit.MILLISECONDS.toDays(millis);
        if (days > 0L) {
            millis -= TimeUnit.DAYS.toMillis(days);
        }
        final long hours = TimeUnit.MILLISECONDS.toHours(millis);
        if (hours > 0L) {
            millis -= TimeUnit.HOURS.toMillis(hours);
        }
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        if (minutes > 0L) {
            millis -= TimeUnit.MINUTES.toMillis(minutes);
        }
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        if (seconds > 0L) {
            millis -= TimeUnit.SECONDS.toMillis(seconds);
        }
        final StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days);
            sb.append("d").append(hours > 0L ? ", " : " ");
        }
        if (hours > 0L) {
            sb.append(hours);
            sb.append("h").append(minutes > 0L ? ", " : " ");
        }
        if (minutes > 0L) {
            sb.append(minutes);
            sb.append("min").append(seconds > 0L ? ", " : " ");
        }
        if (seconds > 0L) {
            sb.append(seconds);
            sb.append("s ");
        }
        if (seconds == 0L) {
            sb.append(millis);
            sb.append("ms");
        }
        return sb.toString();
    }

    public static @NotNull
    String getDurationBreakdown(int second) {
        if (second == 0) {
            return "0";
        }
        final long days = TimeUnit.SECONDS.toDays(second);
        if (days > 0L) {
            second -= (int) TimeUnit.DAYS.toSeconds(days);
        }
        final long hours = TimeUnit.SECONDS.toHours(second);
        if (hours > 0L) {
            second -= (int) TimeUnit.HOURS.toSeconds(hours);
        }
        final long minutes = TimeUnit.SECONDS.toMinutes(second);
        if (minutes > 0L) {
            second -= (int) TimeUnit.MINUTES.toSeconds(minutes);
        }
        long seconds = TimeUnit.SECONDS.toSeconds(second);
        if (seconds > 0L) {
            seconds -= TimeUnit.SECONDS.toSeconds(seconds);
        }
        final StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days);
            final long i = days % 10L;
            if (i == 1L) {
                sb.append(" dzien ");
            } else {
                sb.append(" dni ");
            }
        }
        if (hours > 0L) {
            sb.append(hours);
            final long i = hours % 10L;
            if (i == 1L) {
                sb.append(" godzine ");
            } else if (i < 5L) {
                sb.append(" godziny ");
            } else {
                sb.append(" godzin ");
            }
        }
        if (minutes > 0L) {
            sb.append(minutes);
            final long i = minutes % 10L;
            if (i == 1L) {
                sb.append(" minute ");
            } else if (i < 5L) {
                sb.append(" minuty ");
            } else {
                sb.append(" minut ");
            }
        }
        if (seconds > 0L) {
            sb.append(seconds);
            final long i = seconds % 10L;
            if (i == 1L) {
                sb.append(" sekunde ");
            } else if (i < 5L) {
                sb.append(" sekundy ");
            } else {
                sb.append(" sekund ");
            }
        }
        return sb.toString();
    }

    public static @NotNull
    String getDurationBreakdownShort(int second) {
        if (second == 0) {
            return "0";
        }
        final long days = TimeUnit.SECONDS.toDays(second);
        if (days > 0L) {
            second -= (int) TimeUnit.DAYS.toSeconds(days);
        }
        final long hours = TimeUnit.SECONDS.toHours(second);
        if (hours > 0L) {
            second -= (int) TimeUnit.HOURS.toSeconds(hours);
        }
        final long minutes = TimeUnit.SECONDS.toMinutes(second);
        if (minutes > 0L) {
            second -= (int) TimeUnit.MINUTES.toSeconds(minutes);
        }
        final StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days);
            sb.append("d ");
        }
        if (hours > 0L) {
            sb.append(hours);
            sb.append("h ");
        }
        if (minutes > 0L) {
            sb.append(minutes);
            sb.append("m ");
        }
        if (second > 0) {
            sb.append(second);
            sb.append("s ");
        }
        return sb.toString();
    }
}
