package io.centipod.common.logging;


import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/**
 * This class is a wrapper for the standard Logback logger
 * @author Christian Schuit, Centipod
 *
 */
public class LogUtil {

    /*
     * Logger instance
     */
    private Logger logger;

    /**
     * Returns a new logger for given name
     * @param type A logger name
     * @return
     */
    public static LogUtil get(String type) {

        return new LogUtil((Logger) LoggerFactory.getLogger(type));
    }

    /**
     * Returns a new logger for given type
     * @param type A class
     * @return
     */
    public static LogUtil get(Class<?> type) {

        return new LogUtil((Logger) LoggerFactory.getLogger(type));
    }

    /**
     * Constructor with limited visibility
     * @param logger
     */
    private LogUtil(Logger logger) {

        this.logger = logger;
    }

    /**
     * Returns logger implementation instance
     * @return
     */
    public Logger get() {

        return this.logger;
    }

    /**
     * Change log level for given logger
     * @param type
     * @param level
     */
    public void setLevel(Level level) {

        synchronized (this.logger) {

            this.logger.setLevel(level);
        }
    }

    /**
     * Change the pattern for all appenders for this logger
     * @param pattern
     */
    public void setPattern(String pattern) {

        synchronized(this.logger) {

            this.logger.iteratorForAppenders().forEachRemaining(a -> a.getContext().putObject("pattern", pattern));
        }
    }

    /**
     * Prints the current stack trace for debugging purposes
     */
    public void dump() {

        if (this.isTraceEnabled()) {

            Thread.dumpStack();
        }
    }

    /**
     * Returns a boolean indicating whether log level is TRACE
     * @return
     */
    public boolean isTraceEnabled() {

        return this.logger.isTraceEnabled();
    }

    /**
     * Returns a boolean indicating whether log level is DEBUG
     * @return
     */
    public boolean isDebugEnabled() {

        return this.logger.isDebugEnabled();
    }

    /**
     * Prints a log message to the relevant logger and appender if level is TRACE
     * @param message Logging message
     * @param args Substitution values
     */
    public String trace(String message, Object...args) {

        if (this.logger.isTraceEnabled()) {

            // only format if arguments provided, otherwise it breaks if message contains "{}"
            if ((args != null) && (args.length > 0)) {

                message = String.format(message, args);
            }

            this.logger.trace(message);
        }
        return message;
    }

    /**
     * Prints a log message to the relevant logger and appender if level is DEBUG
     * @param message Logging message
     * @param args Substitution values
     */
    public String debug(String message, Object...args) {

        if (this.logger.isDebugEnabled()) {

            // only format if arguments provided, otherwise it breaks if message contains "{}"
            if ((args != null) && (args.length > 0)) {

                message = String.format(message, args);
            }

            this.logger.debug(message);
        }
        return message;
    }

    /**
     * Prints a log message to the relevant logger and appender if level is INFO
     * @param message Logging message
     * @param args Substitution values
     */
    public String info(String message, Object...args) {

        // only format if arguments provided, otherwise it breaks if message contains "{}"
        if ((args != null) && (args.length > 0)) {

            message = String.format(message, args);
        }

        this.logger.info(message);
        return message;
    }

    /**
     * Prints a log message to the relevant logger and appender if level is WARN
     * @param message Logging message
     * @param args Substitution values
     */
    public String warn(String message, Object...args) {

        // only format if arguments provided, otherwise it breaks if message contains "{}"
        if ((args != null) && (args.length > 0)) {

            message = String.format(message, args);
        }

        this.logger.warn(message);
        return message;
    }

    /**
     * Prints a log message to the relevant logger and appender if level is ERROR
     * @param message Logging message
     * @param args Substitution values
     */
    public String error(String message, Object...args) {

        // only format if arguments provided, otherwise it breaks if message contains "{}"
        if ((args != null) && (args.length > 0)) {

            message = String.format(message, args);
        }

        this.logger.error(message);
        return message;
    }

    /**
     * Prints a log message to the relevant logger and appender if level is TRACE
     * @param e Exception
     * @param message Logging message
     * @param args Substitution values
     * @return Returns the exception to be thrown again or raised
     */
    public <E extends Throwable> E error(E e, String message, Object...args) {

        if (args.length > 0) {

            message = String.format(message, args);
        }
        this.logger.error(message, e);
        return e;
    }

    /**
     * Prints a log message to the relevant logger and appender if level is TRACE
     *
     * @param e Exception
     * @return Returns the exception to be thrown again or raised
     */
    public <E extends Throwable> E error(E e) {

        this.logger.error(e.getMessage(), e);
        return e;
    }

    /**
     * Prints a log message to the relevant logger and appender
     *
     * @param e Exception
     * @return Returns the exception to be thrown again or raised
     */
    public <E extends Throwable> E warn(E e) {

        this.logger.warn(e.getMessage(), e);
        return e;
    }
}
