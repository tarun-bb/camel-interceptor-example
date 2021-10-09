Apache Camel based sample rest endpoint where interceptor intercept an endpoint `sampleEndpoint` of a route `direct-route"` to call condition bases `afterURI`

## To run

To run the app:

    mvn clean spring-boot:run

Then, to test the REST service's GET operation - see how it returns a JSON object:

    $ curl http://localhost:8080/services/api?afterURI=true
    {"message":"Hello, world!"}

Logs when `afterURI` passes as `true`

```
2021-10-09 03:32:37.628  INFO 1064 --- [nio-8080-exec-1] direct-route                             : Inside direct-route, now Calling SAMPLE_ENDPOINT
2021-10-09 03:32:37.629  INFO 1064 --- [nio-8080-exec-1] direct-route                             : Interceptor triggerred for SAMPLE_ENDPOINT to check condition for AFTER_URI
2021-10-09 03:32:37.629  INFO 1064 --- [nio-8080-exec-1] route208                                 : Request received at sampleEndpoint
2021-10-09 03:32:37.629  INFO 1064 --- [nio-8080-exec-1] route209                                 : afterURI was true, now executing the afterURI logic
2021-10-09 03:32:37.629  INFO 1064 --- [nio-8080-exec-1] direct-route                             : Returning final response
```

Logs when `afterURI` passes as `false`

```
2021-10-09 03:33:27.192  INFO 1064 --- [nio-8080-exec-2] direct-route                             : Inside direct-route, now Calling SAMPLE_ENDPOINT
2021-10-09 03:33:27.192  INFO 1064 --- [nio-8080-exec-2] direct-route                             : Interceptor triggerred for SAMPLE_ENDPOINT to check condition for AFTER_URI
2021-10-09 03:33:27.192  INFO 1064 --- [nio-8080-exec-2] route208                                 : Request received at sampleEndpoint
2021-10-09 03:33:27.192  INFO 1064 --- [nio-8080-exec-2] route209                                 : afterURI condition not met
2021-10-09 03:33:27.192  INFO 1064 --- [nio-8080-exec-2] direct-route                             : Returning final response
```



