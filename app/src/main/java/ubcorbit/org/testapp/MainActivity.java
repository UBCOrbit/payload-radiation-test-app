package ubcorbit.org.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import ubcorbit.org.testapp.services.AllocateCheckService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "orbitMainAc";
    private static String RECORD_NAME = "main-activity.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
