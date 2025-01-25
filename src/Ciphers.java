import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ciphers {
    private static final String QUESTION = "Which cipher would you like to use?";
    private static final String OPTIONS = "1 Caesar\n2 Monoalphabetic cipher\n3 Vigenere Cipher\n" +
            "4 Enigma Code\n5 Hill Cipher\nExit\n";
    private static final String CAESARINPUT = "Please enter the text";
    private static final String CAESARSHIFT = "Please enter the number of shifts";
    private static final String NUMBERERROR = "Please enter a valid int";

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALPHABETKEY= "Please enter the alphabet key (one single line, no spaces)";
    private static final String ENCYRPTORDECRYPT = "Encrypt or Decrypt? (E/D)";
    private static final String ALPHABETINPUT = "Please enter your text";
    private static final String CONTINUE = "Would you like to continue? (yes/no)";
    private static final String ERROR = "Too many letters in the alphabet key";

    private static final String VIGKEY = "Please enter your key";
    private static final String VIGINPUT = "Please enter your text";

    private static final String ROTORONE = "Please enter the first rotor";
    private static final String ROTORTWO = "Please enter the second rotor";
    private static final String ROTORTHREE = "Please enter the third rotor";
    private static final String ENIGMAREFLECTOR = "Please enter the reflector board pairs";
    private static final String PLUGBOARD = "Please enter the plug-board " +
            "(leave spaces empty if switches aren't desired)";
    private static final String ENIGMAKEY = "Please enter your key (three letters)";
    private static final String ENIGMAINPUT = "Please enter your message";
    private static final String ENIGMAERROR = "Rotors do not have 26 letters";
    private static final String PLUGBOARDPROBLEM = "Plug board has a problem";
    private static int enigmaFirstCount = 0;
    private static int enigmaSecondCount = 0;

    private static final String MATRIXMESSAGE = "What is your message";
    private static final String MATRIXKEY = "Please enter your encryption key (integers separated" +
            " by commas, no spaces or special characters)";
    private static final String MATRIXERROR = "You're key does not work or your key is not a 2x2, 3x3, ect.";

    public static void main(String[] args) {
        try (BufferedReader r = new BufferedReader(new FileReader("EnigmaInput"))) {
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println(QUESTION);
                System.out.println(OPTIONS);
                String userChoice = scan.nextLine();

                switch (userChoice) {
                    case "1":
                        System.out.println(CAESARINPUT);
                        String text = scan.nextLine();

                        System.out.println(CAESARSHIFT);
                        String shift = "";
                        if (scan.hasNextInt()) {
                            shift = scan.nextLine();
                        } else {
                            System.out.println(NUMBERERROR);
                            scan.nextLine();
                            break;
                        }
                        String result = caesar(text, Integer.parseInt(shift));
                        System.out.println(result);
                        break;
                    case "2":
                        boolean alphaContinue = false;
                        System.out.println(ALPHABETKEY);
                        System.out.println(ALPHABET);
                        String key = scan.nextLine();
                        do {
                            System.out.println(ENCYRPTORDECRYPT);
                            String option = scan.nextLine();

                            System.out.println(ALPHABETINPUT);
                            String input = scan.nextLine();

                            String output = substitution(input, key, option);
                            if (output.equals(ERROR)) {
                                break;
                            }
                            System.out.println(output);

                            System.out.println(CONTINUE);
                            alphaContinue = scan.nextLine().equalsIgnoreCase("yes");
                        } while (alphaContinue);

                        break;
                    case "3":
                        System.out.println(VIGKEY);
                        String vigKey = scan.nextLine();
                        boolean vigContinue = false;
                        do {
                            System.out.println(ENCYRPTORDECRYPT);
                            String encryptQuestion = scan.nextLine();
                            System.out.println(VIGINPUT);
                            String vigInput = scan.nextLine();

                            String output = vigerene(vigInput, vigKey, encryptQuestion);
                            System.out.println(output);

                            System.out.println(CONTINUE);
                            vigContinue = scan.nextLine().equalsIgnoreCase("yes");
                        } while (vigContinue);
                        break;
                    case "4":
                        boolean enigmaBoolean = false;
                        System.out.println(ALPHABET);
                        System.out.println(ROTORONE);
                        String rotorOne = r.readLine();

                        System.out.println(ROTORTWO);
                        String rotorTwo = r.readLine();

                        System.out.println(ROTORTHREE);
                        String rotorThree = r.readLine();

                        System.out.println(ENIGMAREFLECTOR);
                        String reflector = r.readLine();

                        System.out.println(PLUGBOARD);
                        String plugBoard = r.readLine();
                        do {
                            System.out.println(ENIGMAKEY);
                            String enigmaKey = r.readLine();

                            System.out.println(ENIGMAINPUT);
                            String message = r.readLine();

                            String output = enigma(rotorOne, rotorTwo, rotorThree,
                                    reflector, plugBoard, enigmaKey, message);
                            System.out.println(output);

                            System.out.println(CONTINUE);
                            enigmaBoolean = r.readLine().equalsIgnoreCase("yes");
                        } while (enigmaBoolean);
                        break;
                    case "5":
                        System.out.println(MATRIXKEY);
                        String matrixKey = scan.nextLine();

                        boolean matrixBoolean = false;

                        do {
                            System.out.println(MATRIXMESSAGE);
                            String message = scan.nextLine();

                            String output = matrix(message, matrixKey);
                            System.out.println(output);

                            System.out.println(CONTINUE);
                            matrixBoolean = scan.nextLine().equalsIgnoreCase("yes");
                        } while (matrixBoolean);
                        break;

                    case "Exit":
                        return;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Caesar cipher
    public static String caesar(String input, int shifts) {
        String result = ""; //store final value
        if (shifts >= 0 && shifts < 26) { //check if shifts are + or -
            for (int i = 0; i < input.length(); i++) { //run through each position in the string
                int currentChar = input.substring(i, i + 1).charAt(0); //get current char position
                if (currentChar >= 65 && currentChar <= 90) { //check if char is Uppercase
                    if (currentChar + shifts > 90) {
                        result += (char) (64 + (shifts - (90 - currentChar)));
                    } else {
                        result += (char) (currentChar + shifts);
                    }
                } else if (currentChar >= 97 && currentChar <= 122){ //check if char is lowercase
                    if (currentChar + shifts > 122) {
                        result += (char) (96 + (shifts - (122 - currentChar)));
                    } else {
                        result += (char) (currentChar + shifts);
                    }
                } else {
                    result = null;
                }
            }
        } else if (shifts >= 26) {
            result = caesar(input, shifts - 26);
        } else if (shifts < 0) {
            result = caesar(input, 26 + shifts);
        }
        return result;
    }

    public static String substitution(String input, String key, String choice) {
        String output = "";
        if (key.length() != 26) {
            throw new ArithmeticException();
        }
        try {
            char[][] newAlph = new char[2][26];
            for (int i = 0; i < ALPHABET.length(); i++) {
                newAlph[0][i] = ALPHABET.charAt(i);
                newAlph[1][i] = key.charAt(i);
            }

            if (choice.equalsIgnoreCase("E")) {
                for (int j = 0; j < input.length(); j++) {
                    char currentChar = input.toLowerCase().charAt(j);
                    if (currentChar == ' ') {
                        output += ' ';
                        continue;
                    }
                    for (int k = 0; k < newAlph[0].length; k++) {
                        if (currentChar == newAlph[0][k]) {
                            output += newAlph[1][k];
                        }
                    }
                }
            } else if (choice.equalsIgnoreCase("D")) {
                for (int j = 0; j < input.length(); j++) {
                    char currentChar = input.toLowerCase().charAt(j);
                    if (currentChar == ' ') {
                        output += ' ';
                        continue;
                    }
                    for (int k = 0; k < newAlph[1].length; k++) {
                        if (currentChar == newAlph[1][k]) {
                            output += newAlph[0][k];
                        }
                    }
                }
            }
        } catch (ArithmeticException e) {
            return ERROR;
        }
        return output;
    }

    public static String vigerene(String input, String key, String choice) {
        String keyRepeat = "";
        String output = "";
        if (choice.equalsIgnoreCase("E")) {
            for (int i = 0, j = 0; i < input.length(); j++, i++) {
                keyRepeat += key.toLowerCase().charAt(j);
                if (j == key.length() - 1) {
                    j = -1;
                }
            }

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') {
                    output += ' ';
                    continue;
                }
                for (int j = 0; j < ALPHABET.length(); j++) {
                    if (keyRepeat.charAt(i) == ALPHABET.charAt(j)) {
                        if ((j + input.toLowerCase().charAt(i)) > 122) {
                            output += (char) (96 + (j - (122 - input.toLowerCase().charAt(i))));
                            break;
                        } else {
                            output += (char) (j + input.toLowerCase().charAt(i));
                            break;
                        }
                    }
                }
            }
        } else if (choice.equalsIgnoreCase("D")) {
            for (int i = 0, j = 0; i < input.length(); j++, i++) {
                keyRepeat += key.toLowerCase().charAt(j);
                if (j == key.length() - 1) {
                    j = -1;
                }
            }

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') {
                    output += ' ';
                    continue;
                }
                for (int j = 0; j < ALPHABET.length(); j++) {
                    if (keyRepeat.charAt(i) == ALPHABET.charAt(j)) {
                        if ((input.toLowerCase().charAt(i) - j) < 97) {
                            output += (char) (123 - (j + (97 - input.toLowerCase().charAt(i))));
                            break;
                        } else {
                            output += (char) (input.toLowerCase().charAt(i) - j);
                            break;
                        }
                    }
                }
            }
        }
        return output;
    }

    public static String enigma(String rotorOne, String rotorTwo, String rotorThree,
                                String reflector, String plugBoard, String startingPosition, String message) {
        String output = "";
        char temp = ' ';
        if (rotorOne.length() != 26 || rotorTwo.length() != 26 || rotorThree.length() != 26) {
            return ENIGMAERROR;
        }

        char[][] rotors = new char[6][26];

        String plugBoardString = "";
        if (plugBoard.length() < 26) {
            for (int i = 0; i < plugBoard.length(); i++) {
                plugBoardString += plugBoard.charAt(i);
            }
            for (int i = 0; i < 26 - plugBoard.length(); i++) {
                plugBoardString += ' ';
            }
        }

        for (int i = 0; i < 26; i++) {
            rotors[0][i] = ALPHABET.charAt(i);
            rotors[1][i] = rotorOne.toLowerCase().charAt(i);
            rotors[2][i] = rotorTwo.toLowerCase().charAt(i);
            rotors[3][i] = rotorThree.toLowerCase().charAt(i);
            rotors[4][i] = reflector.toLowerCase().charAt(i);
            rotors[5][i] = plugBoardString.toLowerCase().charAt(i);
        }

        if (!checkPlugBoard(rotors[5])) {
            return PLUGBOARDPROBLEM;
        }

        for (int i = 0; i < 3; i++) {
            char setToStartingPos = startingPosition.toLowerCase().charAt(i);

            if (i == 0) {
                while (setToStartingPos != rotors[1][i]) {
                    rotateFirstRow(rotors);
                }
            } else if (i == 1) {
                while (setToStartingPos != rotors[1][i]) {
                    rotateSecondRow(rotors);
                }
            } else {
                while (setToStartingPos != rotors[1][i]) {
                    rotateThirdRow(rotors);
                }
            }
        }

        for (int i = 0; i < message.length(); i++) {

            if (message.charAt(i) == ' ') {
                output += ' ';
                continue;
            } else if (message.charAt(i) == '.') {
                output += '.';
                continue;
            }

            for (int n = 0; n < ALPHABET.length(); n++) {
                if (message.toLowerCase().charAt(i) == ALPHABET.charAt(n)) {
                    if (rotors[5][n] != ' ') {
                        temp = rotors[5][n];
                        break;
                    } else {
                        temp = message.toLowerCase().charAt(i);
                        break;
                    }
                }
            }
            rotateFirstRow(rotors);

            for (int j = 0; j < rotors[0].length; j++) {
                if (temp == rotors[0][j]) {
                    temp = rotors[1][j];
                    break;
                }
            }

            if (enigmaFirstCount == 26) {
                enigmaFirstCount = 0;
                rotateSecondRow(rotors);
            }

            for (int k = 0; k < rotors[0].length; k++) {
                if (temp == rotors[0][k]) {
                    temp = rotors[2][k];
                    break;
                }
            }

            if (enigmaSecondCount == 26) {
                enigmaSecondCount = 0;
                rotateThirdRow(rotors);
            }

            for (int l = 0; l < rotors[0].length; l++) {
                if (temp == rotors[0][l]) {
                    temp = rotors[3][l];
                    break;
                }
            }

            for (int m = 0; m < rotors[0].length; m++) {
                if (temp == rotors[0][m]) {
                    temp = rotors[4][m];
                    break;
                }
            }

            for (int l = 0; l < rotors[3].length; l++) {
                if (temp == rotors[3][l]) {
                    temp = rotors[0][l];
                    break;
                }
            }

            for (int k = 0; k < rotors[2].length; k++) {
                if (temp == rotors[2][k]) {
                    temp = rotors[0][k];
                    break;
                }
            }

            for (int j = 0; j < rotors[1].length; j++) {
                if (temp == rotors[1][j]) {
                    temp = rotors[0][j];
                    break;
                }
            }

            for (int n = 0; n < ALPHABET.length(); n++) {
                if (temp == ALPHABET.charAt(n)) {
                    if (rotors[5][n] != ' ') {
                        temp = rotors[5][n];
                        break;
                    }
                }
            }

            output += temp;
        }
        return output;
    }

    public static void rotateFirstRow(char[][] matrix) {
        char temp = ' ';
        for (int i = 0; i < matrix[1].length - 1; i++) {
            temp = matrix[1][i];
            matrix[1][i] = matrix[1][i + 1];
            matrix[1][i + 1] = temp;
        }
        enigmaFirstCount++;
    }

    public static void rotateSecondRow(char[][] matrix) {
        char temp = ' ';
        for (int i = 0; i < matrix[2].length - 1; i++) {
            temp = matrix[2][i];
            matrix[2][i] = matrix[2][i + 1];
            matrix[2][i + 1] = temp;
        }
        enigmaSecondCount++;
    }

    public static void rotateThirdRow(char[][] matrix) {
        char temp = ' ';
        for (int i = 0; i < matrix[3].length - 1; i++) {
            temp = matrix[3][i];
            matrix[3][i] = matrix[3][i + 1];
            matrix[3][i + 1] = temp;
        }
    }

    public static boolean checkPlugBoard(char[] plugBoard) {
        for (int i = 0; i < 26; i++) {
            if (plugBoard[i] != ' ') {
                char temp = ' ';
                for (int j = 0; j < 26; j++) {
                    if (ALPHABET.charAt(i) == plugBoard[j]) {
                        temp = ALPHABET.charAt(j);
                        break;
                    }
                }
                if (plugBoard[i] != temp) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String matrix(String message, String key) {
        int matrixSize = 0;
        String[] keyTemp = key.toLowerCase().split(",");
        for (int i = 1; i <= keyTemp.length / 2; i++) {
            if (keyTemp.length == i * i) {
                matrixSize = i;
            }
        }

        if (matrixSize == 0) {
            return MATRIXERROR;
        }

        int[][] keyMatrix = new int[matrixSize][matrixSize];
        int[][] finalMatrix = new int[matrixSize][1];
        int[][] messageMatrix = new int[matrixSize][1];
        String output = "";

        //fill key matrix
        int keyCount = 0;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                keyMatrix[i][j] = Integer.parseInt(keyTemp[keyCount++]);
            }
        }

        if (message.length() % matrixSize != 0) {
            message += 'x';
        }

        int messageCount = 0;
        for (int i = 0; i < (message.length() / matrixSize); i++) {

            for (int k = 0; k < matrixSize; k++) {
                messageMatrix[k][0] = message.toLowerCase().charAt(messageCount++) - 'a';
            }

            for (int j = 0; j < matrixSize; j++) {
                finalMatrix[j][0] = 0;
                for (int k = 0; k < matrixSize; k++) {
                    finalMatrix[j][0] += keyMatrix[j][k] * messageMatrix[k][0];
                }
                finalMatrix[j][0] %= 26;
            }

            for (int[] c : finalMatrix) {
                for (int v : c) {
                    output += (char) (v + 'a');
                }
            }
        }
        return output;
    }
}
