import java.util.HashSet;
import java.util.Set;

/**
 * Created by KIM on 2016/9/27.
 */
public class Student implements Comparable<Student>{
    public String id;
    public String name;

    public Set courses;

    public Student(String id, String name){
        this.id = id;
        this.name = name;
        this.courses = new HashSet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return name != null ? name.equals(student.name) : student.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public int compareTo(Student o){

        return this.id.compareTo(o.id);
    }
}
