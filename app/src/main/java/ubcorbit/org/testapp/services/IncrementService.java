package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class IncrementService extends IntentService {

    private static int instanceCount = 0;
    private static String TAG = "orbitIncrementSe";

    public IncrementService() {
        super("IncrementService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG, "onHandleIntent()");

    }

    public int incrementCounter(int n) {

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += n;
        }

        return total;
    }

}
