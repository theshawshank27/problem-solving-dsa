package stack;

// Implement stack using arrays
public class StackImplUsingArrays {

    public static void main(String[] args) {
        Stack stack = new Stack(10);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.pop() + " popped from top of stack");

        System.out.println(stack.peek() + " is top of the stack now");
        System.out.println("Elements present in the stack");
        while (!stack.isEmpty()){
            System.out.println(stack.peek() + " ");
            stack.pop();
        }
    }

}
class Stack {
    int stackSize;
    int[] stackArr;
    int top;

    public Stack(int capacity) {
        this.stackSize = capacity;
        this.stackArr = new int[capacity];
        top =-1;
    }
    public boolean push(int x){
        if(top > stackSize -1){
            System.out.println("Stack is full: Stack overflow");
            return false;
        }
        stackArr[++top] = x;
        return true;
    }

    public int pop(){
        if(top < 0){
            System.out.println("Stack is empty : Stack underflow");
            return 0;
        }
        return stackArr[top--];
    }

    public int peek(){
        if(top < 0){
            System.out.println("Stack is empty");
            return 0;
        }
        return stackArr[top];
    }
    public boolean isEmpty(){
        return top < 0;
    }

}

