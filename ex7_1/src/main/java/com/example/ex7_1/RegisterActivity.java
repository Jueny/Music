package com.example.ex7_1;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ex7_1.utils.MyDatabaseOpenHelper;

public class RegisterActivity extends AppCompatActivity {
    EditText account,password,confirm;
    Button register;
    SQLiteDatabase db;
    MyDatabaseOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        account=(EditText)findViewById(R.id.regaccount2);
        password=(EditText)findViewById(R.id.editregpwd);
        confirm=(EditText)findViewById(R.id.editcheck);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //只在第一次创建数据库
                helper=new MyDatabaseOpenHelper(RegisterActivity.this,"Home.db",null,1);
                db=helper.getWritableDatabase();
                String accountString=account.getText().toString();
                String passwordString=password.getText().toString();
                String confirmString=confirm.getText().toString();
                if(TextUtils.isEmpty(accountString)||TextUtils.isEmpty(passwordString)||TextUtils.isEmpty(confirmString)){
                    Toast.makeText(RegisterActivity.this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!passwordString.equals(confirmString)){
                    Toast.makeText(RegisterActivity.this,"两次输入密码不一致！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //检查该账号是否已存在

                //1.用sql语句,execSQL()方法可用于增、删、改操作
                db.execSQL("insert into user (account,password) values(?,?)",new String[]{accountString,passwordString});
                //删除
//                db.execSQL("delete user where account=?",new String[]{accountString});
                db.close();
                //结束当前活动
                finish();

            }
        });
    }
}
