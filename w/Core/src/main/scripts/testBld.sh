BLD_HOME=/data/tstBld
wHome=/data/ddt/w/w
svnHome=http://10.10.11.18/svn/SVNROOT/PROJECTAUTOMATION/trunk
export BLD_HOME
export wHome
mkdir /data/tstBld
cd  /data/tstBld
echo "remov"
pwd
sleep 6
rm -Rf *

export BLD_HOME
echo "Testing compile from $BLD_HOME  first export from svn"
sleep 4

svn export $svnHome/DDT/Utils/Utils
svn export $svnHome/DDT/Data
svn export $svnHome/DDT/Core
svn export $svnHome/DDT/Agent
svn export $svnHome/DDT/Runners/HttpRunner/
sh $wHome/Core/src/main/scripts/ddtBld2.sh

