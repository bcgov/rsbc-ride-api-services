
# RIDE: GeoCoder API Service  

## About  


## Local Development  



## Change Process  

To push and release new changes, please follow below steps.  

- 'main' branch is the latest codebase and the codebase which is deployed to PROD  
- For each new release, create a new release branch with name format: release-geocoder/x.x.x  
- For new features, checkout feature branches from the release branch. The name format will be feature-geocoder/<feature-name>  
- Create and merge feature branches to the release branches for different features  
- Each push to the feature branches trigger a CI workflow which runs unite tests and perform a test build. If the anything fails, check and correct the errors. The workflow running will be 'CI Checks for Geocoder build'  

### Trigger Dev deployment  

- To trigger the dev deployment, create a new PR from the release branch to the 'devbranch'  
- This triggers the dev deploy workflow (Geocoder-Build & Deploy to DEV). If for any reason the build fails, workflow shows the log and the PR is blocked. Without resolving the error, the merge cannot happen  
- The workflow doesnt actually directly deploy. It builds the container image, pushes to Artifactory and then updates the image tag (commit sha) to the ArgoCD Kustomize manifest for dev overlay  
- Once the workflow completes, perform a Sync on the ArgoCD project to deploy the changes to Dev. The ArgoCD project name is: be5301-ride-geocoder-svc-dev    


### Trigger Test deployment  

- To trigger the test deployment, create a new PR from the release branch to the 'testbranch'  
- This triggers the test deploy workflow (Geocoder-Build & Deploy to TEST). If for any reason the build fails, workflow shows the log and the PR is blocked. Without resolving the error, the merge cannot happen  
- The workflow doesnt actually directly deploy. It builds the container image, pushes to Artifactory and then updates the image tag (commit sha) to the ArgoCD Kustomize manifest for test overlay  
- Once the workflow completes, perform a Sync on the ArgoCD project to deploy the changes to Test. The ArgoCD project name is: be5301-ride-geocoder-svc-test  


### Trigger Prod deployment  

- To trigger the prod deployment, create a new PR from the release branch to the 'main'  
- This triggers the prod deploy workflow (Geocoder-Build & Deploy to PROD). If for any reason the build fails, workflow shows the log and the PR is blocked. Without resolving the error, the merge cannot happen  
- The workflow doesnt actually directly deploy. It builds the container image, pushes to Artifactory and then updates the image tag (commit sha) to the ArgoCD Kustomize manifest for prod overlay  
- Once the workflow completes, perform a Sync on the ArgoCD project to deploy the changes to Prod. The ArgoCD project name is: be5301-ride-geocoder-svc-prod    
