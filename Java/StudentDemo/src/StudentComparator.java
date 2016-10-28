import java.util.Comparator;

/**
 * Created by KIM on 2016/9/28.
 */
public class StudentComparator implements Comparator<Student>{
    public int compare(Student o1, Student o2){
        return o1.name.compareTo(o2.name);
    }
}
