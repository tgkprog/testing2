cd /data/ddt/w/w/DdtDeploy/tmp/
 cp *.jar /data/ddt/tomcatCore/shared/lib
 cp *.jar /data/ddt/tomcatAgent/shared/lib
 
#BLD_HOME=`dirname $0`/..
## BLD_HOME=/Users/brahmareddykapa/Desktop/new_WS_INDIGO
#BLD_HOME=/data/ddt/w/w

#no trailing /
#export BLD_HOME
echo bld home $BLD_HOME
sleep 2
#APP_AGNT=/data/ddt/jbossAgent/server/default
#BOSS_C=/data/ddt/jbossCore/server/default
APP_AGNT=/data/ddt/tomcatAgent/webapps/ROOT
APP_CORE=/data/ddt/tomcatCore/webapps/ROOT

 mkdir  -p $APP_AGNT/agent/
 mkdir  -p $APP_AGNT/agent/a/
mkdir  -p $APP_AGNT/common/
cp $BLD_HOME/Agent/src/main/web/ddtAgent/*.jsp $APP_AGNT/agent/
cp $BLD_HOME/Agent/src/main/web/ddtAgent/a/*.jsp $APP_AGNT/agent/a/
#cp $BLD_HOME/Agent/src/main/web/ddtAgentXls/*.jsp $APP_AGNT/agentXls/

# mkdir  -p /$APP_CORE/core/
cp -R  $BLD_HOME/Core/src/main/web/core/*.jsp $APP_CORE/core/

 mkdir  -p /$APP_CORE/core/hlp/
cp -R  $BLD_HOME/Core/src/main/web/core/hlp/*.jsp $APP_CORE/core/hlp/
cp -R  $BLD_HOME/Core/src/main/web/core/hlp/*.jpg $APP_CORE/core/hlp/

 mkdir  -p /$APP_CORE/core/rpt/
cp -R  $BLD_HOME/Core/src/main/web/core/rpt/*.jsp $APP_CORE/core/rpt/

 mkdir  -p /$APP_CORE/ddt/
cp -R  $BLD_HOME/Core/src/main/web/ddt/*.jsp $APP_CORE/ddt/

 mkdir  -p /$APP_CORE/common/imgs/
cp -R  $BLD_HOME/Core/src/main/web/common/imgs/*.png $APP_CORE/common/imgs/
cp -R  $BLD_HOME/Core/src/main/web/common/*.jsp $APP_CORE/common/

 mkdir -p /$APP_CORE/reports/res/CSS/
cp -R  $BLD_HOME/Core/src/main/resources/CSS/*.css $APP_CORE/reports/res/CSS/

 mkdir -p /$APP_CORE/reports/res/JS/
cp -R  $BLD_HOME/Core/src/main/resources/JS/*.js $APP_CORE/reports/res/JS/

 mkdir -p /data/ddt/res/velocity/resources/
cp -R  $BLD_HOME/Core/src/main/resources/*.vm /data/ddt/res/velocity/resources/

