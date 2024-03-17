package ru.garanin.ex1;

import ru.garanin.utils.StringProcessor;

public class App {
    public static void main(String[] args) {
        System.out.println(StringProcessor.newBuilder()
                .setDelimiter("<>").build()
                .joinElementsWithDelimiter(1, 2, 3));

        System.out.println(StringProcessor.newBuilder()
                .setDelimiter("/").build()
                .joinElementsWithDelimiter("Abc", "efg"));


        System.out.println(StringProcessor.newBuilder()
                .build()
                .joinElementsWithDelimiter(1, 2));

        System.out.println(StringProcessor.newBuilder()
                .build()
                .joinElementsWithDelimiter("asd", "Abc", "efg"));

    }
}
