package algo.string;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bernard on 22/3/15.
 */
public class EquationEvaluation {

    private static final String EQUATION_OPERATORS = "()*/+-";

    private static final Pattern operatorsPattern = Pattern.compile("[\\(\\)\\*\\/\\+\\-]");

    private static final Pattern numbersPattern = Pattern.compile("[-+]?[0-9]+\\.?[0-9]*");

    private static int operatorPriorityOf(String operator) {
        return EQUATION_OPERATORS.indexOf(operator);
    }

    private static List<String> parse(String equation) {
        StringTokenizer tokenizer = new StringTokenizer(equation, EQUATION_OPERATORS, true);
        Deque<String> operators = new ArrayDeque<>();
        List<String> parsed = new ArrayList<>();

        Matcher operatorsMatcher = operatorsPattern.matcher("");
        Matcher numbersMatcher = numbersPattern.matcher("");

        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            if(token.length() == 0) continue;

            if(operatorsMatcher.reset(token).matches()) {
                if(!")".equals(token)) {
                    while (!operators.isEmpty()) {
                        String lastOpt = operators.peek();
                        if ("(".equals(lastOpt)) break;
                        if (operatorPriorityOf(lastOpt) > operatorPriorityOf(token)) break;
                        parsed.add(operators.pop());
                    }
                    operators.push(token);
                } else {
                    while(true) {
                        if(operators.isEmpty())
                            throw new IllegalArgumentException("Syntax Error: Imbalanced parenthesis.");

                        String lastOpt = operators.pop();
                        if(!"(".equals(lastOpt)) parsed.add(lastOpt);
                        else break;
                    }
                }
            } else if(numbersMatcher.reset(token).matches()){
                parsed.add(token);
            } else {
                String errorMessage = String.format("Syntax Error: Unrecognized token '%s'.", token);
                throw new IllegalArgumentException(errorMessage);
            }
        }

        while(!operators.isEmpty()) parsed.add(operators.pop());

        return parsed;
    }

    private static Double evaluate(List<String> parsedTokens) {
        Deque<Double> stack = new ArrayDeque<>();
        Matcher operatorsMatcher = operatorsPattern.matcher("");
        Matcher numbersMatcher = numbersPattern.matcher("");

        for(String token : parsedTokens) {
            if(numbersMatcher.reset(token).matches()) {
                stack.push(Double.valueOf(token));
            } else if(operatorsMatcher.reset(token).matches()) {
                if(stack.size() < 2)
                    throw new IllegalArgumentException("Runtime Error: No sufficient operand for the operator '" + token + "'.");
                Double operand2 = stack.pop();
                Double operand1 = stack.pop();
                Double result;

                switch(token) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                    default:
                        throw new IllegalArgumentException("Runtime Error: Unrecognized operator '" + token +"'.");
                }

                stack.push(result);
            }
        }

        if(stack.size() > 1)
            throw new IllegalArgumentException("Runtime Error: Incomplete expression.");

        return stack.isEmpty()? null : stack.pop();
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                System.out.print(">");
                String expression = scanner.nextLine();
                if(expression.equals("quit")) break;
                List<String> tokens = parse(expression);
                Double result = evaluate(tokens);
                System.out.println(" = " + String.valueOf(result));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
