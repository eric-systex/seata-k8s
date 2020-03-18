# Seata in Kubernetes
Running in **Kubernetes** (EKS & ECR)

## Initialization
### Create K8s Namespace
```
kubectl create ns seata-demo
```

## Deploy Seata Server
version **0.8.1**
```
# build image
docker build -t registry.example.com/seata/seata-server:1.1.0 server

# push image
docker push registry.example.com/seata/seata-server:1.1.0

# deploy
kubectl apply -f server/seata-server.yaml
```
