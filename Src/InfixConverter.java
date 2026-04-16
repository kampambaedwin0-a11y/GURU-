import java.util.Stack;

public class InfixConverter {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert infix to postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }
            // If '(', push to stack
            else if (c == '(') {
                stack.push(c);
            }
            // If ')', pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }
            // Operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Function to reverse a string
    static String reverse(String exp) {
        StringBuilder sb = new StringBuilder(exp);
        sb.reverse();
        return sb.toString();
    }

    // Function to convert infix to prefix
    static String infixToPrefix(String exp) {
        // Step 1: Reverse infix
        String reversed = reverse(exp);

        // Step 2: Swap brackets
        String modified = "";
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (c == '(') {
                modified += ')';
            } else if (c == ')') {
                modified += '(';
            } else {
                modified += c;
            }
        }

        // Step 3: Convert to postfix
        String postfix = infixToPostfix(modified);

        // Step 4: Reverse postfix → prefix
        return reverse(postfix);
    }

    public static void main(String[] args) {
        String infix = "(A+B)*C-D";

        System.out.println("Infix Expression  : " + infix);
        System.out.println("Postfix Expression: " + infixToPostfix(infix));
        System.out.println("Prefix Expression : " + infixToPrefix(infix));
    }
}
