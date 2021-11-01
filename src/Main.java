import java.util.*;
public class Main {

    public static void main(String[] args)
    {

         StringChecking c = new StringChecking();
         ShuntingYard s = new ShuntingYard();
         ReversePolish rev = new ReversePolish();

         String ss = "1+2^2*3/2+s(3)";

         if (c.checkForAll(ss))
         {
             Queue<String> l = s.runShuntingYard(ss);
             Queue<String> k = s.convertString(ss);
             double res = rev.runAlgorithm(l);
            System.out.println(res);

         }
    }
}


