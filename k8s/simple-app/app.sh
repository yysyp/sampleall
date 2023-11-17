#! /bin/bash
##sed -i 's/\r$//' *.sh
set -o nounset
#set -o errexit

echo '-----------------Initializing...-----------------'
imgName=busybox
#ver="v$(date +"%Y%m%d%H%M")"
ver=1.32
appName=myapp3
ns="nsaaa"
containerPort=80
nodePort=30087

echo '-----------------Kubernetes deploy...-----------------'
#Login if required...
sed "s/imageNamePlaceHolder:versionPlaceHolder/$imgName:$ver/g" app.yaml > $appName.yaml.output
sed -i "s/appNamePlaceHolder/$appName/g" $appName.yaml.output
sed -i "s/namespacePlaceHolder/$ns/g" $appName.yaml.output
sed -i "s/containerPortPlaceHolder/$containerPort/g" $appName.yaml.output
sed -i "s/nodePortPlaceHolder/$nodePort/g" $appName.yaml.output

#kubectl delete -f $appName.yaml.output
kubectl delete ns $ns

#if [ $? -ne 0 ]; then exit 1; fi
kubectl apply -f $appName.yaml.output
if [ $? -ne 0 ]; then exit 1; fi

echo "app deployed to -n $ns"
