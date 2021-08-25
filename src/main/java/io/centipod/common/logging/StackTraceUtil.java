package io.centipod.common.logging;

import java.util.ArrayList;
import java.util.List;

public class StackTraceUtil {

    /**
     * Constructor with limited visibility
     */
    private StackTraceUtil() {}

    public static String asString(Throwable t) {

        StringBuilder buffer = new StringBuilder();
        if ((t.getStackTrace() != null) && (t.getStackTrace().length > 0)) {

            List<Throwable> causes = new ArrayList<>();
            Throwable cause = t;
            while ((cause != null) && !causes.contains(cause)) {

                causes.add(cause); // Avoid recursion
                buffer.append("Caused by: ").append(cause.getClass()).append(": ").append(cause.getMessage()).append("\n");
                for (StackTraceElement s : cause.getStackTrace()) {

                    buffer.append("     ").append(s.getClassName()).append(" ").append(s.getMethodName())
                        .append(" ").append(s.getFileName()).append(" ").append(s.getLineNumber()).append("\n");
                }
                cause = cause.getCause();
            }
        } else {

            buffer.append("No stack trace available.");
        }
        return buffer.toString();
    }

    public static List<String> asList(Throwable e) {

        List<Throwable> causes = new ArrayList<>();
        List<String> list = new ArrayList<>();
        if ((e.getStackTrace() != null) && (e.getStackTrace().length > 0)) {

            Throwable cause = e;
            while ((cause != null) && !causes.contains(cause)) {

                causes.add(cause); // To avoid recursion
                list.add("Caused by: " + cause.getClass() + ": " + cause.getMessage());
                for (StackTraceElement s : e.getStackTrace()) {

                    list.add(s.getClassName() + " " + s.getMethodName() + " " + s.getFileName() + " " + s.getLineNumber());
                }
                cause = e.getCause();
            }
        } else {

            list.add("No stack trace available.");
        }
        return list;
    }

}
