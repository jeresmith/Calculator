import java.util.*;

public class Main {

    public static void main(String[] args) {

        gameRules();
        game();

    }

    public static void gameRules()
    {
        System.out.println("GAME RULES");
        System.out.println("-----------");
        System.out.println("Enter e to exit");
        System.out.println("-----------");
        System.out.println("No spaces");
        System.out.println("-----------");
        System.out.println("Unary and trig functions require parenthesis");
        System.out.println("-----------");
        System.out.println();
    }
    
    public static void game()
    {
        Scanner keyboard = new Scanner(System.in);

        StringChecking c = new StringChecking();
        ShuntingYard s = new ShuntingYard();
        ReversePolish rev = new ReversePolish();
        boolean exit = false;

        while (!exit)
        {

            String input = "";
            System.out.println("Enter a calculation");
            System.out.print(">> ");
            input = keyboard.nextLine();
            System.out.println();
            if (input.equals("e"))
            {
                exit = true;
                continue;
            }

            boolean valid = c.checkForAll(input);

            if (!valid)
            {
                System.out.println("Error");
            } else {
                Queue<String> l = s.runShuntingYard(input);
                double res = rev.runAlgorithm(l);
                System.out.println("Answer: " + res);

            }
            System.out.println();

        }

        System.out.println("Program ended");

    }

}


