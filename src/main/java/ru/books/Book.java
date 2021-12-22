package ru.books;


public class Book {
    private int book_id;
    private String urlPDF;
    private String url_req;
    private String image_source;

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getUrlPDF() {
        return urlPDF;
    }

    public void setUrlPDF(String urlPDF) {
        this.urlPDF = urlPDF;
    }

    public String getUrl_req() {
        return url_req;
    }

    public void setUrl_req(String url_req) {
        this.url_req = url_req;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }
    public String message_delete(){
        return "Нажмите сюда, чтобы удалить данную книгу у которой ссылка : " + urlPDF.substring(0,16);
    }
}
