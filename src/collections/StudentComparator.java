package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentComparator {
    public static void main(String[] args) {

        Student student = new Student(26,"abc","B123");
        Student student1 = new Student(23,"cbf","B456");
        Student student2 = new Student(29,"bgf","B789");
        ArrayList<Student> studentList =  new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        Collections.sort(studentList,new StudentNameComparator());
        studentList.forEach(c -> System.out.println(c.getName() + " " + c.getAge() + " " + c.getRollNo()));
    }
}

class StudentNameComparator implements Comparator<Student>{

    @Override
    public int compare(Student s1, Student s2) {
      return  s1.getName().compareTo(s2.getName());
    }
}
