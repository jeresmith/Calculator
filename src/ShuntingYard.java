import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Map;

public class ShuntingYard
{
    StringChecking stringCheck = new StringChecking();
    Map<String, Integer> map = new HashMap<>();
    public ShuntingYard()
    {
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
        map.put("^", 3);

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
                continue;
            }

            if(isLeftParenthesis(s))
            {
                op.add(s);
                continue;
            }

            if(isRightParenthesis(s))
            {
                String looking = op.pop();

                while(!isLeftParenthesis(looking))
                {
                    res.add(looking);
                    looking = op.pop();
                }
                continue;
            }
            if(!op.isEmpty())
            {


                String look = op.peek();

                if(map.containsKey(look))
                {
                    while(!op.isEmpty() && (map.get(look) < map.get(s) || map.get(look) < map.get(s)))
                    {
                        res.add(look);
                        op.pop();

                        if(!op.isEmpty()){
                            look = op.peek();
                        }

                    }
                    op.add(s);

                }
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

            if (stringCheck.operators.contains(c) || stringCheck.isParenthesis(c)) {
                String current = String.valueOf(c);
                q.add(current);
                continue;
            }
            if (c == '-') {
                if (i - 1 >= 0 && i + 1 < s.length()) {
                    if (Character.isDigit(s.charAt(i - 1)) && Character.isDigit(s.charAt(i - 1))) {
                        q.add(String.valueOf(c));
                        continue;
                    }

                }
                if (stringCheck.isParenthesis(s.charAt(i + 1))) {
                    q.add(String.valueOf(c));
                    continue;
                }

            }


            int j = i;

            while (j != s.length() - 1)
            {

                if (j == i && c == '-')
                {
                    j++;
                    c = s.charAt(j);
                    System.out.println(j);
                    continue;

                }
                if ((Character.isDigit(c) || c == '.'))
                {
                    j++;
                    c = s.charAt(j);

                    continue;
                }
                break;
            }

                boolean end = j == s.length() - 1;

            boolean incremented = false;
                if (end && stringCheck.isParenthesis(s.charAt(j))) {
                    j = j;
                }
                else if (end && Character.isDigit(s.charAt(j))) {
                    j = j + 1;
                    incremented = true;
                } else {
                    j = j;
                }

                String subString = s.substring(i, j);
                q.add(subString);
                if(!incremented)
                {

                    if (end && stringCheck.isParenthesis(s.charAt(j)))
                    {
                        i = j - 1;
                    }
                }
                    else if (end && Character.isDigit(s.charAt(j - 1)))
                    {
                        i = j;
                    } else {
                        i = j - 1;
                    }



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

    public boolean isLeftParenthesis(String s)
    {
        return s == "(" || s == "{" || s == "[";
    }
    public boolean isRightParenthesis(String s)
    {
        return s == ")" || s == "}" || s == "]";
    }
}