import java.util.LinkedList;
import java.util.List;

class Observer {
    String name;

    public Observer(String name) {
        this.name = name;
    }

    public void update(String msg) {
        System.out.println(name + " : " + msg);
    }

}

class Subject {
    private List<Observer> observers = new LinkedList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notify(String msg) {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer o1 = new Observer("o1");
        Observer o2 = new Observer("o2");
        Observer o3 = new Observer("o3");

        subject.attach(o1);
        subject.attach(o2);
        subject.attach(o3);

        System.out.println("============");
        subject.notify("alive");

        System.out.println("\n============");
        subject.detach(o2);
        subject.notify("alive");
    }
}
