Let's design a messaging service like WhatsApp with a detailed low-level design (LLD). This service will provide direct messaging, end-to-end encryption, user status, group messaging, and more.

### Key Components

1. **User Authentication**:
   - Phone number-based authentication (e.g., OTP).
   - Token-based session management (e.g., OAuth).

2. **Messaging Protocol**:
   - Real-time messaging using WebSockets or MQTT for low-latency communication.
   - Asynchronous message delivery.

3. **Database**:
   - NoSQL databases (e.g., Cassandra, MongoDB) for fast read/write operations.
   - Storing messages, user data, and metadata.

4. **Message Queue**:
   - Queueing messages for delivery (e.g., Kafka or RabbitMQ).
   - Handling undelivered messages for offline users.

5. **End-to-End Encryption**:
   - Ensuring secure communication between users.
   - Implementing encryption protocols like AES or Signal Protocol.

6. **Media Storage**:
   - Cloud storage for large media files (e.g., AWS S3, Google Cloud Storage).
   - Sharing file URLs rather than embedding files directly in messages.

7. **Push Notifications**:
   - Using services like Firebase Cloud Messaging to notify users of new messages when they are not actively using the app.

8. **Group Chats**:
   - Managing group messages via a publish-subscribe model for scalability.
   - Handling message delivery to multiple recipients efficiently.

### Class Diagram

```plaintext
+----------------+       +----------------+       +----------------+
| MessagingApp   |<----->| User           |<----->| Message        |
+----------------+       +----------------+       +----------------+
| - users        |       | - id           |       | - id           |
| - messages     |       | - phoneNumber  |       | - senderId     |
| + sendMessage()|       | - status       |       | - receiverId   |
| + receiveMessage()     | + authenticate()|       | - content      |
| + createGroup()        | + updateStatus()|       | - timestamp    |
+----------------+       +----------------+       +----------------+
        |
        |
        v
+----------------+
| Encryption     |
+----------------+
| + encrypt()    |
| + decrypt()    |
+----------------+
        |
        |
        v
+----------------+
| Notification   |
+----------------+
| + sendPushNotification()|
+----------------+
```

### Detailed Low-Level Design

#### MessagingApp Class

```java
import java.util.HashMap;
import java.util.Map;

public class MessagingApp {
    private Map<String, User> users;
    private Map<String, Message> messages;
    private Encryption encryption;
    private Notification notification;

    public MessagingApp() {
        users = new HashMap<>();
        messages = new HashMap<>();
        encryption = new Encryption();
        notification = new Notification();
    }

    public void sendMessage(String senderId, String receiverId, String content) {
        String encryptedContent = encryption.encrypt(content);
        Message message = new Message(senderId, receiverId, encryptedContent);
        messages.put(message.getId(), message);
        notification.sendPushNotification(receiverId, "New message from " + senderId);
    }

    public String receiveMessage(String messageId) {
        Message message = messages.get(messageId);
        return encryption.decrypt(message.getContent());
    }

    public void createGroup(String groupId, List<String> userIds) {
        // Logic to create a group chat
    }
}
```

#### User Class

```java
public class User {
    private String id;
    private String phoneNumber;
    private String status;

    public User(String id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.status = "offline";
    }

    public void authenticate(String otp) {
        // Logic for OTP authentication
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }
}
```

#### Message Class

```java
public class Message {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private long timestamp;

    public Message(String senderId, String receiverId, String content) {
        this.id = generateId();
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    private String generateId() {
        // Logic to generate unique message ID
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
```

#### Encryption Class

```java
public class Encryption {
    public String encrypt(String data) {
        // Logic to encrypt data
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public String decrypt(String data) {
        // Logic to decrypt data
        return new String(Base64.getDecoder().decode(data));
    }
}
```

#### Notification Class

```java
public class Notification {
    public void sendPushNotification(String userId, String message) {
        // Logic to send push notification
        System.out.println("Push notification sent to " + userId + ": " + message);
    }
}
```

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        MessagingApp app = new MessagingApp();
        User user1 = new User("1", "1234567890");
        User user2 = new User("2", "0987654321");

        app.sendMessage(user1.getId(), user2.getId(), "Hello, how are you?");
        String receivedMessage = app.receiveMessage("messageId");
        System.out.println("Received message: " + receivedMessage);
    }
}
```

### Edge Cases and Solutions

1. **Offline Users**: Store undelivered messages in a message queue and deliver them when the user comes online.
2. **Message Expiry**: Implement logic to delete messages after a certain period.
3. **Scalability**: Use distributed databases and message queues to handle high traffic and large volumes of data.
4. **Security**: Ensure end-to-end encryption for all messages and secure storage for user data.

This design covers the basic functionality of a messaging service like WhatsApp, including user authentication, real-time messaging, encryption, and notifications. You can expand this design by adding features like media sharing, voice/video calls, and more. If you have any specific questions or need further details, feel free to ask!