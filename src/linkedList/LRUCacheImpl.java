package linkedList;

import java.io.*;
import java.lang.*;
import java.util.*;
public class LRUCacheImpl {

        private static List<String> inputLine(Scanner sc) {
            return Arrays.asList(sc.nextLine().split(" "));
        }

        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);
            int t = Integer.parseInt(sc.nextLine());

            while (t-- > 0) {
                int capacity = Integer.parseInt(sc.nextLine());
                LRUCache cache = new LRUCache(capacity);

                int queries = Integer.parseInt(sc.nextLine());
                while (queries-- > 0) {
                    List<String> vec = inputLine(sc);
                    if (vec.get(0).equals("PUT")) {
                        int key = Integer.parseInt(vec.get(1));
                        int value = Integer.parseInt(vec.get(2));
                        cache.put(key, value);
                    } else {
                        int key = Integer.parseInt(vec.get(1));
                        System.out.print(cache.get(key) + " ");
                    }
                }
                System.out.println();
                System.out.println("~");
            }
        }
    }

class LRUNode {
    LRUNode prev;
    LRUNode next;
    int key;
    int value;
    public LRUNode(int key, int value){
        this.key = key;
        this.value = value;
    }

}

class LRUCache {
    LRUNode head;
    LRUNode tail;
    HashMap<Integer, LRUNode> map;
    int cap = 0;

    public LRUCache(int cap) {
        this.cap = cap;
        this.head = new LRUNode(-1,-1);
        this.tail = new LRUNode(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap();
    }

    public int get(int key) {
        if(map.get(key) == null){
            return -1;
        }
        LRUNode node = map.get(key);
        //remove Node
        removeNode(node);
        //Add Node at the head
        addNode(node);

        return node.value;
    }


    public void put(int key, int value) {
        // your code here
        if(map.containsKey(key)){
            LRUNode node = map.get(key);
            //remove Node
            removeNode(node);

            node.value = value;

            // add node
            addNode(node);

        }else {
            if(map.size() >= cap){
                LRUNode nodeToDelete = tail.prev;
                map.remove(nodeToDelete.key);
                removeNode(nodeToDelete);
            }
            LRUNode node = new LRUNode(key, value);
            // add Node
            addNode(node);
            map.put(key,node);
        }

    }

    private void addNode(LRUNode node){
        LRUNode nextNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
    }

    private void removeNode(LRUNode node){
        LRUNode prevNode = node.prev;
        LRUNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}