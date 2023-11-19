import { useState } from 'react'

const LoginButton = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const sendPostRequest = async () => {
        // Nulstil fejlmeddelelsen ved hver anmodning
        setErrorMessage('');

        // Validering af input
        if (!username || !password) {
            setErrorMessage('Brugernavn og adgangskode må ikke være tomme.');
            return; // Stop funktionen, hvis valideringen fejler
        }

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            console.log(data);
            // Her vil du måske navigere brugeren til en anden side eller opdatere UI'et
        } catch (error) {
            console.error('Der opstod en fejl under POST-anmodningen', error);
            setErrorMessage('En fejl opstod ved indsendelse af data.');
        }
    };

    return (
        <div id="main_login_box" style={{       
            border: '1px solid red', // Rød kant
                  
            margin: 'auto',          // Centrerer boksen på siden
            maxWidth: '60vw',       // Maksimal bredde af boksen
            maxHeight: "40vh",
            width: '60vw',       // Maksimal bredde af boksen
            height: "40vh",
            display: 'flex',         // Anvender flexbox layout
            flexDirection: 'column', // Stabler børnene vertikalt
            alignItems: 'center',    // Centrerer børnene horisontalt
            justifyContent: 'center',// Centrerer børnene vertikalt
            borderRadius: '5px',     // Afrunder hjørnerne
            boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2)',
            background: "lightgrey",
        }}>

            <div style={{
                paddingTop: "10px",
                paddingRight: "15px",
                paddingBottom: "5px",
                paddingLeft: "20px",

            }}>
                <tr style={{

                }}>
                    <td>Brugernavn:</td>
                    <td><input
                        type="text"
                        placeholder="Brugernavn"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                    /></td>
                </tr>

            </div>
            <div style={{
                padding: '20px',

            }}>
                <tr >
                    <td>
                        Adgangskode:
                    </td>
                    <td>
                        <input
                            type="password"
                            placeholder="Adgangskode"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </td>
                </tr>

            </div>
            <div id="login button div">
                <button onClick={sendPostRequest}>Login knap</button>
            </div>
            <div >
                {errorMessage && <p>{errorMessage}</p>}
            </div>

        </div>
    );
};

export default LoginButton;
