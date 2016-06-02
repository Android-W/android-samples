package androidsamples.androidw.com.androidsamples.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import androidsamples.androidw.com.androidsamples.Consts;
import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.base.view.BaseActivity;
import androidsamples.androidw.com.androidsamples.firebase.SimpleValueListener;
import androidsamples.androidw.com.androidsamples.util.PrefUtil;
import androidsamples.androidw.com.androidsamples.util.ToastUtil;
import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_installation_status) TextView mTvInstallationStatus;
    @BindView(R.id.cb_push_enable) CheckBox mCbPushEnable;

    private DatabaseReference mTokenReference;
    private BroadcastReceiver mTokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            requestToken();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
        initFirebase();
        requestToken();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initFirebase() {
        mTokenReference = FirebaseDatabase.getInstance().getReference().child("token");
        mCbPushEnable.setChecked(PrefUtil.getBoolean(this, Consts.SP_PUSH_ENABLE, true));
    }

    private void requestToken() {
        mTokenReference.child(FirebaseInstanceId.getInstance().getId()).addValueEventListener(new SimpleValueListener() {
            @Override
            public void onComplete(DataSnapshot dataSnapshot, DatabaseError databaseError) {
                mTokenReference.removeEventListener(this);
                if (databaseError == null) {
                    if (dataSnapshot.getValue() != null) {
                        String token = dataSnapshot.getValue(String.class);
                        if (!TextUtils.isEmpty(token)) {
                            mTvInstallationStatus.setText(token);
                        } else {
                            mTvInstallationStatus.setText("Token is null!");
                        }
                    }
                } else {
                    ToastUtil.show(SettingActivity.this, databaseError.getMessage());
                }
            }
        });
    }

    @OnCheckedChanged(R.id.cb_push_enable)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        PrefUtil.put(this, Consts.SP_PUSH_ENABLE, isChecked);
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
