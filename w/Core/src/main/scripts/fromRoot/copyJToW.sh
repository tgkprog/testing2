BLD_HOME=/data/ddt/w/w

#no trailing /
BLD_HOME=`dirname $0`/..
export BLD_HOME
echo bld home $BLD_HOME
sleep 2
JBOSS_A=/data/ddt/jbossAgent/server/default
JBOSS_C=/data/ddt/jbossCore/server/default

cp -R $JBOSS_A/deploy/ROOT.war/agent/  $BLD_HOME/Agent/src/main/web/ddtAgent/
cp -R $JBOSS_A/deploy/ROOT.war/agent/a/  $BLD_HOME/Agent/src/main/web/ddtAgent/a/
cp -R $JBOSS_C/deploy/ROOT.war/core/ $BLD_HOME/Core/src/main/web/core/

cp -R $JBOSS_C/deploy/ROOT.war/core/hlp $BLD_HOME/Core/src/main/web/core/hlp

cp -R $JBOSS_C/deploy/ROOT.war/common/ $BLD_HOME/Core/src/main/web/common/

cp -R $JBOSS_C/deploy/ROOT.war/common/imgs $BLD_HOME/Core/src/main/web/common/imgs
# *.jsp