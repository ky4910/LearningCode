import java.util.*;

public class Main {
    public static void main(String[] args){
        int step = 0;
        List<Card> origCards = new ArrayList<Card>();
        List<Card> myCards = new ArrayList<Card>();
        Scanner sc = new Scanner(System.in);

        Card test = new Card();
        origCards =  test.CreateCards();
        test.showCards();
        myCards =  test.shuffleCards();

        Player User = new Player();

        System.out.println("输入玩家A ID：");
        String id_A = sc.next();
        System.out.println("输入玩家A 姓名：");
        String name_A = sc.next();
        Player playerA = new Player(id_A, name_A);

        System.out.println("输入玩家B ID：");
        String id_B = sc.next();
        System.out.println("输入玩家B 姓名：");
        String name_B = sc.next();

        Player playerB = new Player(id_B, name_B);

        System.out.println("玩家已创建!");
        System.out.println("玩家A  ID: " + playerA.id + " Name: " + playerA.name);
        System.out.println("玩家B  ID: " + playerB.id + " Name: " + playerB.name);

        step = sc.nextInt();

        step = User.CompareCard(myCards, origCards);
        if (step == 1){
            System.out.println("玩家A " + playerA.name + " 获胜!");
        }else {
            System.out.println("玩家B " + playerB.name + " 获胜!");
        }

        User.GetCard(myCards);
        test.ShowShuffled();
    }
}
