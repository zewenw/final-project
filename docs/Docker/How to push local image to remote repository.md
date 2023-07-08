# How to push local image to remote repository

generally speaking, a repository refers to a single artifact, for example, you have a multi-module project, then each module should be a repository

1. use ```docker login``` login docker hub
2. tag local image according to remote repository,   then use push command to upload new image, following is  a example:

`docker tag local-image:tagname reponame:tagname`

`docker push reponame:tagname`

e.g.,

my DockerHub repo is aaa/myrepo

so first I need tag my local image: `docker tag bbb:v1.0 aaa/myrepo:v1.0`

then execute command `docker push aaa/myrepo:v1.0`