package com.ogieben.okaydemo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.iid.FirebaseInstanceId;
import com.itransition.protectoria.psa_multitenant.protocol.scenarios.linking.LinkingScenarioListener;
import com.itransition.protectoria.psa_multitenant.state.ApplicationState;
import com.ogieben.okaydemo.BuildConfig;
import com.ogieben.okaydemo.R;
import com.ogieben.okaydemo.data.repositories.PreferenceRepo;
import com.ogieben.okaydemo.fcm.OkayDemoFirebaseMessagingService;
import com.ogieben.okaydemo.ui.theme.BaseTheme;
import com.ogieben.okaydemo.utils.PermissionHelper;
import com.protectoria.psa.PsaManager;
import com.protectoria.psa.api.PsaConstants;
import com.protectoria.psa.api.converters.PsaIntentUtils;
import com.protectoria.psa.api.entities.PsaEnrollResultData;
import com.protectoria.psa.api.entities.SpaAuthorizationData;
import com.protectoria.psa.api.entities.SpaEnrollData;
import com.protectoria.psa.dex.common.data.enums.PsaType;

public class MainActivity extends AppCompatActivity {

    private PreferenceRepo preferenceRepo;
    private Button enrollmentButton;
    private PermissionHelper permissionHelper = new PermissionHelper(this);
    private Button linkManuallyButton;
    private EditText linkingEditTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferenceRepo = new PreferenceRepo(getApplicationContext());
        enrollmentButton = (Button) findViewById(R.id.enrollmentButton);
        linkManuallyButton = (Button) findViewById(R.id.manualLinkButton);
        linkingEditTextView = (EditText) findViewById(R.id.linkingCodeEditText);

        checkPermissions();
        fetchInstanceId();
        handleIntent(getIntent());

        enrollmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beginEnrollment();
            }
        });

        linkManuallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkUser(linkingEditTextView.getText().toString());
            }
        });





    }

    private void checkPermissions() {
        String[] requiredPermissions = PsaManager.getRequiredPermissions();
        if (!permissionHelper.hasPermissions(this, requiredPermissions)) {
            permissionHelper.requestPermissions(requiredPermissions);
        }
    }

    private void fetchInstanceId() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("", "getInstanceId failed", task.getException());
                Toast.makeText(this, "Error could not fetch token", Toast.LENGTH_LONG).show();
                return;
            }
            String token = task.getResult().getToken();
            preferenceRepo.putAppPNS(token);
        });

    }

    private void handleIntent(Intent intent) {
        long sessionId = intent.getLongExtra(OkayDemoFirebaseMessagingService.ACTIVITY_WAKE_UP_KEY, 0);
        if (sessionId > 0) {
            Toast.makeText(this, "Current sessionId $sessionId ", Toast.LENGTH_LONG).show();
            startAuthorization(sessionId);
        }
    }


    private void beginEnrollment() {

        String appPns = preferenceRepo.getAppPNS();
        String base64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxgyacF1NNWTA6rzCrtK60se9fVpTPe3HiDjHB7MybJvNdJZIgZbE9k3gQ6cdEYgTOSG823hkJCVHZrcf0/AK7G8Xf/rjhWxccOEXFTg4TQwmhbwys+sY/DmGR8nytlNVbha1DV/qOGcqAkmn9SrqW76KK+EdQFpbiOzw7RRWZuizwY3BqRfQRokr0UBJrJrizbT9ZxiVqGBwUDBQrSpsj3RUuoj90py1E88ExyaHui+jbXNITaPBUFJjbas5OOnSLVz6GrBPOD+x0HozAoYuBdoztPRxpjoNIYvgJ72wZ3kOAVPAFb48UROL7sqK2P/jwhdd02p/MDBZpMl/+BG+qQIDAQAB" ;
        String installationId = "9990";
        if (!TextUtils.isEmpty(appPns)){
            Toast.makeText(this, appPns + " " + BuildConfig.SERVER_URL + " " + BuildConfig.INSTALLATION_ID, Toast.LENGTH_LONG).show();
            SpaEnrollData spaEnroll = new SpaEnrollData(appPns,
                    base64,
                    installationId,
                    null,
                    PsaType.OKAY);

            PsaManager.startEnrollmentActivity(this, spaEnroll);
            return;
        }
        Toast.makeText(this, "App token not found", Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startAuthorization(long sessionId) {
        PsaManager.startAuthorizationActivity(this, new SpaAuthorizationData(sessionId,
                preferenceRepo.getAppPNS(),
                new BaseTheme(this).getDefaultTheme(),
                PsaType.OKAY));
    }

    private void linkUser(String linkingCode) {
        PsaManager psaManager = PsaManager.getInstance();
        LinkingScenarioListener linkingScenarioListener  = new LinkingScenarioListener() {
            @Override
            public void onLinkingCompletedSuccessful(long l, String s) {
                Toast.makeText(MainActivity.this, "Linking Successful", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onLinkingFailed(ApplicationState applicationState) {
                Toast.makeText(MainActivity.this, "Linking not Successful: linkingCode: ${linkingCodeEditText.text} errorCode: ${var1.code} ", Toast.LENGTH_LONG).show();
            }
        };

        psaManager.linkTenant(linkingCode, preferenceRepo, linkingScenarioListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PsaConstants.ACTIVITY_REQUEST_CODE_PSA_ENROLL) {
            if (resultCode == RESULT_OK) {
                //We should save data from Enrollment result, for future usage
                if (data != null){
                   PsaEnrollResultData resultData = PsaIntentUtils.enrollResultFromIntent(data);
                   preferenceRepo.putExternalId(resultData.getExternalId());

                   Toast.makeText(getApplicationContext(),   "Successfully got this externalId " + resultData.getExternalId(), Toast.LENGTH_SHORT).show();
                   return;
                }
                Toast.makeText(this, "Enrollment unsuccessful" + resultCode, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error Retrieving intent after enrollment:- code " + resultCode, Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PsaConstants.ACTIVITY_REQUEST_CODE_PSA_AUTHORIZATION) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Authorization granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Authorization not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
