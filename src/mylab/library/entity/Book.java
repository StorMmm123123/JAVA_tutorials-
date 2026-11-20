package mylab.library.entity;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private int publishYear;
    private boolean isAvailable;

    // 기본 생성자
    public Book() {
        this.isAvailable = true;
    }

    // 매개변수가 있는 생성자
    public Book(String title, String author,
                String isbn, int publishYear) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.isAvailable = true;
    }

    //getter 4ㄱ개  setter 4개 
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public int getPublishYear() {
        return publishYear;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    
    
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

   
    public boolean checkOut() {
        if (!isAvailable) {
            return false;
        }

        isAvailable = false;
        return true;
    }

   
    public void returnBook() {
        isAvailable = true;
    }
//override 해서 만들기 toString 원래 object 에 있던거임 
    @Override 
    public String toString() {
        return "책 제목: " + title
            + "\t저자: " + author
            + "\tISBN: " + isbn
            + "\t출판년도: " + publishYear
            + "\t대출 가능 여부: "
            + (isAvailable ? "가능" : "대출 중");
    }
}