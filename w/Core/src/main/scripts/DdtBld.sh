
#no trailing /
##BLD_HOME=`dirname $0`/..
## BLD_HOME=/Users/brahmareddykapa/Desktop/new_WS_INDIGO
BLD_HOME=/Users/support/Desktop/AAN/
BLD_HOME=/data/ddt/w
echo $BLD_HOME
sleep 5
export BLD_HOME

cd $BLD_HOME/Utils
mvn clean install -Dmaven.test.skip=true
sleep 2

cd $BLD_HOME/Data
mvn clean install -Dmaven.test.skip=true
sleep 2

cd $BLD_HOME/Core
mvn clean install -Dmaven.test.skip=true
sleep 2

cd $BLD_HOME/Agent
mvn clean install -Dmaven.test.skip=true
sleep 2

cd $BLD_HOME/SeleniumRunner 
mvn clean install -Dmaven.test.skip=true
sleep 2

cd $BLD_HOME/ApiRunner 
# mvn clean install -Dmaven.test.skip=true

cd $BLD_HOME/HttpRunner
mvn clean install -Dmaven.test.skip=true
