#http://10.10.11.18/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT/
#svn copy -m "v132 branch" "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT"  "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/branches/v132/lanes"

svn mkdir   "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/branches/issues/"      

svn copy -m "agentWhenCreate" "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT"  "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/branches/agent2Create"      


svn mkdir   "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/branches/issues/"


svn copy -m "Manjunath - http runner, core runner and selenium runner" "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/trunk/DDT"  "http://10.1.0.25/svn/SVNROOT/PROJECTAUTOMATION/enhancements/runners1"