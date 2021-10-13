package com.example.sqlite_quanlysinhvien_luyentap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lvSinhvien;
    ArrayList<SinhVien> sinhVienArrayList;
    SinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvSinhvien = (ListView) findViewById(R.id.LVSINHVIEN);
        sinhVienArrayList = new ArrayList<>();
        adapter = new SinhVienAdapter(this, R.layout.dong_sinh_vien, sinhVienArrayList);
        lvSinhvien.setAdapter(adapter);
        database = new Database(this, "chang.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS SinhVien(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenSV varchar(200))");
        //database.QueryData("INSERT INTO SinhVien VALUES(null,'Nguyen Cong Chang')");

        GetDataSinhVien();
    }

    public void GetDataSinhVien() {
        Cursor dataSinhVien = database.GetData("SELECT * FROM SinhVien");
        sinhVienArrayList.clear();
        while (dataSinhVien.moveToNext()) {
            String ten = dataSinhVien.getString(1);
            int id = dataSinhVien.getInt(0);
            sinhVienArrayList.add(new SinhVien(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd_sinhvien, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnTHEMSINHVIEN) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    public void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them);
        EditText edtten = dialog.findViewById(R.id.EDTTHEMSINHVIEN);
        Button btnThem = dialog.findViewById(R.id.btnThem);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmoi = edtten.getText().toString();
                if (tenmoi.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui long nhap ten sinh vien", Toast.LENGTH_SHORT).show();
                }else {
                    database.QueryData("INSERT INTO SinhVien VALUES(null,'"+tenmoi+"')");
                    Toast.makeText(MainActivity.this, "Da them!", Toast.LENGTH_SHORT).show();
                    GetDataSinhVien();
                    dialog.dismiss();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void SuaSinhVien(String ten, int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_sinh_vien);
        EditText edtSuaSinhVien = dialog.findViewById(R.id.EDTCAPNHATSINHVIEN);
        Button btnUpdate = dialog.findViewById(R.id.BTNCAPNHAT);
        Button btnHuy = dialog.findViewById(R.id.BTNHUYCAPNHAT);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        edtSuaSinhVien.setText(ten);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tencancapnhat = edtSuaSinhVien.getText().toString();
                database.QueryData("UPDATE SinhVien SET TenSV = '"+tencancapnhat+"' WHERE Id = '"+id+"'");
                Toast.makeText(MainActivity.this, "Da cap nhat", Toast.LENGTH_SHORT).show();
                GetDataSinhVien();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void XoaSinhVien(String ten, int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Ban co muon xoa "+ten+" nay khong? ");
        dialogXoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM SinhVien WHERE Id = '"+id+"'");
                Toast.makeText(MainActivity.this, "Da Xoa", Toast.LENGTH_SHORT).show();
                GetDataSinhVien();
            }
        });
        dialogXoa.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }

}