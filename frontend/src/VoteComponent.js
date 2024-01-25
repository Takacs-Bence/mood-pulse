import React, { useState, useEffect } from 'react';
import generateVoteUUID from './uuid-generator';

const Vote = () => {
  const [hasVoted, setHasVoted] = useState(false);

  useEffect(() => {
    // check local storage to see if the user has already voted
    const hasVotedPreviously = localStorage.getItem('hasVoted');
    setHasVoted(Boolean(hasVotedPreviously));
  }, []);

  const handleVote = async (mood) => {
    // check if the user has already voted
    if (hasVoted) {
      alert('You have already voted today.');
      return;
    }
    
    const voteId = generateVoteUUID();
    const userId = generateVoteUUID();
    const community = "example";

    try {
      // submit Vote to server      
      await fetch('/submissions/vote', {
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
          }),
      });

      // update local storage to indicate that the user has voted
      localStorage.setItem('hasVoted', 'true');
      setHasVoted(true);

      alert(`Thank you for voting ${mood ? 'Good Mood' : 'Bad Mood'}!`);
    } catch (error) {
      console.error('Error while voting:', error);
      alert('Error while voting. Please try again.');
    }
  };

  return (
    <div>
      <h2>How's your mood today?</h2>
      <button onClick={() => handleVote(true)} disabled={hasVoted}>
        Good Mood
      </button>
      <button onClick={() => handleVote(false)} disabled={hasVoted}>
        Bad Mood
      </button>
      {hasVoted && <p>You have already voted today.</p>}
    </div>
  );
};

export default Vote;
