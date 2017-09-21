package ubcorbit.org.testapp.services;

import java.util.Random;

/**
 * Created by curtis on 20/09/17.
 */

public class StupidService {
    public static int incrementCounter(int n){
        int total = 0;
        for(int i = 0;i < n;i++){
            total += n;
        }
        return total;
    }
    public static int allocate_and_check_ram(int size,int time){
        byte[] array = new byte[size];
        for(int i = 0; i < size; i++){
            array[i] = 0;
        }
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int errors = 0;
        for(int i = 0; i < size; i++){
            if (array[i] != 0){
                errors++;
            }
        }

        for(int i = 0; i < size; i++){
            array[i] = 0b1111111;
        }
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < size; i++){
            if (array[i] != 0b1111111){
                errors++;
            }
        }

        return errors;

    }
    public static int random_accesses(int array_size, int accesses, int delay){
        Random rng = new Random();

        int[] array = new int[array_size];
        for (int i = 0; i < array_size; i++){
            array[i] = i;
        }

        int errors = 0;
        for (int i = 0; i < accesses; i++){
            int next = rng.nextInt();
            if (array[next] != next){
                errors++;
            }
        }

        return errors;
    }
}
