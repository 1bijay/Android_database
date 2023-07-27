package database.s;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class Customlcass extends BaseAdapter {
    Context text;
    ArrayList<DataModel> datas= new ArrayList<>();
    public Customlcass(FirstActivity firstActivity, ArrayList<DataModel> data) {
        text = firstActivity;
        datas = data;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= LayoutInflater.from(text).inflate(R.layout.singlelayout,null);
        TextView text1 = v.findViewById(R.id.sinname);
        TextView text2 = v.findViewById(R.id.sinadrr);
        TextView text3 = v.findViewById(R.id.sinphn);

        text1.setText(datas.get(i).name);
        text2.setText(datas.get(i).address);
        text3.setText(datas.get(i).phone);
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder build=new AlertDialog.Builder(text);
                View x=LayoutInflater.from(text).inflate(R.layout.edit_data,null);
                build.setView(x);
                EditText namedt=x.findViewById(R.id.edit1);
                EditText adrssedt=x.findViewById(R.id.edit2);
                EditText phnedt=x.findViewById(R.id.edit3);
                Button  edtbtn=x.findViewById(R.id.btnsav33);
                Button  edtdel=x.findViewById(R.id.btndel);
                namedt.setText(datas.get(i).name);
                adrssedt.setText(datas.get(i).address);
                phnedt.setText(datas.get(i).phone);
                AlertDialog alert=build.create();
                alert.show();

                edtbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataModel d=new DataModel();
                        d.id=datas.get(i).id;
                        d.name= namedt.getText().toString();
                        d.address=adrssedt.getText().toString();
                        d.phone=phnedt.getText().toString();
                        DataBaseHelper helper=new DataBaseHelper(text);
                        helper.updatedata(d);


                    }
                });

                edtdel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataBaseHelper drop=new DataBaseHelper(text);
                        drop.deletedata(datas.get(i).id);
                        Intent i = new Intent(text,FirstActivity.class);
                        alert.dismiss();
                        text.startActivity(i);

                    }
                });
                return true;
            }
        });
        return v;


    }
}
