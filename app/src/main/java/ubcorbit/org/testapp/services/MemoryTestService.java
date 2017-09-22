package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MemoryTestService extends IntentService {

    private static int instanceCount = 0;
    private static String TAG = "orbitMemTestService";

    public MemoryTestService() {
        super("MemoryTestService (" + Integer.toString(instanceCount++) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent()");
    }

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
     * @return # errors
     */
    private static int allocateAndCheckRam(int size, int time) {

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

    private static int randomAccesses(int array_size, int accesses, int delay) {

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

}
