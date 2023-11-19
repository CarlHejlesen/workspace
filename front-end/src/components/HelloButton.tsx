// HelloButton.tsx


const HelloButton = () => {
  const sendPostRequest = async () => {
    try {
      const response = await fetch('http://localhost:8080/sayhello', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        credentials: 'include', // This is necessary to include cookies with the request
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