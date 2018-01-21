echo "sleep then start agent to ensure pause between core and agent start"
sleep 6
cd /data/tfw/jbossAgent/bin
sh ./runTfwAgent.sh -c default --host=0.0.0.0 -Djboss.service.binding.set=ports-01