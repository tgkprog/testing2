#test via checkout bldFromCheckout.sh
BLD_HOME=/data/ddt/w/w2
mkdir -p ~/data/ddt/w/w
cd ~/data/ddt/w/w
mkdir -p $BLD_HOME
cd $BLD_HOME
pwd
echo "If errors above press ctrl-c now!"
sleep 4
rm -Rf *
svn checkout  --non-recursive  http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/ .
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Utils/Utils ./Utils
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Data/
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Core/
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Agent/
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/SeleniumRunner/
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Runners/HttpRunner/
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Runners/Verifiers/
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/DdtAgentTest/
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/DdtCommon
svn checkout http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/DdtWeb

rm  $BLD_HOME/Core/src/main/java/com/exilant/ddt/core/CoreRunner.java 


sleep 5
mvn -Dmaven.test.skip=true install 