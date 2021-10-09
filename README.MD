Apache Camel based sample rest endpoint where interceptor intercept an endpoint `sampleEndpoint` of a route `direct-route"` to call condition bases `afterURI`

## To run

To run the app:

    mvn clean spring-boot:run

Then, to test the REST service's GET operation - see how it returns a JSON object:

    $ curl http://localhost:8080/services/api?afterURI=true
    {"message":"Hello, world!"}

Logs when `afterURI` passes as `true`

```
direct-route                             : Inside direct-route, now Calling SAMPLE_ENDPOINT
direct-route                             : Interceptor triggerred for SAMPLE_ENDPOINT to check condition for AFTER_URI
route208                                 : Request received at sampleEndpoint
route209                                 : afterURI was true, now executing the afterURI logic
direct-route                             : Returning final response
```

Logs when `afterURI` passes as `false`

```
direct-route                             : Inside direct-route, now Calling SAMPLE_ENDPOINT
direct-route                             : Interceptor triggerred for SAMPLE_ENDPOINT to check condition for AFTER_URI
route208                                 : Request received at sampleEndpoint
route209                                 : afterURI condition not met
direct-route                             : Returning final response
```



