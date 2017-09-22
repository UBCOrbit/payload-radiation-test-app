package ubcorbit.org.testapp.services;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by bulat on 21/09/17 for TestApp
 */
public class RAMFillerService extends IntentService {

    private static int instanceCount = 0;
    private static String TAG = "orbitRAMFillerSe";

    public RAMFillerService() {
        super("RAMFillerService (" + Integer.toString(++instanceCount) + ")");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // todo
    }

}
