import java.util.*;

/**
 * Created by KIM on 2016/9/27.
 */
public class ListTest {
    public List<Course> coursesToSelect;

    public Student student;

    public ListTest(){
        this.coursesToSelect = new ArrayList();
    }

    public void testAdd(){
        Course cr1 = new Course("1", "数据结构");
        coursesToSelect.add(cr1);
        Course temp = (Course) coursesToSelect.get(0);
        System.out.println("添加了课程：" + temp.id + ": " + temp.name);

        Course cr2 = new Course("2", "C语言");
        coursesToSelect.add(1, cr2);
        Course temp2 = (Course)coursesToSelect.get(1);
        System.out.println("添加了课程：" + temp2.id + ": " + temp2.name);

        Course[] course = {new Course("3", "离散数学"), new Course("4", "汇编语言")};
        coursesToSelect.addAll(Arrays.asList(course));
        Course temp3 = (Course)coursesToSelect.get(2);
        Course temp4 = (Course)coursesToSelect.get(3);

        System.out.println("添加了两门课程：" + "\n\t" + temp3.id + ": " + temp3.name + "\n\t"
                + temp4.id + ": " + temp4.name);

        Course[] course2 = {new Course("5", "网络原理"), new Course("6", "大学英语")};
        coursesToSelect.addAll(2, Arrays.asList(course2));
        Course temp5 = (Course)coursesToSelect.get(2);
        Course temp6 = (Course)coursesToSelect.get(3);
        System.out.println("添加了两门课程：" + "\n\t" + temp5.id + ": " + temp5.name + "\n\t"
                + temp6.id + ": " + temp6.name);


        System.out.println("\n\n");
//        Course cr3 = new Course("3", "操作系统");
//        coursesToSelect.add(0, cr3);
//        Course temp3 = (Course)coursesToSelect.get(0);
//        System.out.println("添加了课程：" + temp3.id + ": " + temp3.name);
    }

    public void testGet(){
        int size = coursesToSelect.size();
        System.out.println("有如下课程：");
        for(int i = 0; i < size; i++){
            Course cr =  (Course) coursesToSelect.get(i);
            System.out.println("课程：" + cr.id + ": " + cr.name);
        }
    }

    public void testIterator(){
        Iterator it = coursesToSelect.iterator();
        System.out.println("有如下课程(Iterator)：");
        while (it.hasNext()){
            Course cr = (Course)it.next();
            System.out.println("课程：" + cr.id + ": " + cr.name);
        }
    }

    public void testListContains(){

        Course course = (Course) coursesToSelect.get(5);
        System.out.println("\n取得课程：" + course.name);
        System.out.println("备选课程中是否包含课程：" + course.name + ", " + coursesToSelect.contains(course));

//        Course course2 = new Course(course.id, course.name);
//        System.out.println("新创建课程： " + course2.name);
//        System.out.println("备选课程中是否包含课程：" + course2.name + ", " + coursesToSelect.contains(course2));

        if (coursesToSelect.contains(course)){
            System.out.println("课程：" + course.name + "的索引位置为：" +
                    coursesToSelect.indexOf(course));
        }
    }

    public void createStudentAndSelectCourse(){
        student = new Student("1", "小明");
        System.out.println("欢迎学生：" + student.name + "选课！");
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++){
            System.out.println("请输入课程ID：");
            String courseId = sc.next();
            for (Course cr : coursesToSelect){
                if(cr.id.equals(courseId)){
                    student.courses.add(cr);
                }
            }
        }
    }

    public void testSetContains(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生已选的课程名称：");
        String name = sc.next();
        Course course2 = new Course();
        course2.name = name;
        System.out.println("新创建课程： " + course2.name);
        System.out.println("备选课程中是否包含课程：" + course2.name + ", " + student.courses.contains(course2));
    }

    public void testForEach(){
        for (int i = 0; i < 6; i++){
            Course tmp = coursesToSelect.get(i);
            System.out.print(tmp.id + " " + tmp.name + "  ");
            if((i+1)%2 == 0){
                System.out.println();
            }
        }
    }

    public static void main(String[] args){
        ListTest lt = new ListTest();
        lt.testAdd();
        lt.testForEach();
        lt.testListContains();
//        lt.testGet();
//        lt.testIterator();

//        lt.createStudentAndSelectCourse();
//        lt.testSetContains();
    }
}
