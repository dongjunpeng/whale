package com.buterfleoge.whale.type.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.util.Assert;

/**
 *
 * @author xiezhenzong
 *
 */
public class ImagePathFomatter implements Formatter<String> {

    private String prefix;

    public ImagePathFomatter(String prefix) {
        Assert.hasText(prefix, "image path prefix can't be empty");
        this.prefix = prefix;
    }

    @Override
    public String print(String object, Locale locale) {
        return prefix + object;
    }

    @Override
    public String parse(String text, Locale locale) throws ParseException {
        return text;
    }

}
