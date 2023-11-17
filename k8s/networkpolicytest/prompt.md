#### installation guide: https://docs.tigera.io/archive/v3.13/getting-started/kubernetes/self-managed-onprem/onpremises
curl https://docs.projectcalico.org/archive/v3.13/manifests/calico.yaml -O
kubectl apply -f calico.yaml

./k8s-simple-demo.sh
