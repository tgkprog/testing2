#BLD_HOME=/data/ddt/w/w

#no trailing /
#BLD_HOME=`dirname $0`/..
#export BLD_HOME
echo bld home $BLD_HOME
sleep 2
#APP_AGNT=/data/ddt/jbossAgent/server/default
#APP_CORE=/data/ddt/jbossCore/server/default

APP_AGNT=/data/ddt/tomcatAgent/webapps/ROOT
APP_CORE=/data/ddt/tomcatCore/webapps/ROOT

cp -vR $APP_AGNT/agent/  $BLD_HOME/Agent/src/main/web/agent
#cp -R $APP_AGNT/agent/a  $BLD_HOME/Agent/src/main/web/agent/a

cp -vR $APP_CORE/core/ $BLD_HOME/Core/src/main/web/core/

#cp -R $APP_CORE/core/hlp $BLD_HOME/Core/src/main/web/core/hlp
echo ""
echo "common"
cp -v -R $APP_AGNT/common/ $BLD_HOME/Core/src/main/web/common/


# *.jsp