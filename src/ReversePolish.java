
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
        trigSet.add("u-");
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
        if (unaryNegativ(op))
        {
            return (a * -1);
        }
        if (isSin(op))
        {
            return Math.sin(a);
        }
        if (isCos(op))
        {
            return Math.cos(a);
        }
        if (isTan(op))
        {
            return Math.tan(a);
        }
        // Check this funtion
        if (isCot(op))
        {
            return 1.0/ Math.tan(a);
        }
        if (isArcSin(op))
        {
            return Math.asin(a);
        }
        if (isArcCos(op))
        {
            return Math.acos(a);
        }
        if (isArcTan(op))
        {
            return Math.atan(a);
        }

        // Check this function
        if (isArcTg(op))
        {
            return Math.atan(1.0 / performTrig(a, "k"));
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
    public boolean unaryNegativ(String trig)
    {
        return trig.equals("u-");
    }
    public boolean isSin(String trig)
    {
        return trig.equals("s");
    }
    public boolean isCos(String trig)
    {
        return trig.equals("c");
    }
    public boolean isTan(String trig)
    {
        return trig.equals("t");
    }
    public boolean isCot(String trig)
    {
        return trig.equals("k");
    }
    public boolean isArcSin(String trig)
    {
        return trig.equals("a");
    }
    public boolean isArcCos(String trig)
    {
        return trig.equals("x");
    }
    public boolean isArcTan(String trig)
    {
        return trig.equals("n");
    }
    public boolean isArcTg(String trig)
    {
        return trig.equals("r");
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
