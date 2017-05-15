# Current production REST API Documentation version 1.0.0

![REST API DOCUMENTATION version 1.0.0](https://github.com/JuhaPit/AllRounders/blob/master/documents/REST-API_v1.png)  

# Current backend production environment  
 
 Backend for AllRounders RESTAPI is currently running on ubuntu 16.04 LTS
 
| Software     | Version         | 
| ------------- |:-------------:| 
| Java      | 1.8.0.131 | 
| Tomcat     | 8.0.32  | 
| Nginx      | 1.10.0 |
 
# Installation 

Make empty directory to /usr/share/{user who is running tomcat}/ named .credentials    
## Download latest deployment WAR file with WGET
**wget https://github.com/JuhaPit/AllRounders/blob/master/deployment/AllRounders.war** 

## Copy file AllRounders.war /var/lib/tomcat8/webapps  
**cp AllRounders.War /var/lib/tomcat8/webapps** this should deploy latest deployment war file automatically to tomcat.

# Use

After deployment, server should be up and running at **http://{host}:8080/AllRounders**  
You can test if server is online by calling **http://{host}:8080/AllRounders/debug/health** endpoint. This call should return  
{ "Status": "UP" } when server is up and running.

# Auth once to Google Docs  

Do one test call(Instructions are found on API), example: **http://{host}:8080/AllRounders/top**  
Check catalina.out log for authentication url.  
You can find catalina.out from path **/var/lib/tomcat8/logs/catalina.out**

# Useful tools/urls  

| [Postman](https://www.getpostman.com/) 
| [Tomcat](http://archive.apache.org/dist/tomcat/tomcat-8/v8.0.32/) 
| [Nginx](https://nginx.org/en/download.html) |

# Team

* Leif Salminen  
* Juha Pitkänen  
* Eero Salomies  
* Max Prokopenko  
* Antti Ryökkynen  
* Jaakko Rantala 


