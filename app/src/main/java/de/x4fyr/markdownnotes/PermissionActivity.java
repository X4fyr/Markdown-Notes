package de.x4fyr.markdownnotes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Activity to initially grant always needed permissions to this app.
 */
public class PermissionActivity extends AppCompatActivity {

    private static final int EXTERNAL_STORAGE_REQUEST = 0;

    /**
     * Manually requests permissions.
     *
     * <p>Used from Android Marshmallow on.</p>
     * @param view view which triggered the action. Not actually used, but needed for OnClickListener.
     */
    public void askForPermissions(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,
                                             Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQUEST);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        // Request permissions if needed
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == EXTERNAL_STORAGE_REQUEST && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //noinspection HardCodedStringLiteral
            Log.e(this.getClass().toString(), "Permission granted.");
            startActivity(new Intent(this, MainActivity.class));
        } else {
            //noinspection HardCodedStringLiteral
            Log.e(this.getClass().toString(), "Permission denied.");
        }
    }

}
