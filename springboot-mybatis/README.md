# Seata AT Mode Sample
**Springboot** + MyBatis + **Seata AT** and Deploy to **Kubernetes** (EKS & ECR)

## Initialization
### Create K8s Namespace
```
kubectl create ns seata-demo-at
```

### DB Schema and Data
```
kubectl apply -f deploy-db.yaml
kubectl exec -i $(kubectl get po --selector app=api-db -n seata-demo-at --output=jsonpath={.items..metadata.name}) -n seata-demo-at --  mysql -u root -proot < all_in_one.sql
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

## Deploy Microservices 
### Install Dependency
Install the following artifacts
* sbm-common-service
* springboot-mybatis
* seata-sample

```
mvn clean install
```

### Build Docker Image
```
mvn -f sbm-business-service/pom.xml clean package dockerfile:build
mvn -f sbm-storage-service/pom.xml clean package dockerfile:build
mvn -f sbm-order-service/pom.xml clean package dockerfile:build
mvn -f sbm-account-service/pom.xml clean package dockerfile:build
```

### Push Docker Image
```
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-business-service
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-storage-service
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-order-service
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-account-service
```

### Deploy
```
kubectl apply -f sbm-business-service/manifests/deploy-apply.yaml
kubectl apply -f sbm-storage-service/manifests/deploy-apply.yaml
kubectl apply -f sbm-order-service/manifests/deploy-apply.yaml
kubectl apply -f sbm-account-service/manifests/deploy-apply.yaml
```

## Run 
```
# port-forward
kubectl port-forward $(kubectl get po --selector app=api-business -n seata-demo-at --output=jsonpath={.items..metadata.name}) -n seata-demo-at 8888:8080

# commit
curl http://localhost:8888/api/business/purchase/commit

# rollback
curl http://localhost:8888/api/business/purchase/rollback

```

## Verify Data Consistency
* db_account
```
echo 'select * from account_tbl; select * from undo_log;'| kubectl exec -i $(kubectl get po --selector app=api-db -n seata-demo-at --output=jsonpath={.items..metadata.name}) -n seata-demo-at -- mysql -u root -proot db_account
```

* db_order
```
echo 'select * from order_tbl; select * from undo_log;'| kubectl exec -i $(kubectl get po --selector app=api-db -n seata-demo-at --output=jsonpath={.items..metadata.name}) -n seata-demo-at -- mysql -u root -proot db_order
```

* db_storage
```
echo 'select * from storage_tbl; select * from undo_log;'| kubectl exec -i $(kubectl get po --selector app=api-db -n seata-demo-at --output=jsonpath={.items..metadata.name}) -n seata-demo-at -- mysql -u root -proot db_storage
```
