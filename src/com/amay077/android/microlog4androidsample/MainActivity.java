package com.amay077.android.microlog4androidsample;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.appender.FileAppender;
import com.google.code.microlog4android.appender.LogCatAppender;
import com.google.code.microlog4android.format.PatternFormatter;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    // singleton logger object
    static public Logger logger = LoggerFactory.getLogger();

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // initialize logger
        PatternFormatter formatter = new PatternFormatter();
        formatter.setPattern("%d{ISO8601} [%P] %m %T");
        logger.setLevel(Level.INFO);
        // write to LogCat
        LogCatAppender logCatAppender = new LogCatAppender();
        logCatAppender.setFormatter(formatter);
        logger.addAppender(logCatAppender);
        // write to text file of SD-card.(need WRITE_EXTERNAL_STORAGE permission)
        FileAppender fileAppender = new FileAppender();
        fileAppender.setAppend(true);
        fileAppender.setFileName("microlog4android.log");
        fileAppender.setFormatter(formatter);
        logger.addAppender(fileAppender);

        // test logging
        logger.debug("debug");  // <- no logging by level
        logger.info("information");
        logger.warn("warning");
        logger.error("error");
        logger.fatal("fatal");
    }
}