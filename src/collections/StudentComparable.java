package collections;

import java.util.ArrayList;
import java.util.Collections;

public class StudentComparable {


    public static void main(String[] args) {
        Student student = new Student(26,"abc","B123");
        Student student1 = new Student(23,"cbf","B456");
        Student student2 = new Student(29,"bgf","B789");
        ArrayList<Student> studentList =  new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        Collections.sort(studentList);
        studentList.forEach(c -> System.out.println(c.getName() + " " + c.getAge() + " " + c.getRollNo()));

    }
}
class Student implements Comparable<Student>{
    private int age;
    private String name;
    private String rollNo;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public Student(int age, String name, String rollNo) {
        this.age = age;
        this.name = name;
        this.rollNo = rollNo;
    }

    @Override
    public int compareTo(Student o) {
        if(this.age == o.age){
            return 0;
        }
        if(this.age > o.age){
            return 1;
        }else {
            return -1;
        }
    }
}