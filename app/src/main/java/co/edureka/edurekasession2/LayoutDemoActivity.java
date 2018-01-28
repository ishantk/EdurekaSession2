package co.edureka.edurekasession2;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class LayoutDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_demo);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Awesome");

        //actionBar.hide();
        //actionBar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_demo,menu); // Read XML File and Construct the menu

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.about:

                break;

            case R.id.contact:
                Intent intent = new Intent(LayoutDemoActivity.this,DynamicLayoutActivity.class);
                startActivity(intent);
                break;

            case R.id.home:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
