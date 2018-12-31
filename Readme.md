## Sample API to demonstrate JWT authentication and configuring AWS s3 SDk to store and retrieve files

* Architecture:


* DB:


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
