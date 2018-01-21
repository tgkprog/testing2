#workspace, should have - case sensitive - exact projects - Utils, Data, Core, Agent, SeleniumRunner
MYHOME=`dirname $0`/..
sh $MYHOME/setup.sh

echo bld home $BLD_HOME
#sleep 2
COPYTO=/data/ddt/deploy/jars
JBOSS_C=/data/ddt/jbossCore/server/default


cp -v $BLD_HOME/DdtCommon/target/*Common*.jar $COPYTO
cp -v $BLD_HOME/Utils/target/*Utils*.jar $COPYTO
echo "Utils done"



cp -vf $BLD_HOME/Data/target/DdtData-0.0.1.jar $COPYTO

#cp -vf $BLD_HOME/Core/target/DdtCore*.jar .

cp -vf $BLD_HOME/Agent/target/DdtAgent*.jar $COPYTO
echo "Data and Agent main jar(s) copied"



#tar -zxvf $BLD_HOME/SeleniumRunner/target/SA-1*-default.tar.gz
#mv SA-1/lib/* .
#rm  -rf SA-1
#echo "SeleniumRunner done"

echo "Sel & verifiers skipped"

#cp -vf -v $BLD_HOME/Verifiers/target/Verifiers*.jar ./
#echo "Verifiers only main jar copied, HC log4j dependancies are in Utils"



cp -vf -v $BLD_HOME/HttpRunner/target/HttpRunner*.jar $COPYTO
echo "HttpRunner only main jar done, HC log4j dependancies are in Utils"

#cp -vf -v $BLD_HOME/GrxHelpers/target/GrxHelpers-1.jar  ./
#echo okay end
#sleep 2
#cp -n  /data/ddt/tomcatCore/shared/lib/*.jar /data/ddt/w/w3/ddtDeploy/tmp
#echo "copied other jars "
#sleep 2