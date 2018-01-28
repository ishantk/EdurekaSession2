package co.edureka.edurekasession2;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DynamicLayoutActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dynamic_layout);


        relativeLayout = new RelativeLayout(this);
        button = new Button(this);


        button.setText("Click Me");
        //button.setId(101);
        button.setBackgroundColor(Color.GREEN);

        relativeLayout.setBackgroundColor(Color.RED);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        relativeLayout.addView(button,params);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DynamicLayoutActivity.this,ActivityOne.class);
                startActivity(intent);
            }
        });

        setContentView(relativeLayout);

        Toast.makeText(this,"--onCreate--",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"--onDestroy--",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this,"--Portrait Mode--",Toast.LENGTH_LONG).show();
        }

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this,"--Landscape Mode--",Toast.LENGTH_LONG).show();
        }
    }
}
