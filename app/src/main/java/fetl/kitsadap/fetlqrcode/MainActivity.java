package fetl.kitsadap.fetlqrcode;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit ประกาศตัวแปร อยู่ในคลาส และยุนอกแมทตอด
    //ประกาศเพื่นบอกมือถึงให้เตรียมแรมให้พอ
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;

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

        }

    }
} //Main Class นี่คือ คลาสหลัก
