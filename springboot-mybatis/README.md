# Seata AT Mode Sample
Springboot + MyBatis + Seata AT and Deploy to Kubernetes (EKS)

## Initialize
DB Schema and Data
```
kubectl apply -f deploy-db.yaml
kubectl exec -i $(kubectl get po --selector app=api-db -n seata-demo-at --output=jsonpath={.items..metadata.name}) -n seata-demo-at --  mysql -u root -proot < all_in_one.sql
```

## Install Dependency Artifacts
install artifacts
* sbm-common-service
* springboot-mybatis
* seata-sample

```
mvn clean install
```

## Build Docker Image
```
mvn -f sbm-business-service/pom.xml clean package dockerfile:build
mvn -f sbm-storage-service/pom.xml clean package dockerfile:build
mvn -f sbm-order-service/pom.xml clean package dockerfile:build
mvn -f sbm-account-service/pom.xml clean package dockerfile:build
```

## Push Docker Image
```
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-business-service
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-storage-service
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-order-service
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-account-service
```

## Deploy
```
kubectl apply -f sbm-business-service/manifests/deploy-apply.yaml
kubectl apply -f sbm-storage-service/manifests/deploy-apply.yaml
kubectl apply -f sbm-order-service/manifests/deploy-apply.yaml
kubectl apply -f sbm-account-service/manifests/deploy-apply.yaml
```

## Run
```
# commit
curl http://localhost:8888//api/business/purchase/commit

# rollback
curl http://localhost:8888//api/business/purchase/rollback

```



