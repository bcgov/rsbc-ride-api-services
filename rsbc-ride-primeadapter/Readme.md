# RIDE DB Adapter  



## Connect to API  

The API is deployed to Openshift and exposed with a ClusterIP endpoint.   

ClusterIP Endpoint: ride-db-adapter-clusterip  
Port: 5000  

To connect from local, first perform a port-forward to the service:  

```
oc port-forward service/ride-prime-adapter-clusterip 5000  
```  

After this the API can be accessed at :

http://localhost:5000  

The Postman collection for the APi can be found in docs folder.  