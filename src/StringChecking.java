import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class StringChecking
{

   public final Set<Character> s;
    public final Set<Character> operators;

   public StringChecking()
   {
       s = new HashSet<Character>();
       s.add('1');
       s.add('2');
       s.add('3');
       s.add('4');
       s.add('5');
       s.add('6');
       s.add('7');
       s.add('8');
       s.add('9');
       s.add('.');
       s.add('/');
       s.add('+');
       s.add('-');
       s.add('*');
       s.add('(');
       s.add(')');
       s.add('{');
       s.add('}');
       s.add('[');
       s.add(']');
       s.add('^');

       operators = new HashSet<>();
       operators.add('+');
       operators.add('/');
       operators.add('*');
       operators.add('^');
   }
   public void checkForAll(String cur){
       boolean check = true;
       if (!checkValid(cur))
       {
           System.out.println("checkValid Failed");
           check = false;
           return;
       }
       if (!balanacedParenthesis(cur))
       {
           System.out.println("balancedParenthesis Failed");
           check = false;
       }
       if (!checkPeriods(cur))
       {
           System.out.println("checkPeriods Failed");
           check = false;
       }
       if (!validOperator(cur))
       {
           System.out.println("validOperator Failed");
           check = false;
       }

       if(check)
       {
           System.out.println("All TESTS PASSED!");
       }

   }

    public boolean checkValid(String cur)
    {
       for (int i = 0; i < cur.length(); i++)
       {
           if(!s.contains(cur.charAt(i))) {
               return false;
           }
       }
        return true;
    }

    public boolean balanacedParenthesis (String cur)
    {
       Stack<Character> stack = new Stack<Character>();

       for (int i = 0; i < cur.length(); i++)
       {
           char current = cur.charAt(i);

           if (isLeftParenthesis(current)) {
               stack.add(current);

           } else if (isRightParenthesis(current)) {
               if(stack.isEmpty()) {
                   return false;
               }
               if(!validateParenthesis(current, stack.pop())) {
                   return false;
               }
           }
       }
       return stack.isEmpty();
    }

    public boolean isParenthesis(char c)
    {
        return c == '(' || c == '{' || c == '[' || c == ')' || c == '}' || c == ']';
    }
    private boolean isLeftParenthesis (char c) {
        return c == '(' || c == '{' || c == '[';
    }

    private boolean isRightParenthesis (char c) {
        return c == ')' || c == '}' || c == ']';
    }

    private boolean validateParenthesis(char c, char k)
    {
       if(c == '}' && k == '{') {
           return true;
       }
        else if(c == ']' && k == '[') {
            return true;
        }
        else return c == ')' && k == '(';
    }

    public boolean checkPeriods(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '.')
            {
                if (s.length() == 1)
                {
                    return false;
                }
                if((i - 1) < 0)
                {
                    if (!Character.isDigit(s.charAt(i + 1)))
                    {
                        return false;
                    }
                    continue;
                }
                if((i + 1) >= s.length())
                {
                    if (!Character.isDigit(s.charAt(i - 1)))
                    {
                        return false;
                    }
                    continue;
                }

                if (s.charAt(i - 1) == '.' || s.charAt(i + 1) == '.')
                {
                    return false;
                }
                else if (!Character.isDigit(s.charAt(i - 1)) && !Character.isDigit(s.charAt(i + 1)))
                {
                    return false;
                }

            }
        }

        return true;
    }

    public boolean validOperator(String cur)
    {
        for (int i = 0; i < cur.length(); i++)
        {
            char c = cur.charAt(i);

            if(operators.contains(c)) {
                if(i == 0 || i == cur.length() - 1)
                {
                    return false;
                }

                else if (!Character.isDigit(cur.charAt(i - 1)) || (!Character.isDigit(cur.charAt(i + 1))
                        && cur.charAt(i + 1) != '-') ){

                    return false;
                }
            }
            else if (c == '-')
            {
                if(i == cur.length() - 1){
                    return false;
                } else if (!Character.isDigit(cur.charAt(i + 1)) || !isLeftParenthesis(cur.charAt(i + 1))){
                    return false;
                }
            }
        }
        return true;
    }


}
