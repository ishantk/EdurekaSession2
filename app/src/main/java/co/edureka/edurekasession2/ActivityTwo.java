package co.edureka.edurekasession2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtName,eTxtAge;
    Button btnSubmit;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtAge = findViewById(R.id.editTextAge);
        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);

        //Intent rcv = getIntent();

        //Bundle bundle = rcv.getBundleExtra("keyBundle");

        //String name = bundle.getString("keyName");
        //String age = bundle.getString("keyAge");

        //User user = (User) rcv.getSerializableExtra("keyUser");


        //String name = rcv.getStringExtra("keyName");
        //String age = rcv.getStringExtra("keyAge");

        //int a = Integer.parseInt(age);

        //eTxtName.setText("Name:" + user.name);
        //eTxtAge.setText("Age:" + user.age);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initViews();
    }

    @Override
    public void onClick(View view) {

        String name = eTxtName.getText().toString();
        String age = eTxtAge.getText().toString();

        Intent data = new Intent(); // Empty Intent, Created juts to hold the data
        data.putExtra("keyName",name);
        data.putExtra("keyAge",age);

        setResult(201,data);

        finish();

    }
}
