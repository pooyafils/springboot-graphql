package com.example.grapthql.contoller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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

    @PostMapping("/fidnbyid")
    public ExecutionResult executeGraphQL(@RequestBody Map<String, Object> request) {
        // Execute the GraphQL query using the provided request
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query((String) request.get("query"))
                .variables((Map<String, Object>) request.get("variables"))
                 // You can set a context if needed
                .build();

        ExecutionResult executionResult = graphQL.execute(executionInput);
        return executionResult;
    }
}
