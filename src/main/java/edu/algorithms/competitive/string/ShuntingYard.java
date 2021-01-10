package edu.algorithms.competitive.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShuntingYard {

    /**
     * Algoritmo de shunting yard / patio de clasificacion.
     * Presedencia de operadores dentro de una ecuacion.
     * @param input operadores y numeros separados.
     */
    public void shunting_yard(List<Character> input) {
        List<Character> out = new ArrayList<>();
        Stack<Character> op = new Stack<>();
        while (!input.isEmpty()) {
            if (Character.isDigit(input.get(0))) {
                out.add(input.get(0));
            } else {
                if (input.get(0) == '+' || input.get(0) == '-' || input.get(0) == '*'
                        || input.get(0) == '/') {
                    while (!op.isEmpty() && precedencia(input.get(0), op.lastElement())) {
                        out.add(op.lastElement());
                        op.pop();
                    }
                    op.push(input.get(0));
                } else {
                    if (input.get(0) == '(') {
                        op.push(input.get(0));
                    } else {
                        while (!op.empty() && op.lastElement() != '(') {
                            out.add(op.lastElement());
                            op.pop();
                        }
                        op.pop();
                    }
                }
            }
            input.remove(0);
        }
        StringBuilder sb = new StringBuilder();
        int sizeVector = out.size();
        for (int i = 0; i < sizeVector; i++) {
            sb.append(out.get(i));
        }
        while (!op.isEmpty()) {
            sb.append(op.lastElement());
            op.pop();
        }
        sb.append("\n");
        System.out.print(sb);
    }

    private boolean precedencia(Character a, Character b) {
        if (a == '(' || b == '(') {
            return false;
        }
        if ((a == '*' || a == '/') && (b == '+' || b == '-')) {
            return false;
        }
        return true;
    }

}
