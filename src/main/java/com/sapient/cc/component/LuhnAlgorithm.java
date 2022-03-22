package com.sapient.cc.component;

public final class LuhnAlgorithm {
    private LuhnAlgorithm() {
    }

    public static boolean validateCreditCardNumber(final String number) {

        boolean valid = false;

        int[] ints = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            ints[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        for (int i = ints.length - 2; i >= 0; i = i - 2) {
            int j = ints[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            ints[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        valid = sum % 10 == 0;

        return valid;
    }
}
