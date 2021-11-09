package ru.books;

public class Book {
    private String urlPDF ;
    private String describe_source;
    private String image_source;

    public Book() {
    }

    public String getUrlPDF() {
        return urlPDF;
    }

    public void setUrlPDF(String urlPDF) {
        this.urlPDF = urlPDF;
    }

    public String getDescribe_source() {
        return describe_source;
    }

    public void setDescribe_source(String describe_source) {
        this.describe_source = describe_source;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }
}
