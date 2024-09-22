package org.example;

import java.security.SecureRandom;

public class Generator {
    private String generateGroup(int length) {
        StringBuilder group = new StringBuilder();
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z'};
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == 2 || i == 3 || i == 5) {
                group.append(consonants[secureRandom.nextInt(consonants.length)]);
            } else {
                group.append(vowels[secureRandom.nextInt(vowels.length)]);
            }
        }
        return group.toString();
    }

    public String generateAppleEasyToTypePassword() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder();
        int[] numberIndexes = {0, 5, 7, 12, 14, 19}; // possible places of the number
        int numberIndex = numberIndexes[secureRandom.nextInt(numberIndexes.length)];

        int upperCaseIndex = -1;
        while (upperCaseIndex == -1) {
            int possibleUpperCaseIndex = secureRandom.nextInt(20);
            if (possibleUpperCaseIndex != 6 && possibleUpperCaseIndex != 13 && possibleUpperCaseIndex != numberIndex) {
                upperCaseIndex = possibleUpperCaseIndex;
            }
        }

//        for (int i = 0; i < 20; i++) {
//            if (i == 6 || i == 13) {
//                password.append("-");
//            } else if (i == numberIndex) {
//                password.append(secureRandom.nextInt(10));
//            } else if (i == upperCaseIndex) {
//                password.append((char) secureRandom.nextInt(65, 91));
//            } else {
//                password.append((char) secureRandom.nextInt(97, 123));
//            }
//        }

        int i = 0;
        while (i < 20) {
            if (i == 6 || i == 13) {
                password.append("-");
                i++;
            } else if (i == numberIndex) {
                password.append(secureRandom.nextInt(10));
                i++;
            } else if (numberIndex - i == 5 || numberIndex - i == -1) {
                password.append(generateGroup(5));
                i += 5;
            } else {
                password.append(generateGroup(6));
                i += 6;
            }
        }
        password.setCharAt(upperCaseIndex, Character.toUpperCase(password.charAt(upperCaseIndex)));
        return password.toString();
    }

    public String generatePasscode(int length) {
        StringBuilder passcode = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            passcode.append(secureRandom.nextInt(10));
        }
        return passcode.toString();
    }

    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            password.append((char) secureRandom.nextInt(33, 127));
        }
        return password.toString();
    }
}