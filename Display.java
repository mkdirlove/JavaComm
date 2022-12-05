import java.io.*;

public class Display {
   public static void commandListMenu() {
      System.out.println("------------------------------------------------------------------");
      System.out.println("|                         SYSTEM COMMANDS                        |");
      System.out.println("|----------------------------------------------------------------|");
      System.out.println("| H - (help)                                                     |");
      System.out.println("| I - (inquire)                                                  |");
      System.out.println("| L - (list)                                                     |");
      System.out.println("| A - (add)                                                      |");
      System.out.println("| D - (delete)                                                   |");
      System.out.println("| M - (modify)                                                   |");
      System.out.println("| O - (order)                                                    |");
      System.out.println("| R - (return)                                                   |");
      System.out.println("| S - (sell)                                                     |");
      System.out.println("| Q - (quit)                                                     |");
      System.out.println("------------------------------------------------------------------");
   }

   public static void helpMenu() {
      System.out.println("------------------------------------------------------------------");
      System.out.println("|                               HELP                             |");
      System.out.println("|----------------------------------------------------------------|");
      System.out.println("| (H)elp    - brings up current menu                             |");
      System.out.println("| (I)nquire - lists inventory for a movie                        |");
      System.out.println("| (L)ist    - lists all movies in the list                       |");
      System.out.println("| (A)dd     - adds a movie to the list                           |");
      System.out.println("| (D)elete  - deletes a movie from the list                      |");
      System.out.println("| (M)odify  - modify have and want values for a movie            |");
      System.out.println("| (O)rder   - creates order label based on the number of movies  |\n" +
            "|             needed                                             |");
      System.out.println("| (R)eturn  - return a movie                                     |");
      System.out.println("| (S)ell    - sells a movie to a customer by automatically       |\n" +
            "|             decreasing the 'have' value of the movie           |");
      System.out.println("| (Q)uit    - quits program                                      |");
      System.out.println("------------------------------------------------------------------");
   }

   public static void inventoryMenu() {
      System.out.println("------------------------------------------------------------------");
      System.out.println("|                            INQUIRE                             |");
      System.out.println("------------------------------------------------------------------");
      System.out.printf("| %-44s | %-6s | %-6s |\n", "Name", "Stock", "Need");
      System.out.println("|----------------------------------------------|--------|--------|");
   }

   public static void listOfMoviesMenu() {
      System.out.println("------------------------------------------------------------------");
      System.out.println("|                         LIST OF MOVIES                         |");
      System.out.println("|----------------------------------------------------------------|");
   }

   public static void lineSeparator() {
      System.out.println("------------------------------------------------------------------");
   }

   public static void modifyMoviesMenu() {
      System.out.println("\n------------------------------------------------------------------");
      System.out.println("|                             MODIFY                             |");
      System.out.println("------------------------------------------------------------------");
      System.out.printf("| %-44s | %-6s | %-6s |\n", "Name", "Stock", "Need");
      System.out.println("------------------------------------------------------------------");
   }

   public static void writePurchaseOrderMenu(PrintWriter writer) {
      writer.println("--------------------------------------------------------------------------------------");
      writer.println("|                                   Purchase  Order                                  |");
      writer.println("|------------------------------------------------------------------------------------|");
      writer.println("| #   | Name                                         | Need   | Time printed         |");
      writer.println("|-----|----------------------------------------------|--------|----------------------|");
   }

   public static void writeLineSeparator(PrintWriter writer) {
      writer.println("--------------------------------------------------------------------------------------");
   }
}
