import java.util.*;
import java.util.concurrent.*;

interface Subscriber {
    void onMessage(String topic, Object message);

    String getName();
}

class UserSubscriber implements Subscriber {
    private String name;

    public UserSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onMessage(String topic, Object message) {
        System.out.println("【" + name + "】收到来自 " + topic + " 的消息: " + message);
    }

    @Override
    public String getName() {
        return name;
    }
}

public class PubSub {
    private final Map<String, Set<Subscriber>> topics = new ConcurrentHashMap<>();

    public void subscribe(String topic, Subscriber subscriber) {
        if (!topics.containsKey(topic)) {
            topics.put(topic, new CopyOnWriteArraySet<>());
        }

        topics.get(topic).add(subscriber);
        System.out.println(subscriber.getName() + " subscribed: " + topic);
    }

    public void unsubscribe(String topic, Subscriber subscriber) {
        Set<Subscriber> subscribers = topics.get(topic);

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            System.out.println("not exist");
        }
    }

    public void publish(String topic, String message) {
        Set<Subscriber> subscribers = topics.get(topic);

        if (subscribers == null || subscribers.isEmpty()) {
            System.out.println("主题 " + topic + " 暂无订阅者");
            return;
        }

        for (Subscriber sub : subscribers) {
            sub.onMessage(topic, message);
        }
    }

    public static void main(String[] args) {
        PubSub bus = new PubSub();

        Subscriber sub1 = new UserSubscriber("sub1");
        Subscriber sub2 = new UserSubscriber("sub2");

        bus.subscribe("topic1", sub1);
        bus.subscribe("topic2", sub2);
        bus.subscribe("topic2", sub1);

        bus.publish("topic1", "111");
        bus.publish("topic2", "222");

        bus.unsubscribe("topic1", sub1);
    }
}
