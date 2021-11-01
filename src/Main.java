import java.util.*;
public class Main {

    public static void main(String[] args)
    {

         StringChecking c = new StringChecking();
         ShuntingYard s = new ShuntingYard();
         ReversePolish rev = new ReversePolish();

         String ss = "s(5+3)";

         if (c.checkForAll(ss))
         {
             Queue<String> l = s.runShuntingYard(ss);
             Queue<String> k = s.convertString(ss);
             double res = rev.runAlgorithm(l);
            System.out.println(res);

         }
    }
}


