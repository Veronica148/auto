# auto
this is valid read.me
Collection of tests and usefull features.
======================
For running tests execute this command:
# ToDo mvn clean test -Denv=dev
# ToDo here is env - environment, can be dev, qa, local
 mvn clean test -Denv=dev -Dgroups=Full -DforkCount=0 -Dsystem.test.os=iOS

======================
Done:
Maven project,
log4j,
velocity report (fail.html),
dataProvider from json,
Groups support,
passing parameters through testNG.xml
======================

Multithreading through testng.xml:
<suite parallel="classes" thread-count="4" .../>

====
Console multithreading doesn't:
//https://groups.google.com/forum/#!topic/testng-users/Hx9Zk0PAjl4
mvn clean test -DsuiteXmlFile=TestNGSuiteConfig.xml -Denv=dev -Dgroups=Full -DforkCount=0 -Dbrowser=CHROME -DthreadCount=2 -Dparallel="classes"

This works:
mvn clean test -Denv=dev -Dgroups=Full -DforkCount=0 -Dbrowser=FIREFOX

===-Ddataproviderthreadcount="6"