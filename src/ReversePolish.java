import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
public class ReversePolish {
    Stack<Double> s = new Stack<>();
    ShuntingYard shunt = new ShuntingYard();
    Set<String> opsSet = new HashSet<>();
    Set<String> trigSet = new HashSet<>();

    public ReversePolish()
    {
        opsSet.add("+");
        opsSet.add("-");
        opsSet.add("*");
        opsSet.add("/");
        opsSet.add("^");
        trigSet.add("s");
        trigSet.add("c");
        trigSet.add("t");
        trigSet.add("k");
        trigSet.add("a");
        trigSet.add("x");
        trigSet.add("n");
        trigSet.add("r");
        trigSet.add("l");
        trigSet.add("g");
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
                double num2 = s.pop();
                double num1 = s.pop();
                double res = performOp(num1, num2, cur);
                s.add(res);

            } else if(trigSet.contains(cur))
            {
                double num1 = s.pop();
                double res = performTrig(num1, cur);
                s.add(res);
            }


        }


        return s.pop();
    }
    public double performTrig(double a, String op)
    {
        if (isSin(op))
        {
            return Math.sin(a);
        }
        if(isNaturalLog(op))
        {
            return Math.log(a);
        }
        if(isLog(op))
        {
            return Math.log10(a);
        }
        return -1;
    }

    public boolean isSin(String trig)
    {
        return trig.equals("s");
    }
    public boolean isNaturalLog(String trig)
    {
        return trig.equals("l");
    }
    public boolean isLog(String trig)
    {
        return trig.equals("g");
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
        if (pow(op))
        {
            return Math.pow(a,b);
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
    public boolean pow(String op)
    {
        return op.equals("^");
    }
}
