package org.im.quotes.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection = "Quotes")
public class QuotesModel {

    private String quote;
    private String author;

    public QuotesModel() {

    }

    public QuotesModel(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "QuotesModel{" +
                "quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
