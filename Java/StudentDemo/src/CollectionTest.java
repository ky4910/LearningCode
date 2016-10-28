import java.util.*;

/**
 * Created by KIM on 2016/9/28.
 */
public class CollectionTest {
    public void testSort1(){
        List<Integer> integerList = new ArrayList<Integer>();
        Random random = new Random();

        Integer k;
        for (int i = 0; i < 10; i++){
            do {
                k = random.nextInt(100);
            }while (integerList.contains(k));
            integerList.add(k);
            System.out.println("成功添加整数：" + k);
        }
        System.out.println("------------------------排序前------------------------");
        for (Integer integer : integerList){
            System.out.print(integer + " ");
        }
        System.out.println();

        Collections.sort(integerList);
        System.out.println("------------------------排序后------------------------");
        for (Integer integer : integerList){
            System.out.print(integer + " ");
        }
    }

    public void testSort2(){
        List<String> stringList = new ArrayList<String>();
        stringList.add("Microsoft");
        stringList.add("Google");
        stringList.add("Lenovo");

        System.out.println("------------------------排序前------------------------");
        for (String string: stringList){
            System.out.print(string + " ");
        }
        System.out.println();

        Collections.sort(stringList);
        System.out.println("------------------------排序后------------------------");
        for (String string: stringList){
            System.out.print(string + " ");
        }
        System.out.println();
    }

    public void testSort3(){
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student(3 + "", "Lily"));
        studentList.add(new Student(1 + "", "Kimmy"));
        studentList.add(new Student(2 + "", "Tinna"));
        studentList.add(new Student(10 + "", "Jack"));

        System.out.println("------------------------排序前------------------------");
        for (Student student: studentList){
            System.out.print(student.name + " ");
        }
        System.out.println();

        Collections.sort(studentList);
        System.out.println("------------------------排序后------------------------");
        for (Student student: studentList){
            System.out.print(student.name + " ");
        }
        System.out.println();

        Collections.sort(studentList, new StudentComparator());
        System.out.println("------------------------按照姓名排序后------------------------");
        for (Student student: studentList){
            System.out.print(student.name + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        CollectionTest ct = new CollectionTest();
//        ct.testSort1();
//        ct.testSort2();
        ct.testSort3();
        System.out.println(Integer.MAX_VALUE);
    }
}
