## Command used to remove file from commit history
```git filter-branch --force --index-filter "git rm --cached
--ignore-unmatch docs/OAuth/keycloak-21.1.1.zip"
```
after remove related commit history, maybe incur some conflict with current version so need pull 
first, then use
```git push```
command to push all the commit to remote repository
## The difference between git merge and git rebase
- git rebase: merge one commit history into another commit hisotry
- merge: combine two different commit history and create a new one