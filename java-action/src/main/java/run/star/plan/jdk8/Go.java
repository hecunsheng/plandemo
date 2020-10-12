package run.star.plan.jdk8;

import java.util.Random;

/**
 * @author hecs
 * @date 2020-07-22 16:28
 */
public class Go {
    public static void main(String[] args) {
        Random random = new Random();
        boolean charge = false;
        int n = 0, ch = 0;
        int temp[] = new int[6];//
        System.out.print("red:\t");
        while (true) {
            charge = false;
            if (n == 0) {
                temp[n] = random.nextInt(32) + 1;
            }
            ch = random.nextInt(32) + 1;
            for (int i = 0; i < temp.length; i++) {
                if (ch == temp[i]) {
                    charge = true;
                }
            }
            if (charge == false) {
                temp[n] = ch;
                n++;
            }
            if (n == 6) {
                break;
            }
        }
        for (int i = 0; i < 6; i++) {
            System.out.print("\t" + temp[i]);
        }
        System.out.print("\tblue:\t" + (random.nextInt(15) + 1));
    }
}
