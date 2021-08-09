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
}
