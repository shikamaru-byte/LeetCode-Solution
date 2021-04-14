import java.util.HashMap;
import java.util.Map;

public class Calculator {

    public static void main(String[] args) {
        System.out.println(new Calculator().calculate("+2 + 3"));
    }

    Map<Character, Integer> map = new HashMap<>();

    private void initMap() {
        map.put('+', 1);
        map.put('-', 1);
        map.put('*', 2);
        map.put('/', 2);
    }

    public int calculate(String s) {
        initMap();
        s = s.replaceAll("\\s", "").replaceAll("\\(-", "(0-").replaceAll("\\(\\+", "(0+");
        if (s.length() > 0 && s.charAt(0) == '-') s = s.replaceFirst("-", "0-");
        int[] numStack = new int[s.length()];
        char[] opStack = new char[s.length()];
        int numTop = 0, opTop = 0;
        for (int i = 0;i < s.length();i ++) {
            if (Character.isDigit(s.charAt(i))) {
                int x = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    x = x * 10 + s.charAt(i++) - '0';
                }
                numStack[numTop++] = x;
                if (i != s.length()) i--;
                continue;
            }
            if (s.charAt(i) == '(') {
                opStack[opTop++] = s.charAt(i);
                continue;
            }
            if (s.charAt(i) == ')') {
                while (opTop > 0 && numTop > 1 && opStack[opTop - 1] != '(') {
                    doCalculate(numStack, opStack, numTop, opTop);
                    opTop--;
                    numTop--;
                }
                opTop--;
                continue;
            }
            if (opTop > 0 && numTop > 1 && opStack[opTop - 1] != '(' && map.get(opStack[opTop - 1]) >= map.get(s.charAt(i))) {
                doCalculate(numStack, opStack, numTop, opTop);
                opTop--;
                numTop--;
            }
            opStack[opTop++] = s.charAt(i);
        }
        while (opTop > 0 && numTop > 1) {
            doCalculate(numStack, opStack, numTop, opTop);
            opTop--;
            numTop--;
        }
        return numStack[0];
    }

    private void doCalculate(int[] numStack, char[] opStack, int numTop, int opTop) {
        int a = numStack[numTop - 2], b = numStack[numTop - 1];
        if (opStack[opTop - 1] == '+') numStack[numTop - 2] = a + b;
        else if (opStack[opTop - 1] == '-') numStack[numTop - 2] = a - b;
        else if (opStack[opTop - 1] == '*') numStack[numTop - 2] = a * b;
        else if (opStack[opTop - 1] == '/') numStack[numTop - 2] = a / b;
    }

}