package com.example.ex7_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ex7_1.utils.MyDatabaseOpenHelper;

public class LoginActivity extends AppCompatActivity {
    EditText account,password;
    CheckBox autoLogin;
    Button login;
    SQLiteDatabase db;
    MyDatabaseOpenHelper helper;
    SharedPreferences spf;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account=(EditText)findViewById(R.id.editaccount);
        password=(EditText)findViewById(R.id.editpwd);
        autoLogin=(CheckBox)findViewById(R.id.checkBox);
        login=(Button)findViewById(R.id.login);
        spf= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemeber=spf.getBoolean("isRemeber",false);
        if(isRemeber){
            String acc=spf.getString("account","");
            String pwd=spf.getString("password","");
            account.setText(acc);
            password.setText(pwd);
            autoLogin.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper=new MyDatabaseOpenHelper(LoginActivity.this,"Home.db",null,1);
                db=helper.getWritableDatabase();
                String accountString=account.getText().toString();
                String passwordString=password.getText().toString();
                boolean isAutoLogin=autoLogin.isChecked();
                if(TextUtils.isEmpty(accountString)||TextUtils.isEmpty(passwordString)){
                    Toast.makeText(LoginActivity.this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                //1.用sql语句执行查找
                Cursor cursor=db.rawQuery("select * from user where account=? and password=?",new String[]{accountString,passwordString});
                if(cursor.moveToFirst()){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"账号或密码错误！",Toast.LENGTH_SHORT).show();
                }
                //处理自动登录操作
                editor=spf.edit();
                if(isAutoLogin){
                    editor.putBoolean("isRemeber",true);
                    editor.putString("account",accountString);
                    editor.putString("password",passwordString);
                }else{
                    editor.clear();//清空文本框中的数据
                }
                editor.apply();//提交
                //对数据结果集作遍历
//                if(cursor.moveToFirst()){
//                    do{
//                        cursor.getString(cursor.getColumnIndex("account"));
//                    }while (cursor.moveToNext());
//                }
                cursor.close();
                db.close();
            }
        });
    }
    //注册账号的点击事件方法
    public void myClick(View view){
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}
