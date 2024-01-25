import { v4 as uuidv4 } from 'uuid';

// function to generate a UUID for a vote or user
const generateVoteUUID = () => {
  return uuidv4();
};

export default generateVoteUUID;
