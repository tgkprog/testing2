


echo $BLD_HOME
sleep 5


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



cd $BLD_HOME/HttpRunner 
mvn clean install -Dmaven.test.skip=true
