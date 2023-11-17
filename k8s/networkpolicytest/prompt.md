#### installation guide: https://docs.tigera.io/archive/v3.13/getting-started/kubernetes/self-managed-onprem/onpremises
curl https://docs.projectcalico.org/archive/v3.13/manifests/calico.yaml -O
kubectl apply -f calico.yaml

./k8s-simple-demo.sh

k8s networkpolicy calico fqdn
#https://stackoverflow.com/questions/69635928/allow-egress-from-a-kubernetes-pod-to-only-specific-fqdn-dns-with-azure-cni-netw
If you use Azure CNI & Calico Policy Plugin you get advanced possibilities like Global Network Polices but not the FQDN/DNS one. This is a paid feature on Calico Cloud unfortunately.

kubectl get networkpolicy
kubectl describe networkpolicy <networkpolicy-name>
kubectl delete networkpolicy <networkpolicy-name>

