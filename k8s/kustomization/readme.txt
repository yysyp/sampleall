#kubectl apply -f myapp3yaml.output
#kubectl delete -f myapp3yaml.output
#kubectl delete ns nsaaa
#kubectl create namespace nsaaa
#kubectl apply -f myapp3yaml.output --namespace=nsaaa
#kubectl delete -f myapp3yaml.output
#kubectl delete namespace nsaaa
#kubectl -n nsaaa describe pod myapp3
#kubectl -n nsaaa get pod -w
#kubectl -n nsaaa logs myapp3
#Below kubectl exec needs to use CMD or PowerShell, gitbash desnot work:
#kubectl -n nsaaa exec -it myapp3 -- bash
#kubectl -n nsaaa exec -it myapp3 -- /bin/sh
#kubectl -n nsaaa exec -it $(kubectl -n nsaaa get pods --field-selector status.phase=Running --no-headers -o custom-columns=":metadata.name" | grep myapp3 | head -1) -- /bin/sh
#kubectl -n nsaaa port-forward myapp3 30080:80
#kubectl explain pod --recursive | grep -i maxRetries
#kubectl -n nsaaa logs -f $(kubectl -n nsaaa get pods --field-selector status.phase=Running --no-headers -o custom-columns=":metadata.name" | grep myapp3 | head -1)
#kubectl get netpol -n nsaaa
#kubectl -n nsaaa delete networkpolicy myapp3-np
#kubectl explain pod --recursive | grep -i maxRetries
#kubectl explain networkpolicy --recursive

kubectl apply -k kustomization
kubectl apply -k .

kubectl delete -k kustomization

kubectl -n nsaaa get all
kubectl -n nsaaa get networkpolicy

