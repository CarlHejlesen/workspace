// HelloButton.tsx
import React from 'react';

const HelloButton = () => {
  const sendPostRequest = async () => {
    try {
      const response = await fetch('http://localhost:8080/sayhello', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        // body: JSON.stringify({ data: 'Dine data her' }), // Tilføj dette, hvis du har en body i din request
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.text();
      console.log(data);
    } catch (error) {
      console.error('Der opstod en fejl under POST-anmodningen', error);
    }
  };

  return <button onClick={sendPostRequest}>Sig Hej</button>;
};

export default HelloButton;