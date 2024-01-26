import React, { useState, useEffect } from 'react';
import generateVoteUUID from './uuid-generator';

const Vote = () => {
  const [hasVoted, setHasVoted] = useState(false);

  useEffect(() => {
    // check local storage to see if the user has already voted
    const hasVotedPreviously = localStorage.getItem('hasVoted');
    setHasVoted(Boolean(hasVotedPreviously));
  }, []);

  const handleVote = (event, mood) => {
    event.preventDefault();
    // check if the user has already voted
    if (hasVoted) {
      alert('You have already voted today.');
      return;
    }
    
    const voteId = generateVoteUUID();
    const userId = generateVoteUUID();
    const community = "example";

    // submit Vote to server      
    fetch('/submissions/vote', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        "id": voteId,
        "mood": mood,
        "voter": {
          "id": userId,
          "community": community
        }
      })

    }).then(() => {
      // update local storage to indicate that the user has voted
      localStorage.setItem('hasVoted', 'true');
      setHasVoted(true);
      
      fetch('/result/community/example')
      .then(resp => {
        if (resp.ok) {
          return resp.json();
        } else {
          throw new Error(`Error ${resp.status}: ${resp.statusText}`);
        }
      })
      .then(result => {
        alert(`Thank you for voting ${mood ? 'Good Mood' : 'Bad Mood'}!
          Vote Result:
        
          Community: ${result.community}
          Vote count: ${result.allVotePercentage}
          Positive votes: ${result.positiveVotePercentage * 100.00}%
          Negative votes: ${result.negativeVotePercentage * 100.00}%
          Invalid votes: ${result.invalidVotePercentage * 100.00}%
        `);
      })
      .catch(error => {
    
        console.error('Error while showing result for community ${community}:', error);
        alert('Error while showing result for community ${community}');
      });

    }).catch(error => {
      
      console.error('Error while voting:', error);
      alert('Error while voting. Please try again.');
    });
  };

  return (
    <div>
      <h2>How's your mood today?</h2>
      <button onClick={(event) => handleVote(event, true)} disabled={hasVoted}>
        Good Mood
      </button>
      <button onClick={(event) => handleVote(event, false)} disabled={hasVoted}>
        Bad Mood
      </button>
      {hasVoted && <p>You have already voted today.</p>}
    </div>
  );
};

export default Vote;
