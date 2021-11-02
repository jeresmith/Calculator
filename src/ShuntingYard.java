import java.util.*;

public class ShuntingYard
{
    StringChecking stringCheck = new StringChecking();
    Map<String, Integer> map = new HashMap<>();
    Set<String> trig = new HashSet<>();
    boolean[] associativeness = new boolean[3];

    public ShuntingYard()
    {
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
        map.put("^", 3);
        associativeness[0] = true;
        associativeness[1] = true;
        associativeness[2] = false;
        trig.add("u-");


    }

    public Queue<String> runShuntingYard(String string)
    {

        Queue<String> cur = convertString(string);
        Queue<String> res = new LinkedList<>();

        Stack<String> op = new Stack<>();

        while(!cur.isEmpty())
        {
            String s = cur.poll();


            if(isDouble(s))
            {

                res.add(s);
            }

            else if(isLeftParenthesis(s))
            {

                op.add(s);

            }

            else if(isRightParenthesis(s))
            {

                String looking = op.pop();

                while(!isLeftParenthesis(looking))
                {
                    res.add(looking);
                    looking = op.pop();
                }
                if(!op.isEmpty() && isTrig(op.peek()))
                {
                    res.add(op.pop());
                }

            }
            else if(isTrig(s))
            {
                op.add(s);
            }

            else if(!op.isEmpty() && map.containsKey(s))
            {
                String look = op.peek();

                if(map.containsKey(look) && map.containsKey(s))
                {

                    // At the end the assosiativeness is if its a power ^ symbol we treat is different
                    while(!op.isEmpty() && map.containsKey(look))
                    {
                        if (map.get(s) < map.get(look) || (map.get(look).equals(map.get(s)) && !associativeness[map.get(look)])) {
                            res.add(look);
                            op.pop();
                        }
                        else {
                            break;
                        }
                        if(!op.isEmpty()){
                            look = op.peek();
                        }

                    }
                }
                op.add(s);
            }
            else{

                op.add(s);
            }
        }

        while (!op.isEmpty())
        {
            res.add(op.pop());
        }
        return res;
    }

    public Queue<String> convertString(String s)
    {
        Queue<String> q = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (stringCheck.operators.contains(c) || stringCheck.isParenthesis(c) || stringCheck.trig.contains(c)) {
                String current = String.valueOf(c);
                q.add(current);
                continue;
            }
            if (c == '-') {
                if (i - 1 >= 0 && i + 1 < s.length()) {
                    if ((Character.isDigit(s.charAt(i - 1)) || isRightParenthesis(s.substring(i-1,i))) && (Character.isDigit(s.charAt(i + 1)) || stringCheck.trig.contains(s.charAt(i+1)) || isLeftParenthesis(s.substring(i+1,i+2)))) {
                        q.add(String.valueOf(c));
                        continue;
                    }

                    if(map.containsKey(s.substring(i-1,i)) || (s.charAt(i-1) == '-' && s.charAt(i + 1) != '-'))
                    {
                        q.add("u-");
                        continue;
                    }

                }
                if (i + 1 < s.length()) {
                    if (stringCheck.isParenthesis(s.charAt(i + 1))) {
                        q.add(String.valueOf(c));
                        continue;
                    }
                }

                if (i + 3 < s.length())
                {
                    if (stringCheck.trig.contains(s.charAt(i+1)))
                    {
                        q.add("u-");
                        continue;
                    }
                }


            }

            int j = i;
            while (j != s.length())
            {
                c = s.charAt(j);
                if (j == i && c == '-')
                {
                    j++;

                }
                else if ((Character.isDigit(c) || c == '.'))
                {
                    j++;

                } else {
                    break;
                }

            }

                String subString = s.substring(i, j);
                q.add(subString);
                i = j - 1;

        }



        return q;
    }
    public boolean isDouble(String s)
    {
        boolean check = true;
        try
        {
            Double.parseDouble(s);
        }
        catch (Exception e)
        {
            check = false;
        }
        return check;
    }
    public boolean isTrig(String s)
    {
        return stringCheck.validTrig(s);
    }

    public boolean isLeftParenthesis(String s)
    {
        return s.equals("(") || s.equals("{") || s.equals("[");
    }
    public boolean isRightParenthesis(String s)
    {
        return s.equals(")") || s.equals("}") || s.equals("]");
    }
}
