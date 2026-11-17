
import java.util.ArrayList;

class SafeList {
    private ArrayList<String> names = new ArrayList<>();

    public synchronized void add(String name) {
        names.add(name);
    }

    public synchronized int size() {
        return names.size();
    }

    public synchronized void remove(String name) {
        names.remove(name);
    }
}

class ListWorker implements Runnable {
    SafeList list;

    public ListWorker(SafeList list) {
        this.list = list;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            list.add("Name" + i);
        }
    }
}

public class Task2 {
    public static void main(String[] args) {
        SafeList list = new SafeList();

        Thread t1 = new Thread(new ListWorker(list));
        Thread t2 = new Thread(new ListWorker(list));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("List size: " + list.size());

        list.remove("Name0");

        System.out.println("Size after removal: " + list.size());
    }
}
