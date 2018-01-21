#test via export
mkdir -p /data/ddt/w/o
cd /data/ddt/w/o
rm -Rf *
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Utils/Utils
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Data/
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Core/
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Agent/
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/SeleniumRunner/
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Runners/HttpRunner/
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Runners/Verifiers/
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/DdtCommon

svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/Verifiers
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/DdtWeb
svn export http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/DdtCommon
#cd Utils
cp ./DdtAgentTest/src/main/allProjectsPom/pom.xml .
mvn -Dmaven.test.skip=true install 
#sleep 2