package database.s;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    Button adds;
    TextView shows,shows2;
    ListView lis;
    ArrayList<DataModel> data=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adds=findViewById(R.id.actbtn);
        shows=findViewById(R.id.acttxt); 
        shows2=findViewById(R.id.acttxt1);
        lis=findViewById(R.id.lis1);
        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder build=new AlertDialog.Builder(FirstActivity.this);
                View v= LayoutInflater.from(FirstActivity.this).inflate(R.layout.add_data,null);
                build.setView(v);
                AlertDialog alert=build.create();
                alert.show();
                EditText name=v.findViewById(R.id.add1);
                EditText adreeess=v.findViewById(R.id.add2);
                EditText cont=v.findViewById(R.id.add3);
                Button btnsav=v.findViewById(R.id.btnsav);
                Button btncan=v.findViewById(R.id.btncnc);

                btnsav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataModel d= new DataModel();
                        d.name=name.getText().toString();
                        d.address=adreeess.getText().toString();
                        d.phone=cont.getText().toString();

                        DataBaseHelper help = new DataBaseHelper(FirstActivity.this);
                        help.insertdata(d);
                        alert.dismiss();

                    }
                });

               btncan.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       alert.dismiss();
                   }
               });


            }
        });
       DataBaseHelper display=new DataBaseHelper(FirstActivity.this);
       data=display.readdata();
       shows2.setText("student size"+data.size());
       lis.setAdapter(new Customlcass(FirstActivity.this,data));

    }
}
