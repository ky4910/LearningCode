import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by KIM on 2016/9/28.
 */
public class MapTest {

    public Map<String ,Student> students;

    public MapTest(){
        this.students = new HashMap<String, Student>();
    }

    public void testPut(){
        int i = 0;
        Scanner sc = new Scanner(System.in);

        while(i < 3){
            System.out.println("Plz input ID: ");
            String ID = sc.next();
            Student st = students.get(ID);
            if(st == null){
                System.out.println("Plz input student name: ");
                String name = sc.next();
                Student newStudent = new Student(ID, name);
                students.put(ID, newStudent);
                System.out.println("New Student added! " + students.get(ID).name);
                i++;
            } else{
                System.out.println("This student exist!");
                continue;
            }
        }
    }

    public void testRemove() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Plz input the del ID: ");
            String ID = sc.next();
            Student st = students.get(ID);
            if(st == null){
                System.out.println("Not exist!");
                continue;
            } else {
                students.remove(ID);
                System.out.println("Remove Ok! " + st.name);
                break;
            }
        }
    }

    public void testEntrySet(){
        Set<Map.Entry<String, Student>> entrySet = students.entrySet();
        for (Map.Entry<String, Student> entry : entrySet){
            System.out.println("Key value: " + entry.getKey());
            System.out.println("The value is: " + entry.getValue().name);
        }
    }

    public void testKeySet(){
        Set<String> keySet = students.keySet();
        System.out.println("the count is " + students.size());
        for (String stuID : keySet){
            Student st = students.get(stuID);
            if(st != null){
                System.out.println("Student: " + st.name);
            }
        }
    }

    public void testModify(){
        System.out.println("Plz input modify ID: ");
        Scanner sc = new Scanner(System.in);
        while (true){
            String stuID = sc.next();
            Student st = students.get(stuID);
            if(st == null){
                System.out.println("Not exist!");
                continue;
            }
            System.out.println("Current name: " + st.name);
            System.out.println("input new name: ");
            String name = sc.next();
            Student newStudent = new Student(stuID, name);
            students.put(stuID, newStudent);
            System.out.println("Modify Success!");
            break;
        }
    }

    public void testContainsKeyOrValue(){
        System.out.println("请输入要查询的学生ID：");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        System.out.println("您输入的学生ID为：" + id + ", 在学生映射表中是否存在：" + students.containsKey(id));
        if (students.containsKey(id)){
            System.out.println("对应的学生为：" + students.get(id).name);
        }

        System.out.println("请输入要查询学生的姓名：");
        String name = sc.next();
        if (students.containsValue(new Student(null, name))){
            System.out.println("学生存在！" + name);
        }else{
            System.out.println("学生不存在！");
        }
    }

    public static void main(String[] args){
        MapTest mt = new MapTest();
        mt.testPut();
        mt.testKeySet();
//        mt.testRemove();
//        mt.testEntrySet();
//        mt.testModify();
//        mt.testEntrySet();
        mt.testContainsKeyOrValue();
    }
}
