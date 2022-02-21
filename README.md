<!-- START readme-head.md -->
<!-- END readme-head.md -->
![modulestage](https://img.shields.io/badge/module%20stage-developement-red)
![moduleversion](https://img.shields.io/badge/version-0.0.1-red)
<!-- START readme-shields.md -->
<!-- END readme-shields.md -->
### Server module to provide access for and connection to clients
<!-- START readme-link-to-main-repo.md -->
<!-- END readme-link-to-main-repo.md -->
<!-- START readme-how-to-install.md -->
<!-- END readme-how-to-install.md -->
## This module: Server
This module allows clients to connect with the server, parses packages from them and sends packages to them.

#### This module does the following:
 - Accept connections, recieve, parse and send packages to and from Minecraft Clients
 - Store and access parameters for different client connections
 - Provide data type parser for Minecraft protocol

#### Why is it its own module?
This Server only recieves and parses packages for them to have a length, a data field an an ID. It does not process them further in case a self-creted protocol should be processed.

<!-- START readme-maven-instructions-repo.md -->
<!-- END readme-maven-instructions-repo.md -->

2. Include artifact:

```XML
<dependencies>
  <dependency>
    <groupId>org.craftyserver</groupId>
    <artifactId>server</artifactId> <!-- Name of the module -->
    <version>0.0.1-SNAPSHOT</version> <!-- Version of the module -->
    <scope>provided</scope>
  </dependency>
</dependencies>
```