package ubcorbit.org.testapp.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by bulat on 21/09/17 for TestApp
 */
public class TestCompletionReceiver extends BroadcastReceiver{

    private static int MAX_NUM_TESTS = 10;

    public TestCompletionReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    public void runAllTests() {
        // todo
    }

    private void runRandomAccess() {
        // todo
    }

}
