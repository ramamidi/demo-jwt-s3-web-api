## Sample API to demonstrate JWT authentication and configuring AWS s3 SDk to store and retrieve files

* Architecture:

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/demo-web-api-arch.png "arch")

* DB:

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/db.png "db")


#### Local setup:

* Install Java 8 and latest Mysql

* Create a mysql db and s3 bucket
    * steps to create s3 bucket: https://docs.aws.amazon.com/quickstarts/latest/s3backup/step-1-create-bucket.html
* Configure application.yml file
    ```
            server.port: 9966
            server.host.name : "http://localhost:9966"
            
            
            ramdemo.security.jwt:
              tokenExpirationTime: 60 # Number of minutes
              refreshTokenExpTime: 90 # Minutes
              tokenIssuer: http://webdemo.com
              tokenSigningKey: xm8EV6Hy5RMFK4EEACIDAwQus
            
            aws.s3:
              url: <your-s3-url> # ex: https://s3.amazonaws.com
              accessKey: <your-s3-accessKey>
              secretKey: <your-s3-s3 secretKey>
              bucket: <your-s3-bucket>
            
            spring.datasource:
              url: "jdbc:mysql://localhost:3306/<you-db-name>?useSSL=false"
              username: <your-mysql-username>
              password: <your-mysql-pass>
            spring.jpa:
              show-sql: true
    ```

* Steps to run:
    * cd into demo-web-api
    * `mvn clean install`
    * `java -jar target/demo-web-api-1.0-SNAPSHOT.jar`


#### Testing using postman:

* Login End point

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/login-endpoint.png "login")


* save location info End point(Saves location info in DB and creates a folder in S3 with locationInfoId)

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/location-info-save.png "location-info-savee")

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/location-into-save-bearer.png "location-info-save-bearer")

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/Screen%20Shot%202018-12-31%20at%2012.32.34%20PM.png "location-info-save-bearer")


* upload file End point(Uploads file into to the specified location info id folder and returns s3 file url)

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/upload-file.png "upload file")

![alt text](https://github.com/ramamidi/media/blob/master/demo-web-api/s3-upload.png "s3 upload")