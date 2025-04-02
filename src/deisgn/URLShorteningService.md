
Let's design a URL shortening service with a detailed low-level design (LLD). This service will convert long URLs into shorter ones and ensure that each short URL maps back to the original URL when accessed.

Key Components
User Interface (UI): Allows users to input long URLs and receive the shortened version.
API Layer: Handles requests to shorten or retrieve URLs.
Database: Stores mappings between short and long URLs.
Hashing Algorithm: Converts long URLs into unique, fixed-length shortened URLs using Base62 encoding.
Redirect Service: Retrieves the original URL from the database and redirects the user.
Class Diagram
+----------------+       +----------------+       +----------------+
| URLShortener   |<----->| URLMapping     |<----->| Database       |
+----------------+       +----------------+       +----------------+
| - database     |       | - longUrl      |       | - mappings     |
| + shortenUrl() |       | - shortUrl     |       | + saveMapping()|
| + redirectUrl()|       +----------------+       | + getLongUrl() |
+----------------+                               +----------------+
        |
        |
        v
+----------------+
| Base62Encoder  |
+----------------+
| + encode()     |
| + decode()     |
+----------------+
Detailed Low-Level Design
URLShortener Class
import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private Database database;
    private Base62Encoder encoder;

    public URLShortener() {
        database = new Database();
        encoder = new Base62Encoder();
    }

    public String shortenUrl(String longUrl) {
        String shortUrl = encoder.encode(longUrl.hashCode());
        database.saveMapping(shortUrl, longUrl);
        return shortUrl;
    }

    public String redirectUrl(String shortUrl) {
        return database.getLongUrl(shortUrl);
    }
}
URLMapping Class
public class URLMapping {
    private String longUrl;
    private String shortUrl;

    public URLMapping(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
Database Class
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Map<String, String> mappings;

    public Database() {
        mappings = new HashMap<>();
    }

    public void saveMapping(String shortUrl, String longUrl) {
        mappings.put(shortUrl, longUrl);
    }

    public String getLongUrl(String shortUrl) {
        return mappings.get(shortUrl);
    }
}
Base62Encoder Class
public class Base62Encoder {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String encode(int input) {
        StringBuilder encoded = new StringBuilder();
        while (input > 0) {
            encoded.append(BASE62.charAt(input % 62));
            input /= 62;
        }
        return encoded.reverse().toString();
    }

    public int decode(String input) {
        int decoded = 0;
        for (char c : input.toCharArray()) {
            decoded = decoded * 62 + BASE62.indexOf(c);
        }
        return decoded;
    }
}
Example Usage
public class Main {
    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        String longUrl = "https://www.example.com/some/long/url";
        String shortUrl = urlShortener.shortenUrl(longUrl);
        System.out.println("Short URL: " + shortUrl);

        String originalUrl = urlShortener.redirectUrl(shortUrl);
        System.out.println("Original URL: " + originalUrl);
    }
}
Edge Cases and Solutions
Collision Handling: Ensure that the generated short URL is unique by checking the database before saving.
Invalid URL: Validate the input URL format before processing.
Expired URLs: Implement expiration logic to handle URLs that should no longer be valid.
High Availability: Use distributed databases and caching to ensure the service is highly available and scalable.
This design covers the basic functionality of a URL shortening service, including URL shortening, redirection, and encoding/decoding. You can expand this design by adding features like user authentication, analytics, and custom aliases. If you have any specific questions or need further details, feel free to ask!