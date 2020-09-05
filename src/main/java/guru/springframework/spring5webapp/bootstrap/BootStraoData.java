package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStraoData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStraoData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book book = new Book("my book", "123456");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        Publisher publisher = new Publisher();
        publisher.setName("publisher name");
        publisher.setCity("city");
        publisher.setState("state");
        publisherRepository.save(publisher);

        book.setPublisher(publisher);
        publisher.getBooks().add(book);


        authorRepository.save(eric);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author toto = new Author("toto", "titi");
        Book secondBook = new Book("second book", "isbn book");

        toto.getBooks().add(secondBook);
        secondBook.getAuthors().add(toto);
        secondBook.setPublisher(publisher);
        publisher.getBooks().add(secondBook);

        authorRepository.save(toto);
        bookRepository.save(secondBook);
        publisherRepository.save(publisher);

        System.out.println("number of books :        " + bookRepository.count());
        System.out.println("publisher number of books :        " + publisher.getBooks().size());
    }
}
