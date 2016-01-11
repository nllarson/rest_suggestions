# RESTful Controllers / Domain Class Marshalling
-----

So this is a super simple example, and much of it was taken directly from the Grails documentation for [REST Web Services](http://grails.github.io/grails-doc/2.5.3/guide/webServices.html#REST).

* I use Grails 2.5.3 for this example.  It is going to make some of this quite a bit easier than sticking to 2.1.1.  The REST support has really been enhanced.  I believe this was really updated in Grails 2.3.x, so going atleast that far would help, but if you are going to upgrade, I would get to the latest available at this time.
* You can run the command `grails url-mappings-report`
* I have the spring security-plugin-core as well as the spring-security-rest plugin inlcuded here.  I went with version 2 of the core plugin, as it is the latest.  It handles security a bit more agressive (All URLs locked down by default), but the idea is the same as the original plugin.  The spring-security-rest plugin allows use of JWT tokens by default for the RESTful endpoints.

  * This will require you to login when using the endpoints (See [spring-security-rest plugin docs](http://alvarosanchez.github.io/grails-spring-security-rest/1.5.3/docs/guide/introduction.html)).  
    * POST to http://localhost:8080/rest/api/login with a body of ```{"username":"admin","password":"password"}```     
    * Successful authentication will give you a JWT token (access_token) in the response.
    * Take this access token and put it in successive request headers with the key of 'X-Auth-Token'.

*  The 3 controllers in this app are all setup to handle the different domains.  You can see what roles are required to access the contorllers by the @Secured annotation at the top.  (Note Genre requires ROLE_ADMIN)
*  The URLMapping.groovy file holds the mapped URLs for the api.
*  The object marshallers are registered in Bootstrap.groovy  There are examples in the REST Web Services documentation link about putting these in their own classes and / or giving them different config names for different ways of rendering the objects.



