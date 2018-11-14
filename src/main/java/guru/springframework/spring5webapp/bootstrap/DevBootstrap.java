package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.model.repositories.AuthorRepository;
import guru.springframework.spring5webapp.model.repositories.BookRepository;
import guru.springframework.spring5webapp.model.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        Publisher publisher = new Publisher();
        publisher.setName("foo");
        publisherRepository.save(publisher);

        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        Publisher publisher1 = new Publisher("Publisher1", "Dublin");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
//        publisher1.getPublishers().add(publisher1);

        authorRepository.save(eric);
        bookRepository.save(ddd);
//        publisherRepository.save(publisher1);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "2345", publisher);
        Publisher publisher2 = new Publisher("Publisher2", "Cork");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
//        publisher2.getPublishers().add(publisher1);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
//        publisherRepository.save(publisher2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
