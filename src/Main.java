import java.util.*;
public class Main {

    public static void main(String[] args)
    {

         StringChecking c = new StringChecking();
         ShuntingYard s = new ShuntingYard();

         String ss = "1+1+2/3";
         Queue<String> q = s.convertString(ss);
         Queue<String> l = s.runShuntingYard(ss);
         System.out.println(q);

    }
}
