package com.example.android.testone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.*;
import java.lang.reflect.Field;

import static android.R.attr.tag;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by peisu on 2017/9/5.
 */

public class LoginActivity extends Activity {

    private Button loginButton;
    private Button registButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
}
