package com.buterfleoge.whale.type.formatter;

import java.util.Collections;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;


/**
 *
 * @author xiezhenzong
 *
 */
public class ImagePathAnnotationFormatterFactory implements AnnotationFormatterFactory<ImagePathFormat> {

    private String imgHostUrl;

    /**
     * @param prefix
     */
    public ImagePathAnnotationFormatterFactory(String imgHostUrl) {
        this.imgHostUrl = imgHostUrl;
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.<Class<?>> singleton(String.class);
    }

    @Override
    public Printer<String> getPrinter(ImagePathFormat annotation, Class<?> fieldType) {
        return ceateImagePathFormatter(annotation);
    }

    @Override
    public Parser<String> getParser(ImagePathFormat annotation, Class<?> fieldType) {
        return ceateImagePathFormatter(annotation);
    }

    /**
     * @param annotation
     * @return
     */
    protected Formatter<String> ceateImagePathFormatter(ImagePathFormat annotation) {
        String prefix = imgHostUrl + annotation.prefix().getPrefix();
        return new ImagePathFomatter(prefix);
    }

}
