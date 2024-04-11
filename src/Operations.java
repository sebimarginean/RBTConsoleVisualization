
import java.util.Scanner;

public class Operations {

    public static void showMenu()
    {
        System.out.println();
        System.out.println( " 1. Add nodes");
        System.out.println( " 2. Delete node");
        System.out.println( " 3. Get minimum node");
        System.out.println( " 4. Get maximum node");
        System.out.println( " 5. Get successor of a node");
        System.out.println( " 6. Get predecessor of a node");
        System.out.println( " 7. Show tree");
        System.out.println( " 8. Show inorder traversal");
        System.out.println( " 9. Show black-height of the tree");
        System.out.println( "10. Show the maximum key of a black node of the tree");
        System.out.println( "11. Show the maximum key of a red node of the tree");
        System.out.println( "12. Show tree depth");
        System.out.println( "13. Exit");
        System.out.print( "Enter your choice (1-13): ");
    }

    public static int getOperation()
    {
        int option = 0;
        while (true)
        {
            showMenu();
            Scanner read = new Scanner(System.in);
            option = Integer.parseInt(read.next());

            if (option < 1 || option > 13)
            {
                System.out.println("Enter a valid option number!");
            }
            else
            {
                System.out.println("Execute " + option + " .." + "\n");
                break;
            }
        }
        return option;
    }

    public static int getNodeKey()
    {
        System.out.print("Type in the key of the node: ");
        int key;
        Scanner read = new Scanner(System.in);
        key = Integer.parseInt(read.next());
        return key;
    }
}
