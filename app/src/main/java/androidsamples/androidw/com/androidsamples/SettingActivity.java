package androidsamples.androidw.com.androidsamples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @BindView(R.id.tv_installation_status) TextView mTvInstallationStatus;
    @BindView(R.id.bt_installation_toggle) Button mBtInstallationToggle;

    private Firebase mFirebase;
    private String mInstanceId;
    private String mToken;

    private BroadcastReceiver mTokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("uno", "onReceive " + intent.getAction());
            requestToken();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initView();
        initFirebase();
        requestToken();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
        mFirebase = new Firebase("https://androidsample.firebaseio.com/token");
        mInstanceId = FirebaseInstanceId.getInstance().getId();
        mToken = FirebaseInstanceId.getInstance().getToken();
    }

    private void requestToken() {
        if (!TextUtils.isEmpty(mInstanceId) && !TextUtils.isEmpty(mToken)) {
            mFirebase.child(mInstanceId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null || TextUtils.isEmpty(dataSnapshot.getValue().toString())) {
                        mTvInstallationStatus.setText("Not apply FCM");
                        mBtInstallationToggle.setText("APPLY");
                    } else {
                        mTvInstallationStatus.setText(dataSnapshot.getValue().toString());
                        mBtInstallationToggle.setText("CANCEL");
                    }

                    mBtInstallationToggle.setEnabled(true);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        } else {
            mTvInstallationStatus.setText("Token is null.");
            mBtInstallationToggle.setEnabled(false);
        }
    }

    @OnClick({ R.id.bt_installation_toggle })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_installation_toggle:
                String status = mBtInstallationToggle.getText().toString();
                switch (status) {
                    case "등록":
                        User user = new User();
                        user.name = "test1";
                        user.token = mToken;
                        mFirebase.child(mInstanceId).setValue(user);
                        break;

                    case "해제":
                        mFirebase.child(mInstanceId).removeValue();
                        break;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mTokenReceiver, new IntentFilter(Consts.ACTION_RECEIVE_TOKEN));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mTokenReceiver);
        super.onPause();
    }
}
