package ubcorbit.org.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import ubcorbit.org.testapp.services.RandomAccessService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "orbitMainAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // todo start test services
//        Intent i = new Intent(this, RandomAccessService.class);
//        startService(i);

    }

}
