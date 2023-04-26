package com.example.ktratrenlop_tablayout.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.example.ktratrenlop_tablayout.R;
import com.example.ktratrenlop_tablayout.database.SQLiteHelper;
import com.example.ktratrenlop_tablayout.model.CongViec;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eTen, eND, eNgay;
    CheckBox cbTT, cbCT;
    Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        eNgay.setOnClickListener(this);
    }

    private void initView() {
        eTen = findViewById(R.id.eTen);
        eND = findViewById(R.id.eND);
        eNgay = findViewById(R.id.eNgay);
        cbTT = findViewById(R.id.cbTT);
        cbCT = findViewById(R.id.cbCT);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
    }

    @Override
    public void onClick(View view) {
        if(view == eNgay){
            final Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m>8){
                        date = y+"/"+(m+1)+"/"+d;
                    }else {
                        date = y+"/0"+(m+1)+"/"+d;
                    }
                    eNgay.setText(date);
                }
            }, y, m, d);
            dialog.show();
        }
        if (view == btnCancel){
            finish();
        }
        if(view == btnAdd){
            String ten = eTen.getText().toString();
            String nd = eND.getText().toString();
            String ngay = eNgay.getText().toString();
            Boolean tt = cbTT.isChecked();
            Boolean ct = cbCT.isChecked();
            SQLiteHelper db = new SQLiteHelper(this);
            db.addCv(new CongViec(ten, nd, ngay, tt, ct));
            finish();
        }
    }
}