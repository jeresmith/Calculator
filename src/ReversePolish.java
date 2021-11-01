import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
public class ReversePolish {
    Stack<Double> s = new Stack<>();
    ShuntingYard shunt = new ShuntingYard();
    Set<String> opsSet = new HashSet<>();
    
    public ReversePolish()
    {
        opsSet.add("+");
        opsSet.add("-");
        opsSet.add("*");
        opsSet.add("/");
    }

    public double runAlgorithm(Queue<String> q)
    {

        while (q.size() != 0)
        {
            String cur = q.poll();

            if (shunt.isDouble(cur))
            {
                s.add(Double.parseDouble(cur));
            }
            else if (opsSet.contains(cur))
            {
                double num1 = s.pop();
                double num2 = s.pop();
                double res = performOp(num1, num2, cur);
                s.add(res);
            }

        }


        return s.pop();
    }

    public double performOp(double a, double b, String op)
    {
        if (addition(op))
        {
            return a + b;
        }
        if (subtraction(op))
        {
            return a - b;
        }
        if (mupltiplication(op))
        {
            return a * b;
        }
        if (division(op))
        {
            return a / b;
        }
        return -1;
    }

    public boolean addition(String op)
    {
        return op.equals("+");
    }
    public boolean subtraction(String op)
    {
        return op.equals("-");
    }
    public boolean mupltiplication(String op)
    {
        return op.equals("*");
    }
    public boolean division(String op)
    {
        return op.equals("/");
    }
}
