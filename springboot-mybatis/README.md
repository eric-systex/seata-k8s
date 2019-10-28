# Deploy to Kubernetes
Deploy to EKS

## Install Dependency Artifacts
install artifacts
* sbm-common-service
* springboot-mybatis
* seata-sample

```
mvn clean install
```

## Build Docker Image
切換到 springboot-mybatis/sbm-business-service 目錄下，執行 docker build
```
mvn package dockerfile:build
```

## Push Docker Image
```
docker push 348053640110.dkr.ecr.us-west-2.amazonaws.com/seata-k8s-at:sbm-business-service-v1
```

## Deploy
```
kubectl apply -f manifests/deploy-apply.yaml
```



kubectl exec -ti api-db-68d575c4f5-lzdhk -n seata-demo-at --  mysql -u root -proot < ~/git.repo/seata/seata-samples/springboot-mybatis/sql/all_in_one.sql

