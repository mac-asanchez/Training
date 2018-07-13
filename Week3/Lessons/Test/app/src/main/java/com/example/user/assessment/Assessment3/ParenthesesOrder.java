package com.example.user.assessment.Assessment3;

public class ParenthesesOrder {
    public static boolean Check(String input) {
        int OpenParentheses = 0;
        int OpenBrackets = 0;
        int OpenCurlyBrackets = 0;
        char[] arrayInput = input.toCharArray();
        for (int i = 0; i < arrayInput.length; i++) {
            //region count Open and close Parentheses / Brackets
            switch (arrayInput[i]) {
                case '(': {
                    OpenParentheses++;
                    break;
                }
                case ')': {
                    if (OpenParentheses <= 0) {
                        return false;
                    }
                    OpenParentheses--;
                    break;
                }
                case '{' :{
                    OpenCurlyBrackets++;
                    break;
                }
                case '}' :{
                    if (OpenCurlyBrackets <= 0) {
                        return false;
                    }
                    OpenCurlyBrackets--;
                    break;
                }
                case '[' :{
                    OpenBrackets++;
                    break;
                }
                case ']' :{
                    if (OpenBrackets <= 0) {
                        return false;
                    }
                    OpenBrackets--;
                    break;
                }
            }
            //endregion
        }

        return (OpenBrackets == 0 && OpenParentheses == 0 && OpenCurlyBrackets == 0);
    }
}
