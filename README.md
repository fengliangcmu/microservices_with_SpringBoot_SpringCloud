# microservices_with_SpringBoot_SpringCloud
This repo stores some demo projects for quick learning of spring boot and spring cloud, covering topics like:
1. Swagger Documentation for rest APIs
2. Actuator to monitor application status
3. Basic Authentication with Spring Security
4. Spring data, JPA
5. Richardson Maturity Model for rest APIs
6. Spring Cloud Config Server
7. Client side load balancing with Ribbon
8. Eureka Naming Server
9. Zuul API gateway
10. Distributed Tracing with Zipkin and RabbitMQ
11. Spring Cloud Bus(not covered)
12. Hystrix

Some issues:
1. Eureka should use cluster mode to prevent single point failure. Lots of tutorials online.
2. Sometimes due to settings inside host file, client is not able to connect to remote eureka server, then we can configure like these: Server Side - eureka.client.serviceUrl.defaultZone:http://127.0.0.1:8888 ; Client Side - eureka.client.serviceUrl.defaultZone:http://127.0.0.1:8888  & eureka.instance.prefer-ip-address=true
    
