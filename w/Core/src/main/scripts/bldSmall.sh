#export BLD_HOME=
echo BLD_HOME $BLD_HOME
echo ctrl c if incorrect bld home
#sleep 3
cd $BLD_HOME/
#mvn clean install -Dmaven.test.skip=true
# de=$BLD_HOME/DdtDeploy/tmp
de=/data/ddt/deploy/jars
cp -vf Utils/target/DdtUtils-0.0.1.jar $de
cp -vf Data/target/DdtData-0.0.1.jar  $de
cp -vf Agent/target/DdtAgent-0.0.1.jar $de
cp -vf Core/target/DdtCore-0.0.1.jar $de
cp -vf DdtAgentTest/target/DdtAgentTest-1.jar $de
cp -vf HttpRunner/target/H*Runner*1.jar $de
cp -vf DdtCommon/target/DdtCommon*.jar  $de
cp -vf GrxHelpers/target/GrxHelpers-1.jar $de
pwd