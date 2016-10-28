import java.util.*;

/**
 * Created by KIM on 2016/9/29.
 */
public class Card {
    String color;
    String number;

    String[] colors = new String[]{"黑桃", "红桃", "梅花", "方片"};
    String[] numbers = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    List<Card> cards = new ArrayList<Card>();
    List<Card> washCards = new ArrayList<Card>();
    Map<String, String> cMap = new HashMap<String, String>();

    public Card(){}

    public Card(String c, String n){
        this.color = c;
        this.number = n;
    }

    public List<Card> CreateCards(){
        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                Card tmp = new Card(colors[i], numbers[j]);
                cards.add(index, tmp);
                index++;
            }
        }
        return cards;
    }

    public void showCards(){
        System.out.println("扑克牌生成!");
        for (int i = 0; i < 52; i++){
            System.out.print(cards.get(i).color + cards.get(i).number + " ");
            if((i+1)%13 == 0){
                System.out.println();
            }
        }
    }

    public List<Card> shuffleCards(){
        Integer k;
        List<Integer> integerList = new ArrayList<Integer>();

        for (int i = 0; i < 52; i++){
            integerList.add(i);
        }

        Collections.shuffle(integerList);

        System.out.println("\n洗牌完成!");
        for (int i = 0; i < 52; i++){
            k = integerList.get(i);
            washCards.add(i, cards.get(k));
        }
        return washCards;
    }

    public void ShowShuffled(){
        System.out.println();
        for (int i = 0; i < 52; i++){
            System.out.print(washCards.get(i).color + washCards.get(i).number + " ");
            if((i+1)%13 == 0){
                System.out.println();
            }
        }
    }
}
