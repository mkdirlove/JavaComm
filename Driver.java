import java.util.*;

public class Driver {
        public static void main(String[] args) {
                LinkedList<Movie> list = new LinkedList<Movie>();
                Queue<Customer> queue = new LinkedList<Customer>();
                Utility.restoreList(list);
                Utility.restoreWaitQueue(queue);
                Display.commandListMenu();

                while (true) {
                        System.out.print("\nEnter a command: ");
                        String command = Utility.fetchUserInput();
                        Utility.checkCommand(command, list, queue);
                }
        }
}
