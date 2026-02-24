![Common_Cryptography_Alogorithms](https://github.com/user-attachments/assets/08ddb4a1-789f-48b0-8127-4e82de9ab192)

# Ciphers

## Enigma Machine
The Enigma Code was the encryption used by the Germans in WWII.

This reimplements the encryption scheme, using matrices

to accurately replicate the physical gears and reflector board

that made up the original design. 

<br>

Supports any alphanumeric transposition and plugboard and 

rotor configuration. 

## Caesar Cipher

This reimplements the Caesar Cipher in Java, a cipher

that encrypts by transposing plaintext by some offset

in the alphabet.

## Monoalphabetic Cipher

This cipher uses a new alphabet to encrypt the message.

Each letter in the message corresponds to a

letter in the new alphabet.

## Vigenere Cipher

The Vigenere Cipher works by using a keyword to encrypt the message.

Each letter in the keyword represents an alphabetic shift. What happens

if the keyword is not the same length as the message? The keyword will

repeat until it is the same length. For example, if the message is

SECRET and the key is LION, the logic will use LIONLI to encyrpt the message. 

## Hill Cipher

The Hill Cipher uses linear algebra to encode messages. First, a

key is used that must be contained in a n x n matrix, eg. 2 x 2, 3 x 3. 

Next, the key encrypts the message n letters at a time, where n is

the length of the key. To successfully decrypt the message,

a key that uses the inverse matrix is necessary. 

A valid key is also necessary, one whose modular inverse determinant is equal to 1.

## Disclaimer

This was one of my first large projects done over my freshmen

winter break, so the quality of the code is not

up to the level that I am capable of now. The main

takeaway was the ability to understand and implement

complex, theoretical systems from history.

