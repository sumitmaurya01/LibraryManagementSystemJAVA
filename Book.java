public class Book {
    final private String isbn;
    final private String name;
    final private String author;
    public Book(String name, String author, String isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }
    public String getName(){
        return name;
    }
    public String getAuthor(){
        return author;
    }
    public String getIsbn(){
        return isbn;
    }
    public String toFileString(){
        return name+" , "+author+" , "+isbn;
    }
    @Override
    public String toString(){
        return " 📚 "+name+" by "+author+" ( isbn: "+isbn+" )";
    }
}
