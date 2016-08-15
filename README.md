# NykreditBF
Nykredit Bordfodbold App

Getting started:
Download IntelliJ IDEA
Download GIT
Run IDEA, and checkout project: https://github.com/tarcom/NykreditBF
install XAMPP as default.
config tomcat, and add the gui user to user xml config file
start apache
start tomcat
start mysql (user root, no passwd)
goto http://localhost/phpmyadmin/
create db: nykreditbf
import DB from /sql/nykreditbf.sql
goto http://localhost:8080/manager/html
deploy war file from /releases/NykreditBF_1.0.war
goto: http://localhost:8080/NykreditBF_1.0/
DONE.

To run tomcat from within intelliJ IDEA, make sure you have installed the ultimate IDEA version (not community)
Stop the xampp tomcat
Start tomcat from idea (allready configured to use artifact)
Now you can see logs etc. withing idea. To redeploy changes, press the "update" button, and the application server will restart quickly to reflect latest changes

To run selenium tests, copy the /bin/chromedriver to somewhere you have a path to, or set one up (I used c:\windows\system32)
