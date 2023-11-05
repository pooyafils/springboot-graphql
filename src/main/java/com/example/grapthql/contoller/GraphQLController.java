package com.example.grapthql.contoller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping("/graphql")
    public Map<String, Object> execute(@RequestBody String query) {
        ExecutionResult executionResult = graphQL.execute(query);
        return executionResult.toSpecification();
    }
}
