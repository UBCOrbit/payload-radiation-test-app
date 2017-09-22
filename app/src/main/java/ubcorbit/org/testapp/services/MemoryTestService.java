package ubcorbit.org.testapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MemoryTestService extends Service {

    private static String TAG = "orbitMemTestService";

    public static int incrementCounter(int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += n;
        }
        return total;
    }

    /**
     * @param size: bytes to write
     * @param time: milliseconds to switch between writing and checking
     * @return
     */
    public static int allocate_and_check_ram(int size, int time) {

        byte[] array = new byte[size];
        for (int i = 0; i < size; i++) {
            array[i] = 0;
        }

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int errors = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] != 0) {
                errors++;
            }
        }

        for (int i = 0; i < size; i++) {
            array[i] = 0b1111111;
        }

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < size; i++) {
            if (array[i] != 0b1111111) {
                errors++;
            }
        }

        return errors;

    }

    public static int random_accesses(int array_size, int accesses, int delay) {

        Random rng = new Random();

        int[] array = new int[array_size];
        for (int i = 0; i < array_size; i++) {
            array[i] = i;
        }

        int errors = 0;
        for (int i = 0; i < accesses; i++) {
            int next = rng.nextInt();
            if (array[next] != next) {
                errors++;
            }
        }

        return errors;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MemoryTestService.onStartCommand");
        int num_errors = allocate_and_check_ram(1000000, 10000);
        Log.d(TAG, "done, num_errors = " + Integer.toString(num_errors));
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
