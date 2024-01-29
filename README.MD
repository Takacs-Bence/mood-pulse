# Mood Pulse

Daily poll for your mood in your community.

## How to run

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
   }' http://localhost:8080/submissions/vote
```

## How to use

### via browser

1. Have npm installed. Navigate to the ./frontend dir.

2. Execute the command 'npm run build && npm start'

3. Open http://localhost:3000

You can vote and be shown the results immediately for your community. After one vote, voting is disabled. You can delete the hasVote key-value from your local storage to be able to vote again.
The ability to add/detect community is to come.

### via CLI

The functionality is mostly in-memory at the moment, between server start and stop you can do the following:

1. Submit some votes as stated in the 'How to run' section

2. Check the election result by communities

```
curl -X GET \
  http://localhost:8080/result/community/example
```

The result JSON looks like this:

```
{"community":"example","positiveVotePercentage":0.50,"negativeVotePercentage":0.50,"invalidVotePercentage":0.00,"allVotePercentage":2}
```

which informs you, that with the total of 2 votes, none invalid and the yes and no answers were equally distributed (50-50%).
