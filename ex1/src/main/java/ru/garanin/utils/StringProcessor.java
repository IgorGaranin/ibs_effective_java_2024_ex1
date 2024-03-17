package ru.garanin.utils;

import org.apache.commons.lang3.StringUtils;

public class StringProcessor {

    private static String delimiter = StringUtils.EMPTY;

    public String joinElementsWithDelimiter(Object... elements) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < elements.length; i++) {
            result.append(elements[i].toString());

            if (i < elements.length - 1) {
                result.append(delimiter);
            }
        }
        return result.toString();
    }

    private StringProcessor(Builder builder){
        this.delimiter = builder.delimiter;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private String delimiter = StringUtils.EMPTY;

        public Builder setDelimiter(String val) {
            this.delimiter = val;
            return this;
        }

        public StringProcessor build() {
            return new StringProcessor(this);
        }

    }

}
