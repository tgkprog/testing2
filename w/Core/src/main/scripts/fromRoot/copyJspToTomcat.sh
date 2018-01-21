BLD_HOME=`dirname $0`/..
BLD_HOME=/Users/brahmareddykapa/Desktop/new_WS_INDIGO2
## BLD_HOME=/data/ddt/w/w

#no trailing /
export BLD_HOME
echo bld home $BLD_HOME
sleep 2

TOMCAT_A=/data/ddt/tomcatAgent/webapps/ROOT
TOMCAT_C=/data/ddt/tomcatCore/webapps/ROOT

mkdir  -p $TOMCAT_A/agent/
cp -vR  $BLD_HOME/Agent/src/main/web/ddtAgent/*.jsp $TOMCAT_A/agent/

mkdir  -p $TOMCAT_A/agent/a/
cp -vR  $BLD_HOME/Agent/src/main/web/ddtAgent/a/*.jsp $TOMCAT_A/agent/a/

mkdir  -p $TOMCAT_A/agentXls/
cp -vR  $BLD_HOME/Agent/src/main/web/ddtAgentXls/*.jsp $TOMCAT_A/agentXls/

mkdir  -p /$TOMCAT_C/core/
cp -vR  $BLD_HOME/Core/src/main/web/core/*.jsp $TOMCAT_C/core/

mkdir  -p /$TOMCAT_C/core/hlp/
cp -vR  $BLD_HOME/Core/src/main/web/core/hlp/*.jsp $TOMCAT_C/core/hlp/
cp -vR  $BLD_HOME/Core/src/main/web/core/hlp/*.jpg $TOMCAT_C/core/hlp/

mkdir  -p /$TOMCAT_C/core/rpt/
cp -vR  $BLD_HOME/Core/src/main/web/core/rpt/*.jsp $TOMCAT_C/core/rpt/

mkdir  -p /$TOMCAT_C/ddt/
cp -vR  $BLD_HOME/Core/src/main/web/ddt/*.jsp $TOMCAT_C/ddt/

mkdir  -p /$TOMCAT_C/common/imgs/
cp -vR  $BLD_HOME/Core/src/main/web/common/imgs/*.png $TOMCAT_C/common/imgs/
cp -vR  $BLD_HOME/Core/src/main/web/common/*.jsp $TOMCAT_C/common/

mkdir -p /$TOMCAT_C/reports/res/CSS/
cp -vR  $BLD_HOME/Core/src/main/resources/CSS/*.css $TOMCAT_C/reports/res/CSS/

mkdir -p /$TOMCAT_C/reports/res/JS/
cp -vR  $BLD_HOME/Core/src/main/resources/JS/*.js $TOMCAT_C/reports/res/JS/

mkdir -p /data/ddt/res/velocity/resources/
cp -vR  $BLD_HOME/Core/src/main/resources/*.vm /data/ddt/res/velocity/resources/

cp -vR  $BLD_HOME/DdtTests/src/main/webapp/testApp $TOMCAT_C/