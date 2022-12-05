public class Customer {
        private String firstName;
        private String lastName;
        private String phone;

        public Customer(String firstName, String lastName, String phone) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.phone = phone;
        }

        public String getFirstName() {
                return firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String toString() {
                return firstName + "|" + lastName + "|" + phone + "|";
        }

        public static Customer createCustomer() {
                System.out.print("Enter the customer's first name: ");
                String firstName = Utility.fetchUserInput();
                System.out.print("Enter the customer's last name: ");
                String lastName = Utility.fetchUserInput();
                System.out.print("Enter customer's phone number: ");
                String phone = Utility.fetchUserInput();

                return new Customer(firstName, lastName, phone);
        }
}
