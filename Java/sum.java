public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        Scanner scan = new Scanner(System.in);
        int sum1 = 0;
        int sum2 = 0;
        String str = null;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(scan.hasNext()){
            sum1 = 0;
            sum2 = 0;

            str = scan.nextLine();
            String[] tmp = str.split(" ");

            for(int i = 0; i < tmp.length; i++){
                if(i%2 == 0){sum1 += Integer.parseInt(tmp[i]);}
                else if(i%2 != 0){sum2 += Integer.parseInt(tmp[i]);}
            }
            if(sum1 > sum2){
                System.out.println(sum1);
            }
            else{
                System.out.println(sum2);
            }
        }
    }
}