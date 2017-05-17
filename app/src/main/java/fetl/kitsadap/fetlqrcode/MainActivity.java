package fetl.kitsadap.fetlqrcode;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit ประกาศตัวแปร อยู่ในคลาส และยุนอกแมทตอด
    //ประกาศเพื่นบอกมือถึงให้เตรียมแรมให้พอ
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial View
        initialView();
        //Controler ทำให้คลิกได้
        controller(); //กด alt + enter ได้ Method


    } // Main Method

    private void controller() {
        textView.setOnClickListener(MainActivity.this);//Listener ทำเอง
        button.setOnClickListener(MainActivity.this); //พิมพ์ Main แล้ว . แล้ว พิมพ์ this
    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtNewRegis);
        button = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View v) {

        // For TextView
        if (v == textView) {
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);//สืบทอด class
            startActivity(intent); //หน้า RegisterActivity มาบังหน้า กด Back กลับหน้า MainActivity
        }
        // For button
        if (v == button) {

            //Get Value From EditText
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            //Check Space
            if (userString.equals("")|| passwordString.equals("")) {
                //Have Space
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDialog(getResources().getString(R.string.title_HaveSpace),
                        getResources().getString(R.string.massage_HaveSpace));

            } else {
                //No Space
                checkUserAnPass();

            }


        }

    }

    private void checkUserAnPass() {

        try {

            GetData getData = new GetData(this);
            Myconstant myconstant = new Myconstant();
            getData.execute(myconstant.getUrlGetUser());
            String strJSON = getData.get();
            Log.d("17MayV2", "JSON ==>" + strJSON);
            //showMessage(strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            boolean b = true;  // User False
            String strName = null, strPassword = null;
            MyAlert myAlert = new MyAlert(this);
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (userString.equals(jsonObject.getString("User"))) {
                    b = false;
                    strName = jsonObject.getString("Name");
                    strPassword = jsonObject.getString("Password");
                }
            } // for


            if (b) {
                //User False
                myAlert.myDialog(getResources().getString(R.string.title_UserFalse),
                        getResources().getString(R.string.massage_UserFalse));

            } else if (passwordString.equals(strPassword)) {
                // Password True
                Toast.makeText(MainActivity.this, "Welcom" + strName, Toast.LENGTH_SHORT).show(); //strName ได้จาก alt + enter เพื่อใส่ Null ให้ตัวแปล

                //Intent to Service
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                intent.putExtra("Login", strName);
                startActivity(intent);
                finish();

            } else {
                // Password False
                myAlert.myDialog(getResources().getString(R.string.title_plassFalse),
                        getResources().getString(R.string.message_PasswordFales));

            }


        } catch (Exception e) {
            Log.d("17MayV2", "e checkUser ==>" + e.toString());
        }

    }

    private void showMessage(String strJSON) {
        Toast.makeText(MainActivity.this, strJSON, Toast.LENGTH_SHORT).show();
    }
} //Main Class นี่คือ คลาสหลัก
