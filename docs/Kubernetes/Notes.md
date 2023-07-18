# Notes

## How to rollout&rollback automatically with the help of K8s

**Rollout**

1. build new image with a new tag
2. set the deployment Yaml file use this new tag

`kubectl set image deployment xxx-deployment container-name=container-image`

3. K8s will automatically finish the rest task for you

**Rollback**

1. `kubectl rollout history deployment xxx-deployment`
2. `kubectl rollout undo deployment xxx-deployment --to-revision=x`
3. `kubectl describe pod podname` to make sure everything goes well

## Logs

`kubectl logs pod-name`

## Autoscaling inside Kubernetes Cluster

**HPA**: Horizontal Pod Autoscaler

`kubectl autoscale deployment xxx-deployment --min=3 --max=10 --cpu-percent=70`

## Helm



