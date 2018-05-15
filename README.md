# tutorial-microservices

Tutorials about microservices.

## References

__Spring Boot__

Full doc: [https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/]

## Notes

All maven modules have those coordinates:

```
<groupId>com.danidemi.tutorial</groupId>
<artifactId>ms-{slug}</artifactId>
<version>0.0.1-SNAPSHOT</version>
```

All java packages are defined as: 

```
package ms.{technology-slug}.{...}
```

This is not compliant to the usual Java naming guidelines, however the goal is to have code that is not too 
much cluttered with details and that fits gracefully in an editor zoomed enough to be shown with a projector. 
So... short code is welcome.

All Spring Boot configuration files are defined in `.yaml` format.
