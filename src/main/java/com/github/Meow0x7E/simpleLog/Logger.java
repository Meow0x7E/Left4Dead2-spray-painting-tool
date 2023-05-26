package com.github.Meow0x7E.simpleLog;

/**
 * 简单日志系统
 */
public class Logger {
    private static Logger logger = null;
    private final LogLevel level;
    private final String name;

    /**
     * 构造函数
     *
     * @param level 日志系统的日志级别（输出低于该级别的日志信息将不会输出）
     */
    private Logger(LogLevel level) {
        this.level = level;
        this.name = null;
    }

    /**
     * 构造函数
     *
     * @param level 日志系统的日志级别（输出低于该级别的日志信息将不会输出）
     * @param name  自定义一个在日志级别后出现的名称
     */
    private Logger(LogLevel level, String name) {
        this.level = level;
        this.name = "[" + name + "]";
    }

    /**
     * 构造函数
     *
     * @param level 日志系统的日志级别（输出低于该级别的日志信息将不会输出）
     * @param c     使一个类名在日志级别后出现
     */
    private Logger(LogLevel level, Class<?> c) {
        this.level = level;
        this.name = "[" + c.getSimpleName() + "] ";
    }

    /**
     * 获取唯一Logger对象的静态方法
     *
     * @param level 日志系统的日志级别（输出低于该级别的日志信息将不会输出）
     * @return Logger对象
     */
    public static Logger getLogger(LogLevel level) {
        if (logger == null) {
            logger = new Logger(level);
        }
        return logger;
    }

    /**
     * 获取唯一Logger对象的静态方法
     *
     * @param level 日志系统的日志级别（输出低于该级别的日志信息将不会输出）
     * @param name  自定义一个在日志级别后出现的名称
     * @return Logger对象
     */
    public static Logger getLogger(LogLevel level, String name) {
        if (logger == null) {
            logger = new Logger(level, name);
        }
        return logger;
    }

    /**
     * 获取唯一Logger对象的静态方法
     *
     * @param level 日志系统的日志级别（输出低于该级别的日志信息将不会输出）
     * @param c     使一个类名在日志级别后出现
     * @return Logger对象
     */
    public static Logger getLogger(LogLevel level, Class<?> c) {
        if (logger == null) {
            logger = new Logger(level, c);
        }
        return logger;
    }

    /**
     * 输出debug级别的日志信息
     *
     * @param newline 是否换行
     * @param message 日志消息
     */
    public void debug(Boolean newline, String message) {
        if (this.level.compareTo(LogLevel.DEBUG) <= 0) {
            if (newline) {
                System.out.println("[DEBUG] " + name + message);
            } else {
                System.out.print("[DEBUG] " + name + message);
            }
        }
    }

    /**
     * 输出info级别的日志信息
     *
     * @param newline 是否换行
     * @param message 日志消息
     */
    public void info(Boolean newline, String message) {
        if (this.level.compareTo(LogLevel.INFO) <= 0) {
            if (newline) {
                System.out.println("[INFO] " + name + message);
            } else {
                System.out.print("[INFO] " + name + message);
            }
        }
    }

    /**
     * 输出warn级别的日志信息
     *
     * @param newline 是否换行
     * @param message 日志消息
     */
    public void warn(Boolean newline, String message) {
        if (this.level.compareTo(LogLevel.WARN) <= 0) {
            if (newline) {
                System.out.println("[WARN] " + name + message);
            } else {
                System.out.print("[WARN] " + name + message);
            }
        }
    }

    /**
     * 输出error级别的日志信息
     *
     * @param newline 是否换行
     * @param message 日志消息
     */
    public void error(Boolean newline, String message) {
        if (this.level.compareTo(LogLevel.ERROR) <= 0) {
            if (newline) {
                System.out.println("[ERROR] " + name + message);
            } else {
                System.out.print("[ERROR] " + name + message);
            }
        }
    }

