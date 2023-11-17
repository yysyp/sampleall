#! /bin/bash
##sed -i 's/\r$//' *.sh
set -o nounset
#set -o errexit

echo '-----------------Initializing...-----------------'
imgName=simple-demo
ver="v$(date +"%Y%m%d%H%M")"
ns="nsabc"
containerPort=8880
nodePort=30081

echo '-----------------Docker build...-----------------'
#gcloud auth configure-docker
#echo 'password123' | docker login --username user123 --password-stdin https://xxx.bbb.ccc:12345
docker build -t $imgName:$ver -f ../../sample1/Dockerfile ../../sample1
if [ $? -ne 0 ]; then exit 1; fi

echo '-----------------Kubernetes deploy...-----------------'
#Login if required...
sed "s/imageNamePlaceHolder:versionPlaceHolder/$imgName:$ver/g" k8s-simple-demo.yaml > k8s-simple-demo-output.yaml
sed -i "s/namespacePlaceHolder/$ns/g" k8s-simple-demo-output.yaml
sed -i "s/containerPortPlaceHolder/$containerPort/g" k8s-simple-demo-output.yaml
sed -i "s/nodePortPlaceHolder/$nodePort/g" k8s-simple-demo-output.yaml
kubectl delete -f k8s-simple-demo-output.yaml
#if [ $? -ne 0 ]; then exit 1; fi
kubectl apply -f k8s-simple-demo-output.yaml
if [ $? -ne 0 ]; then exit 1; fi

echo "app deployed to -n $ns"
