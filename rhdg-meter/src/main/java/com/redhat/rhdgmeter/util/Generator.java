package com.redhat.rhdgmeter.util;

import com.github.javafaker.Faker;
import com.redhat.rhdgmeter.model.Book;


public class Generator {

    private static Faker faker = new Faker();

    public static Book newBook(){

        Book book = new Book();

        book.setId(faker.letterify("????????"));
        book.setTitle(faker.book().title());
        book.setAuthor(faker.book().author());
        
        return book;
    }


}
