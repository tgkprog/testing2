newWName=w18
mkdir -p /data/ddt/w/exp/$newWName
cd /data/ddt/w/exp/$newWName
pwd
echo "copy w if folders okay else ctrl-c"


ls ../../w3/
sleep 4
echo "..."
echo "start cp deep"
cp -Rf ../../w3/* .
echo "remove ."
find . -type f -name ".d*"  -exec rm  -rf {} \;
find . -type f -name ".D*"  -exec rm  -rf {} \;


find . -type d -name ".svn"  -exec rm  -rf {} \;

find . -type d -name "target"  -exec rm  -rf {} \;
find . -type d -name "log"  -exec rm  -rf {} \;
find . -type f -name ".log"  -exec rm  -rf {} \;
echo "remove not required"
rm -fR v.zip
rm -fR vv
rm -fR ddtDeploy
rm -fR t
rm -fR DdtTests1
rm -fR HttpRunneru
rm -fR localRepo
rm -fR Workbook1.xls
rm -fR Agent2old
rm -fR AcademicTsts
rm -fR SA2j
rm -fR SA2
rm -fR DdtCommon-orig
rm -fR DdtCommon.zip
rm -fR Utils1

rm -fR  DdtAgentTest.zip

#rm -fR CipherEncrypt
#rm -fR cipherRun
#rm -fR Verifiers
#rm -fR SeleniumRunner
rm -fR DdtTests

rm -fR Servers/
 rm -fR Servers
rm -fR ApiRunner/
rm verifrs.zip
rm -fr Servers
rm -fr *.zip
rm -fr DdtCommon-orig
rm -fr ddtDeploy
rm -fr Verifiers
rm -fr 

echo "pack w $newWName"

find . -name "*.log" -exec rm -fvd {} \;
find . -name "*.log.*" -exec rm -fvd {} \;

jar cvfM /data/ddt/$newWName.zip *
echo "pack conf "
cd /data/ddt/
jar cvfM conf.zip config/* res/*
jar cvfM confLite.zip config/testApp/*.* config/main.p* config/TestAppHttp*.xls

echo /data/ddt/$newWName.zip "done, also confLite.zip"