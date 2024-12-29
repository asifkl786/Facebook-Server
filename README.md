          Plan for Implementation
          
 *****Modules to Implement******

A) User Management(Module)
    Register/Login with validation and encrypted passwords.
    User Profile CRUD (DTO for input/output).

B) Posts and Feeds (Module)
   CRUD operations for posts.
   Fetch user feeds with paginated results.

C) Friendship Management (Module)
    Friend requests, accept/reject friends, list friends.


D) Notifications  (Module)
    Event-based notifications for likes, comments, and friend requests.


   ****** Tools and Design Choices *********
   
A)  Framework: Spring Boot.

B)  Database: MySQL.

C)  ORM: Hibernate (via JPA).

D)  Logging: SLF4J with Logback.

E)  Mapping: MapStruct for DTO <-> Entity transformations.

F)  Authentication: JWT-based authentication.
