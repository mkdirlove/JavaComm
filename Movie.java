import java.util.*;

public class Movie implements Comparable<Movie> {
        private String name;
        private int have, want;
        private Queue<Customer> queue = new LinkedList<Customer>();

        public Movie(String name, int have, int want) {
                this.name = name;
                this.have = have;
                this.want = want;
        }

        public void addToQueue(Customer c) {
                this.queue.add(c);
        }

        public void setQueue(Queue<Customer> queue) {
                this.queue = queue;
        }

        public Queue<Customer> getQueue() {
                return this.queue;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public int getHave() {
                return have;
        }

        public void setHave(int have) {
                this.have = have;
        }

        public int getWant() {
                return want;
        }

        public void setWant(int want) {
                this.want = want;
        }

        public int compareTo(Movie other) {
                return name.compareTo(other.name);
        }

        public String toString() {
                return name + "|" + have + "|" + want + "|";
        }
}
