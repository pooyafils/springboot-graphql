# springboot-graphql
this application is example of springboot with graphql
## requirement
installed postgres on your computer
## how to send request for the api
* http://localhost:8080/graphql<br/>
 `` {
        allBooks {
            isbn
            title
            publisher
        }
} ``
* http://localhost:8080/fidnbyid<br/>
``{
    "query": "query GetBookById($id: ID!) { findOne(id: $id) { isbn title publisher } }",
    "variables": {
        "id": 1  
    }
}``
