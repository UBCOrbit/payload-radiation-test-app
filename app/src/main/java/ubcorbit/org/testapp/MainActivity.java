package ubcorbit.org.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "orbitMainAc";
    private static String RECORD_NAME = "main-activity.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent i2 = new Intent(this, RandomAccessService.class);
//        i2.putExtra(RandomAccessService.ITAG_ARRAY, 1000000);
//        i2.putExtra(RandomAccessService.ITAG_ACCESSES, 10000);
//        startService(i2);

    }

}
