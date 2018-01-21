BLD_HOME=/data/ddt/w
export BLD_HOME
#JBOSS_H=/data/ddt/jbossAgent/jboss-5.1.0.GA/server/node1
mkdir -p $BLD_HOME/ddtDeploy/tmp
# *******
cd   $BLD_HOME/ddtDeploy/tmp
rm -fR *
rmdir -p *
# jar xf DdtUtils-0.0.1.jar

tar -zxvf $BLD_HOME/Utils/target/DdtUtils-0.0.1-default.tar.gz

mv DdtUtils-0.0.1/lib/* .
rm  -rf DdtUtils-0.0.1

tar -zxvf $BLD_HOME/Data/target/*-default.tar.gz
mv DdtData-0.0.1/lib/* .
rm  -rf DdtData-0.0.1

tar -zxvf $BLD_HOME/Core/target/*-default.tar.gz
mv DdtCore-0.0.1/lib/* .
rm  -rf DdtCore-0.0.1

tar -zxvf $BLD_HOME/Agent/target/*-default.tar.gz
mv DdtAgent-0.0.1/lib/* .
rm  -rf DdtAgent-0.0.1

