package com.example.grapthql.config;

import com.example.grapthql.model.Book;
import com.example.grapthql.repository.BookRepository;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Configuration
public class GraphQLConfiguration {


    @Autowired
    private BookRepository bookRepository;

    @Bean
    public GraphQLSchema graphQLSchema() throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(new ClassPathResource("books.graphql").getInputStream());
        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetcher("allBooks", allBooksFetcher()).dataFetcher("findOne", bookByIdFetcher()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createBook", createBookFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("publisher", publisherFetcher()))

                .build();
        return new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

    private DataFetcher<Book> bookByIdFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookRepository.findById(Integer.parseInt(bookId)); // Fetch the book by its ID
        };
    }



    private DataFetcher<List<Book>> allBooksFetcher() {
        return dataFetchingEnvironment -> (List<Book>) bookRepository.findAll();
    }

    private DataFetcher<Book> createBookFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, Object> input = dataFetchingEnvironment.getArgument("input");
            String isbn = (String) input.get("isbn");
            String title = (String) input.get("title");
            String publisher = (String) input.get("publisher");
            Book newBook = new Book(isbn, title, publisher);
            return bookRepository.save(newBook);
        };
    }

    private DataFetcher<String> publisherFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            return book.getPublisher();
        };
    }

}

