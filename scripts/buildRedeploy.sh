#!/bin/sh

echo "************ UNDEPLOYING *******************"
asadmin undeploy spolki
echo "************ BUILDING **********************"
echo "============ CLEANING PROJECT =============="
mvn clean
echo "============ PACKING ======================="
mvn package
echo "************ DEPLOYING *********************"
asadmin deploy target/spolki.war
