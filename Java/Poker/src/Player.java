import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by KIM on 2016/9/29.
 */
public class Player {
    public String id;
    public String name;

    Map<String, Card> pCards = new HashMap<String, Card>();

    public Player(){}
    public Player(String pid, String pname){
        this.id = pid;
        this.name = pname;
    }

    public void GetCard(List<Card> Temp){
        System.out.println("玩家A 得到：" + Temp.get(0).color + Temp.get(0).number + " " +
                Temp.get(2).color + Temp.get(2).number + " " + Temp.get(4).color + Temp.get(4).number);
        System.out.println("玩家B 得到：" + Temp.get(1).color + Temp.get(1).number + " " +
                Temp.get(3).color + Temp.get(3).number + " " + Temp.get(5).color + Temp.get(5).number);
    }

    public int CompareCard(List<Card> Temp, List<Card> Original){
        int maxA = 0;
        int maxB = 0;

        for (int i = 0; i < 3; i++){
            if (Original.indexOf(Temp.get(i*2)) > maxA){
                maxA = Original.indexOf(Temp.get(i));
            }
            if(Original.indexOf(Temp.get(i*2+1)) > maxB){
                maxB = Original.indexOf(Temp.get(i*2+1));
            }
        }
        if (maxA > maxB){
            return 1;
        } else {
            return -1;
        }
    }
}
