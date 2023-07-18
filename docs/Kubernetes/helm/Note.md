# Helm

- `helm create <chart-name>`
- install specific chart, the command should be called where the chart folder located

`helm install xxx-deployment xxx-chartname`

- `uninstall chart

`helm uninstall xxx-deployment`

- `helm upgrade xxx-deployment xxx-chartname`

- `helm ls`

- `helm uninstall xxx-deployment`

- `helm dependency build`

- generate the actual kubernetes deployment yaml file. should call this cammand within the same folder

`helm template .`

- `helm history xxx-deployment`
- `helm rollback xxx-deployment version-number`
- `helm list` this command lists all of the releases for a specified namespace





