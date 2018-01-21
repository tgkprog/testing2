echo should have BLD_HOME $BLD_HOME already defined



sleepTime=1
echo $BLD_HOME
sleep $sleepTime


cd $BLD_HOME/Utils
sh src/main/scripts/bldAll.sh
echo utils done
sleep 2

cd $BLD_HOME/Data
#cat src/main/scripts/bldAssembly.sh
sh src/main/scripts/bldAssembly.sh
echo data done
sleep 2

cd $BLD_HOME/Core
#cat  src/main/scripts/bldAssembly.sh
sh src/main/scripts/bldAssembly.sh
echo core done
sleep 2

cd $BLD_HOME/Agent
mvn clean install -Dmaven.test.skip=true
echo agent done
sleep 2


cd $BLD_HOME/HttpRunner 
mvn clean install -Dmaven.test.skip=true
echo http runr done
sleep 2

cd $BLD_HOME/Verifiers 
mvn clean install -Dmaven.test.skip=true
echo verfrs done
sleep 2

cd $BLD_HOME/SeleniumRunner 
mvn clean install -Dmaven.test.skip=true
echo sel done
sleep 2


cd $BLD_HOME/DdtAgentTest 
mvn clean install -Dmaven.test.skip=true
echo sel done
sleep 2