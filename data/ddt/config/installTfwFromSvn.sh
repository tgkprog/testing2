echo "Ctrl-c if you do not have /data/ folder with same spelling - case sensitive and full permissions on that"
sleep 5
cd /data/
mkdir tfw
cd tfw
echo "ctrl - c if errors"
sleep 4
svn export http://10.10.11.18/svn/SVNROOT/PROJECTAUTOMATION/trunk/TFWBinaries/confs.zip
svn export http://10.10.11.18/svn/SVNROOT/PROJECTAUTOMATION/trunk/TFWBinaries/jbosses.zip
echo "ctrl - c if errors"
sleep 4
tar -zxvf Archive.zip
mv ./__MACOSX/jbossCore ./jbossCore
mv ./__MACOSX/jbossAgent ./jbossAgent

echo "ctrl - c if errors"
sleep 4

tar -zxvf confs.zip
echo "ctrl - c if errors"
sleep 4
echo "Now open blue terminal & copy paste from file : /data/tfw/config/runCore.sh"

echo "And open red terminal & give command : sh /data/tfw/config/runAgent.sh"
sleep 4

