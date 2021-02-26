



# Catalog Filter Service



### To Run The Service

1. Navigate into folder `catalog-filter` with: `cd ./catalog-filter`

2. Make sure that Docker is running on your machine

3. To get the Catalog-Filter Service up and running:

   - The Fastest - Cool Way :
     - run command: `docker-compose up`

   - The Traditional Way:
     - run `docker build -t catalog-filter:0.0.1-SNAPSHOT .` to build Dockerimage
     - run `docker run -it -p 80:80 catalog-filter:0.0.1-SNAPSHOT` to start Dockercontainer



### About The Development of this Service 

#### 	

![Modules](./Modules.png)

Figure 1. Service Architechture



![CatalogModel](./CatalogModel.png)

Figure 2. Catalog Datamodel

