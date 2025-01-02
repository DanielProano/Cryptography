# Ciphers

## Caesar Cipher
The Caesar Cipher was originally used by Julius Caesar to send secure messages to his troops. 
It works by taking a message and then shifting each letter a set number of positions in the alphabet.
For example, with a shift of 2, 'A' would shift to 'C', 'B' would shift to 'D', ect.

## Monoalphabetic Cipher
This cipher uses a new alphabet to encrypt the message. Each letter in the message corresponds to a
letter in the new alphabet.

## Vigenere Cipher
The Vigenere Cipher works by using a keyword to encrypt the message. Each letter in the keyword
represents an alphabetic shift. What happens if the keyword is not the same length as the message? 
The keyword will repeat until it is the same length. For example, if the message is SECRET and the
key is LION, the logic will use LIONLI to encyrpt the message. 

## Enigma Machine
To make the cipher easier, EnigmaInput should be used.
The Enigma Code was the encryption used by the Germans in WWII to communicate with each other. 
This encryption system is extremely complex and is very effective. First, the initial position is set using the three letter key. After the message is inputted, 
each letter will go through a minimum of 7 shifts. There can be more if pairs of letters are inputted in the plugboard. 
Let's say the message HI is inputted. First, 'H' will go to the plugboard. If 'H' points at 'A', 'A' be the letter 
inputted into the rotors. Within each rotor, the alphabet is entirely scrambled. Therefor, 'A' will get scrambled three times
before reaching the reflector. The reflector is the most essential part, allowing the systematic encryption.
The reflector contains letters that point to each other ('A' points to 'D', 'D' points to 'A', this is not necessary in the rotors).
Then the letters are sent back through the three rotors but in reverse. The plugboard is visited one last time and the cipher text is outputted.

## Hill Cipher
The Hill Cipher uses linear algebra to encode messages. First, a key is used that must be contained in a n x n matrix, eg. 2 x 2, 3 x 3. 
Next, the key encrypts the message n letters at a time, where n is the length of the key. To successfully decrypt the message,
a key that uses the inverse matrix is necessary. A valid key is also necessary, one whose modular inverse determinant is equal to 1.

