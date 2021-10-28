import java.util.*;
public class Main {

    public static void main(String[] args)
    {

         StringChecking c = new StringChecking();
         ShuntingYard s = new ShuntingYard();

         String ss = "s(3)*2";
         if(c.checkForAll(ss)){
             Queue<String> l = s.runShuntingYard(ss);
             Queue<String> k = s.convertString(ss);

             System.out.println(k);
         }
    }
}


