## **How to Start the Application**

1. Install Docker Desktop
   
3. open command prompt
   
4. Enter this command:  **docker pull redis** 
   
   This will install redis with docker
   
5. Next enter this command: **docker run -d --name redis-container -p 6379:6379 redis** 

   This will create a new docker container for redis on port 6379.
    
6. Open docker desktop, the container we just created should now be visible. Run it

7. Then open the Springboot application found in the Git repository using Intellij IDE and Run it.

8. Lastly, Test the API Endpoints using Postman or any other similar software. 

