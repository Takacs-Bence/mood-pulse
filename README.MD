# Mood Pulse

Daily poll for your mood in your community.

## How to use

1. Compile the project

    mvn clean package (Maven is required)
    
2. Run the server locally <br />
    switch to the target dir then run: <br />
    java -jar mood-pulse-{version}.jar
   
3. Use a HTTP client e.g. curl to add your vote in the following format: 
```
   curl -X POST -H "Content-Type: application/json" -d '{
   "id": "91599997-5c12-4721-aa38-80f4fa411b32",
   "mood": true,
   "voter": {
   "id": "4734f7f1-31ee-4d28-963b-10dab763aa02",
   "community": "example-community"
   }
   }' http://localhost:8080/vote
```
