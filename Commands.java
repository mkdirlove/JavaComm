import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Commands {
   public static void help() {
      Display.helpMenu();
   }

   public static void inquire(LinkedList<Movie> list) {
      listAlphabetically(list);
      ListIterator<Movie> iter = list.listIterator();
      System.out.print("\nWhat movie would you like to inquire more about? ");
      String input = Utility.fetchUserInput();
      boolean inputIsValid = true;

      while (iter.hasNext()) {
         Movie movie = iter.next();
         String movieName = movie.getName();

         if (movieName.equals(input)) {
            inputIsValid = false;
            System.out.println();
            Display.inventoryMenu();
            System.out.printf("| %-44s | %-6d | %-6d |\n", movieName, movie.getHave(), movie.getWant());
            Display.lineSeparator();
         }
      }

      if (inputIsValid)
         System.out.println("Movie was not found, try again.");
   }

   public static void listAlphabetically(LinkedList<Movie> list) {
      Display.listOfMoviesMenu();
      ListIterator<Movie> iter = list.listIterator();

      while (iter.hasNext())
         System.out.printf("| %-62s |\n", iter.next().getName());

      Display.lineSeparator();
   }

   public static void add(LinkedList<Movie> list) {
      System.out.print("What is the name of the movie you'd like to add to the list? ");
      String movieName = Utility.fetchUserInput();

      if (movieName.equals("") || movieName.equals(null)) {
         System.out.print("'" + movieName + "' is not a valid input, try again.");
      } else {
         movieName = movieName.substring(0, 1).toUpperCase() + movieName.substring(1);
         System.out.print("How many '" + movieName + "' copies are currently stocked? ");
         String input = Utility.fetchUserInput();
         int wantValue = Utility.tryParse(input);

         if (wantValue == -1) {
            System.out.println("'" + input + "' is not a number, try again.");
         } else {
            list.add(new Movie(movieName, 0, wantValue));
            Utility.writeToFile(list);
            System.out.println("Movie has been succesfully added.");
         }
      }
   }

   public static void delete(LinkedList<Movie> list) {
      boolean inputIsValid = false;
      listAlphabetically(list);
      ListIterator<Movie> iter = list.listIterator();
      System.out.print("\nWhat movie would you like to delete? ");
      String input = Utility.fetchUserInput();

      while (iter.hasNext()) {
         Movie movie = iter.next();

         if (input.equals(movie.getName())) {
            inputIsValid = true;
            list.remove(movie);
            System.out.println("'" + movie.getName() + "' was succesfully removed from the list.");
            Utility.writeToFile(list);
            break;
         }
      }

      if (!inputIsValid)
         System.out.println("'" + input + "' is not a valid input, try again.");
   }

   public static void modify(LinkedList<Movie> list) {
      ListIterator<Movie> iter = list.listIterator();
      listAlphabetically(list);

      System.out.print("\nWhat movie would you like to modify? ");
      String input = Utility.fetchUserInput();
      boolean inputIsValid = false;

      while (iter.hasNext()) {
         Movie movie = iter.next();
         String movieName = movie.getName();

         if (movieName.equals(input)) {
            inputIsValid = true;
            Display.modifyMoviesMenu();
            System.out.printf("| %-44s | %-6d | %-6d |\n", movieName, movie.getHave(), movie.getWant());
            Display.lineSeparator();

            System.out.print("\nEnter the new 'stock' value for " + movieName + ": ");
            String stringStockInput = Utility.fetchUserInput();
            int newHave = Utility.tryParse(stringStockInput);
            System.out.print("Enter the new 'need'  value for " + movieName + ": ");
            String stringNeedInput = Utility.fetchUserInput();
            int newWant = Utility.tryParse(stringNeedInput);

            if (newHave == -1) {
               System.out.println("'" + stringStockInput + "' is not a valid input, try again.");
               break;
            }

            if (newWant == -1) {
               System.out.println("'" + stringNeedInput + "' is not a valid input, try again.");
               break;
            }

            movie.setHave(newHave);
            movie.setWant(newWant);
            Utility.writeToFile(list);
            System.out.println("Successfully changed value(s).");
         }
      }

      if (!inputIsValid)
         System.out.println("'" + input + "' is not a valid input, try again.");
   }

   public static void order(LinkedList<Movie> list) {
      try {
         PrintWriter writer = new PrintWriter("PurchaseOrder.txt");
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
         ListIterator<Movie> iter = list.listIterator();
         Display.writePurchaseOrderMenu(writer);
         int count = 1;

         while (iter.hasNext()) {
            Movie movie = iter.next();

            if (movie.getHave() == 0)
               writer.printf("| %3d | %-44s | %-6s | %-20s |\n", count++, movie.getName(), movie.getWant(),
                     dtf.format(LocalDateTime.now()));
         }

         Display.writeLineSeparator(writer);
         writer.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      System.out.println("You have successfully created a purchase order label named 'PurchaseOrder.txt'");
   }

   public static void rtn(LinkedList<Movie> list) {
      listAlphabetically(list);
      ListIterator<Movie> iter = list.listIterator();
      boolean movieInList = false;

      System.out.print("\nWhat movie would you like to return? ");
      String input = Utility.fetchUserInput();

      while (iter.hasNext()) {
         Movie movie = iter.next();

         if (input.equals(movie.getName())) {
            movieInList = true;
            movie.setHave(movie.getHave() + 1);
            System.out.println("Succesfully returned the movie '" + input + "'");
            break;
         }
      }

      if (!movieInList)
         System.out.println("'" + input + "' is not in the list, try again.");
   }

   public static void sell(LinkedList<Movie> list, Queue<Customer> queue) {
      listAlphabetically(list);
      ListIterator<Movie> iter = list.listIterator();
      boolean movieInList = false;

      System.out.print("\nWhat movie would you like to sell? ");
      String input = Utility.fetchUserInput();

      while (iter.hasNext()) {
         Movie movie = iter.next();
         String movieName = movie.getName();

         if (input.equals(movieName)) {
            movieInList = true;

            if (movie.getHave() == 0) {
               System.out.println("'" + movieName + "' is not in stock!\n");
               System.out.print("Would you like to add the customer to " + movieName + "'s waitlist? (Y/N) ");
               String decision = Utility.fetchUserInput();

               if (decision.equals("y") || decision.equals("Y")) {
                  Customer customer = Customer.createCustomer();

                  if (customer.getFirstName().equals("") || customer.getLastName().equals("")
                        || customer.getPhone().equals("")) {
                     System.out.println("Not valid customer data, try again.");
                     break;
                  }

                  queue.add(customer);
                  movie.addToQueue(customer);
                  System.out.println(
                        "Succesfully added '" + customer.getFirstName() + "' to the '" + movieName + "' waitlist.");
                  Utility.writeToWaitFile(queue);
                  break;
               } else if (decision.equals("n") || decision.equals("N")) {
                  break;
               } else {
                  System.out.println("'" + decision + "' is not a valid input, try again.");
                  break;
               }
            } else {
               movie.setHave(movie.getHave() - 1);
               System.out.println("Succesfully sold a copy of '" + movieName + "'");

               if (movie.getHave() == 0)
                  System.out.println(movieName + " is now out of stock!");

               Utility.writeToFile(list);
            }
         }
      }

      if (!movieInList)
         System.out.println("'" + input + "' is not a valid input, try again.");
   }

   public static void quit() {
      System.out.println("The system has quit.");
      System.exit(0);
   }
}
