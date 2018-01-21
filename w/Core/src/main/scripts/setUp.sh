# Do not add trailing slash / in below paths Example : Correct : /data/ddt/w/w  Wrong : /data/ddt/w/w/

BLD_HOME=/data/ddt/w/w
export BLD_HOME

# can be different in case of build from exported source 
WORKSPACE_HOME=/data/ddt/w/w
export WORKSPACE_HOME=/data/ddt/w/w
echo "w home $WORKSPACE_HOME "
