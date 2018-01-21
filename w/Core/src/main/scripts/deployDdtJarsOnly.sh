#workspace, should have - case sensitive - exact projects - Utils, Data, Core, Agent, Verifiers, SeleniumRunner, HttpRunner and other runners
MYHOME=`dirname $0`/..
#sh $MYHOME/setup.sh

echo bld home $BLD_HOME
#sleep 2
JBOSS_A=/data/ddt/jbossAgent/server/default
JBOSS_C=/data/ddt/jbossCore/server/default
JBOSS_A=/data/ddt/tomcatAgent/webapps/ROOT
JBOSS_C=/data/ddt/tomcatCore/webapps/ROOT

mkdir -p $BLD_HOME/ddtDeploy/tmp
# *******
cd   $BLD_HOME/ddtDeploy/tmp
rm -fR *
rmdir -p *
# jar xf DdtUtils-0.0.1.jar

tar -zxvf $BLD_HOME/Utils/target/DdtUtils-0.0.1-default.tar.gz

mv DdtUtils-0.0.1/lib/* .
rm  -rf DdtUtils-0.0.1
echo "Utils done"
sleep 2

#tar -zxvf $BLD_HOME/Data/target/*-default.tar.gz
#mv DdtData-0.0.1/lib/* .
#rm  -rf DdtData-0.0.1
cp $BLD_HOME/Data/target/DdtData-0.0.1.jar .
echo "Data done"
sleep 3

tar -zxvf $BLD_HOME/Core/target/DdtCore*-default.tar.gz
mv DdtCore-0.0.1/lib/* .
rm  -rf DdtCore-0.0.1
echo "Core done"
sleep 3

tar -zxvf $BLD_HOME/Agent/target/DdtAgent*-default.tar.gz
mv DdtAgent-0.0.1/lib/* .
rm  -rf DdtAgent-0.0.1
echo "Agent done"
sleep 3

tar -zxvf $BLD_HOME/SeleniumRunner/target/SA-1*-default.tar.gz
mv SA-1/lib/* .
rm  -rf SA-1
echo "SeleniumRunner done"
sleep 2

#tar -zxvf $BLD_HOME/ApiRunner/target/ApiRunner*-default.tar.gz
#mv ApiRunner-1/lib/* .
#rm  -rf ApiRunner-1
#echo "ApiRunner done"

sleep 2

#tar -zxvf $BLD_HOME/ApiRunner/target/HttpRunner*-default.tar.gz 
#mv ApiRunner-1/lib/* .
#rm  -rf ApiRunner-1
cp -v $BLD_HOME/HttpRunner/target/HttpRunner*.jar ./
echo "HttpRunner only main jar done, HC log4j dependancies are in Utils"

sleep 1
cd $JBOSS_A/lib
#rm -fR *
#cd $JBOSS_C/lib
#rm -fR *

cd   $BLD_HOME/ddtDeploy/tmp
cp *.* $JBOSS_A/lib
echo "Copied to jboss $JBOSS_A/lib"

cp *.* $JBOSS_C/lib
echo "Copied to jboss $JBOSS_C/lib"
sleep 5
#copy jsps to web, TODO modify for html, images and other resources
## mkdir  -p $JBOSS_A/deploy/ROOT.war/agent/
## cp $BLD_HOME/Agent/src/main/web/ddtAgent/*.jsp $JBOSS_A/deploy/ROOT.war/agent/
# cp $BLD_HOME/Agent/src/main/web/ddtAgentXls/*.jsp $JBOSS_A/deploy/ROOT.war/agentXls/
## mkdir  -p /$JBOSS_C/deploy/ROOT.war/core/
# mkdir  -p /$JBOSS_C/deploy/ROOT.war/coreXls
## cp -R  $BLD_HOME/Core/src/main/web/core/*.jsp $JBOSS_C/deploy/ROOT.war/core/
# cp -R  $BLD_HOME/Core/src/main/web/core/hlp/*.jsp $JBOSS_C/deploy/ROOT.war/core/hlp
# cp -R  $BLD_HOME/Core/src/main/web/coreXls/*.jsp $JBOSS_C/deploy/ROOT.war/coreXls/
## in Core : 
## cd $JBOSS_C/../../bin
## sh run.sh -c default --host=0.0.0.0

## sleep wait 8 seconds before starting other on same system
## in Agent : 
## cd $JBOSS_A/../../bin
## sh ./runDdtAgent.sh -c default --host=0.0.0.0 -Djboss.service.binding.set=ports-01

##sh ./run.sh default --host=0.0.0.0 -Djboss.service.binding.set=ports-01

