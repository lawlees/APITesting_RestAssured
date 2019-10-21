# APITesting_RestAssured

## Tools Used:

***Framework: testng <br/> Rest API Testing: rest-assured by com.jayway.restassured <br/> Validate Response Body: json-path by com.jayway.restassured***

> Online API Provider: https://jsonplaceholder.typicode.com/

You can also set up the your own API server using Node.JS. Here is the instruction on how to do it:
https://github.com/typicode/json-server

For the package TypiCodePost, the default json server is used. Follow the instruction above to start the server. 
1. You don't need any server to execute the class: JSONStuff and it utilizes the API resources:  https://jsonplaceholder.typicode.com/
2. Befor executing the class: JSONServerRequest, you must set up the server and start it. 3000 is the default port used by JSON server and the default endpoint is: http://localhost:3000.

For the package TypicodeComplexPost, the JSON Server is modified. Before executing the test in this package,
1. Create a new db1.json with the below data:
```
{
  "posts": [
    {
      "id": 1,
      "title": "JSON Server",
      "author": "Typicode",
      "info": [
        {
          "email": "test@email.com",
          "phone": "123456"
        },
        {
          "email": "email@test.com",
          "phone": "654321"
        }
      ]
    }
  ]
}
```
2. Start JSON server: json-server --watch db1.json
3. Execute the test in this package.s
