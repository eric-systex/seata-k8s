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
docker build -t 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-server:0.8.1 server

# push image
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-server:0.8.1

# deploy
kubectl apply -f server/seata-server.yaml
```
