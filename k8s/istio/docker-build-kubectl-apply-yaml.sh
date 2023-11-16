#! /bin/bash
##sed -i 's/\r$//' *.sh
set -o nounset
#set -o errexit

echo '-----------------Initializing...-----------------'
DateTimeVer=$(date +"%Y%m%d%H%M")
curDir=$PWD
imgName=app
ver="v$DateTimeVer"
ns=appistio

echo '-----------------Docker build...-----------------'
#gcloud auth configure-docker
#echo 'password123' | docker login --username user123 --password-stdin https://xxx.bbb.ccc:12345
docker build -t $imgName:$ver -f ../../sample1/Dockerfile ../../sample1
if [ $? -ne 0 ]; then exit 1; fi

echo '-----------------Kubernetes deploy...-----------------'
#Login if required...

oldns=$(kubectl get ns | grep "$ns")
if [ -z "$oldns" ]
then
    echo "$ns is not present"
else
    echo "$ns present"
    kubectl delete namespace $ns
fi

kubectl create namespace $ns
if [ $? -ne 0 ]; then exit 1; fi
if [ ! -d "k8sall-output.yaml" ]; then
    echo 'k8sall-output.yaml not exists'
else
    rm -f k8sall-output.yaml
fi
sed "s/imageNamePlaceHolder:versionPlaceHolder/$imgName:$ver/g" k8sall.yaml > k8sall-output.yaml
#kubectl apply -f k8sall-output.yaml --namespace=$ns
istioctl kube-inject -f k8sall-output.yaml | kubectl apply --namespace=$ns -f -

if [ $? -ne 0 ]; then exit 1; fi
cd $curDir
echo "echo kubectl delete ns $ns"
echo "echo please visit: http://localhost:30080/"
