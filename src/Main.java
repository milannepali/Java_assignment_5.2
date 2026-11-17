
class Theater {
    int availableSeats = 10;

    public synchronized void reserve(int id, int tickets) {
        if (tickets <= availableSeats) {
            availableSeats -= tickets;
            System.out.println("Customer " + id +
                    " reserved " + tickets + " tickets.");
        } else {
            System.out.println("Customer " + id +
                    " couldn't reserve " + tickets + " tickets.");
        }
    }
}

class Customer implements Runnable {
    Theater theater;
    int id;
    int tickets;

    public Customer(Theater theater, int id, int tickets) {
        this.theater = theater;
        this.id = id;
        this.tickets = tickets;
    }

    public void run() {
        theater.reserve(id, tickets);
    }
}

public class Main {
    public static void main(String[] args) {
        Theater theater = new Theater();

        for (int i = 1; i <= 15; i++) {
            int tickets = (i % 4) + 1;

            Thread customer = new Thread(
                    new Customer(theater, i, tickets)
            );

            customer.start();
        }
    }
}
