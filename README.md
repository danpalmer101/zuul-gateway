# zuul-gateway
Example Zuul Gateway implementation with a sample route.

The project contains a custom Filter that can add static request headers before forwarding the request.

## Run

The following command will run the gateway on the port specified in `application.yml` (8098):

```
mvn spring-boot:run
```

Alternative `application.yml` files can be used with spring profiles, and options can be overridden with command line arguments or an additional properties/yaml file.
