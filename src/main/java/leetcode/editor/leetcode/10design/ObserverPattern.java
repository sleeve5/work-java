import java.util.LinkedList;
import java.util.List;

interface Observer {
    void update(String msg);
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

class Observer1 implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("Observer1: " + msg);
    }
}

class Observer2 implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("Observer2: " + msg);
    }
}

class Observer3 implements Observer {
    @Override
    public void update(String msg) {
        System.out.println("Observer3: " + msg);
    }
}

public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer o1 = new Observer1();
        Observer o2 = new Observer2();
        Observer o3 = new Observer3();

        subject.attach(o1);
        subject.attach(o2);
        subject.attach(o3);

        System.out.println("第一次广播消息：");
        subject.notify("服务状态已更新！");

        System.out.println("\n移除 Observer2 后广播：");
        subject.detach(o2);
        subject.notify("配置已修改！");
    }
}
