@FunctionalInterface
interface Notifier {
    void send(String message);
}

// Marker interface used to identify senders that need a second message.
interface Urgent {
}

@FunctionalInterface
interface UrgentNotifier extends Notifier, Urgent {
}

public class Broadcast {
    public static void main(String[] args) {
        Notifier emailSender = message -> System.out.println("Email: " + message);
        UrgentNotifier smsSender =
                message -> System.out.println("SMS: " + message);

        Notifier[] senders = {emailSender, smsSender};
        String message = "Server is down";

        for (Notifier sender : senders) {
            sender.send(message);

            // Urgent senders broadcast the message twice.
            if (sender instanceof Urgent) {
                sender.send("URGENT: " + message);
            }
        }
    }
}
