package co.edureka.edurekasession2;

/**
 * Created by ishantkumar on 28/01/18.
 */

public class Book {

    String name;
    String price;
    String author;

    public Book(){

    }

    public Book(String name, String price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