    /**
     * 输出fatal级别的日志信息
     *
     * @param newline 是否换行
     * @param message 日志消息
     */
    public void fatal(Boolean newline, String message) {
        if (this.level.compareTo(LogLevel.FATAL) <= 0) {
            if (newline) {
                System.out.println("[FATAL] " + name + message);
            } else {
                System.out.print("[FATAL] " + name + message);
            }
        }
    }

    /**
     * 输出debug级别的日志信息（自动换行）
     *
     * @param message 日志消息
     */
    public void debug(String message) {
        debug(true, message);
    }

    /**
     * 输出info级别的日志信息（自动换行）
     *
     * @param message 日志消息
     */
    public void info(String message) {
        info(true, message);
    }

    /**
     * 输出warn级别的日志信息（自动换行）
     *
     * @param message 日志消息
     */
    public void warn(String message) {
        warn(true, message);
    }

    /**
     * 输出error级别的日志信息（自动换行）
     *
     * @param message 日志消息
     */
    public void error(String message) {
        error(true, message);
    }

    /**
     * 输出fatal级别的日志信息（自动换行）
     *
     * @param message 日志消息
     */
    public void fatal(String message) {
        fatal(true, message);
    }

    /**
     * 输出debug级别的格式化日志信息
     *
     * @param newline 是否换行
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void debug(Boolean newline, String message, Object... args) {
        if (this.level.compareTo(LogLevel.DEBUG) <= 0) {
            if (newline) {
                System.out.println("[DEBUG] " + name + String.format(message, args));
            } else {
                System.out.print("[DEBUG] " + name + String.format(message, args));
            }
        }
    }

    /**
     * 输出info级别的格式化日志信息
     *
     * @param newline 是否换行
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void info(Boolean newline, String message, Object... args) {
        if (this.level.compareTo(LogLevel.INFO) <= 0) {
            if (newline) {
                System.out.println("[INFO] " + name + String.format(message, args));
            } else {
                System.out.print("[INFO] " + name + String.format(message, args));
            }
        }
    }

    /**
     * 输出warn级别的格式化日志信息
     *
     * @param newline 是否换行
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void warn(Boolean newline, String message, Object... args) {
        if (this.level.compareTo(LogLevel.WARN) <= 0) {
            if (newline) {
                System.out.println("[WARN] " + name + String.format(message, args));
            } else {
                System.out.print("[WARN] " + name + String.format(message, args));
            }
        }
    }

    /**
     * 输出error级别的格式化日志信息
     *
     * @param newline 是否换行
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void error(Boolean newline, String message, Object... args) {
        if (this.level.compareTo(LogLevel.ERROR) <= 0) {
            if (newline) {
                System.out.println("[ERROR] " + name + String.format(message, args));
            } else {
                System.out.print("[ERROR] " + name + String.format(message, args));
            }
        }
    }

    /**
     * 输出fatal级别的格式化日志信息
     *
     * @param newline 是否换行
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void fatal(Boolean newline, String message, Object... args) {
        if (this.level.compareTo(LogLevel.FATAL) <= 0) {
            if (newline) {
                System.out.println("[FATAL] " + name + String.format(message, args));
            } else {
                System.out.print("[FATAL] " + name + String.format(message, args));
            }
        }
    }

    /**
     * 输出debug级别的格式化日志信息（自动换行）
     *
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void debug(String message, Object... args) {
        debug(true, message, args);
    }

    /**
     * 输出info级别的格式化日志信息（自动换行）
     *
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void info(String message, Object... args) {
        info(true, message, args);
    }

    /**
     * 输出warn级别的格式化日志信息（自动换行）
     *
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void warn(String message, Object... args) {
        warn(true, message, args);
    }

    /**
     * 输出error级别的格式化日志信息（自动换行）
     *
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void error(String message, Object... args) {
        error(true, message, args);
    }

    /**
     * 输出fatal级别的格式化日志信息（自动换行）
     *
     * @param message 要格式化的消息
     * @param args    格式化参数
     */
    public void fatal(String message, Object... args) {
        fatal(true, message, args);
    }
}