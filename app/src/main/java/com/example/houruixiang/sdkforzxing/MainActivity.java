package com.example.houruixiang.sdkforzxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAnalysis;
    private TextView tvResult;
    private EditText mInput;
    private CheckBox cbAdd;
    private Button btnBuild;
    private ImageView ivResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();
    }

    private void initEvent() {
        btnAnalysis.setOnClickListener(this);
        btnBuild.setOnClickListener(this);
    }

    private void initView() {
        btnAnalysis = (Button) findViewById(R.id.btn_analysis);
        tvResult = (TextView) findViewById(R.id.tv_Result);
        mInput = (EditText) findViewById(R.id.et_input);
        cbAdd = (CheckBox) findViewById(R.id.cb_addlogo);
        btnBuild = (Button) findViewById(R.id.btn_build);
        ivResult = (ImageView) findViewById(R.id.iv_Result);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_analysis:
                startActivityForResult(new Intent(this, CaptureActivity.class),0);
                break;
            case R.id.btn_build:
                String s = mInput.getText().toString().trim();
                //生成二维码的逻辑
                if (s.equals("")){
                    Toast.makeText(this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    Bitmap bitmap = EncodingUtils.createQRCode(s, 500, 500,
                            cbAdd.isChecked()? BitmapFactory.decodeResource(getResources(),R.mipmap.icon_scord) : null);
                    ivResult.setImageBitmap(bitmap);
                }

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            tvResult.setText("Result:" + result);
        }
    }
}
