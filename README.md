<!-- START readme-head.md -->
# 

**WARNING! THIS PROJECT IS STILL IN DEVELOPEMENT!** At the moment, it lacks most of it functions and is unusable.<!-- END readme-head.md -->
![modulestage](https://img.shields.io/badge/module%20stage-developement-red)
![moduleversion](https://img.shields.io/badge/version-0.0.1-red)
<!-- START readme-shields.md -->
[![license](https://img.shields.io/github/license/)](https://github.com//blob/main/LICENSE)
[![issues](https://img.shields.io/github/issues/)](https://github.com//issues)<br>
[![contributors](https://img.shields.io/github/contributors/)](https://github.com//graphs/contributors)
[![activity](https://img.shields.io/github/commit-activity/m/)](https://github.com//commits/main)
[![lastcommit](https://img.shields.io/github/last-commit/)](https://github.com//commits/main)<br>
![size](https://img.shields.io/github/languages/code-size/)
![files](https://img.shields.io/github/directory-file-count/)
![languages](https://img.shields.io/github/languages/count/)<br><!-- END readme-shields.md -->
### Server module to provide access for and connection to clients
<!-- START readme-link-to-main-repo.md -->
Read more about the project [here](https://github.com/CraftyServerMC/CraftyServer)!  
Legal disclaimer: This project is not affiliated with Minecraft&trade; or Mojang&trade;/Microsoft&trade; in any way, shape or form.  <!-- END readme-link-to-main-repo.md -->
<!-- START readme-how-to-install.md -->
## How to install
This Project uses Maven.  
1. Clone the repository: 
```bash
git clone https://github.com//
```
2. Run inside of e.g. Eclipse, using the default "Run as -> Java Application"-option, or build yourself a `.jar`-file, using Maven:
```bash
mvn install
```  

Once we hit a usable state in developement, there will be some form of installer to actually install and use the server.<!-- END readme-how-to-install.md -->
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