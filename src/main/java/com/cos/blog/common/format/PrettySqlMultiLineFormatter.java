package com.cos.blog.common.format;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class PrettySqlMultiLineFormatter implements MessageFormattingStrategy {
    private static final Formatter FORMATTER = new BasicFormatterImpl();

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return "#" + now + " | took " + elapsed + "ms | " + category + " | connection " + connectionId + " | " + FORMATTER.format(sql) + ";";
    }
}
