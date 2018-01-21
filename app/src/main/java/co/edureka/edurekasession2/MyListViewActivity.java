package co.edureka.edurekasession2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    //GridView gridView;

    RecyclerView recyclerView;

    ArrayList<Person> personList;
    PersonAdapter personAdapter;

    PersonRecyclerAdapter personRecyclerAdapter;

    void initViews(){
        listView = findViewById(R.id.listView);
        //gridView = findViewById(R.id.gridView);

        recyclerView = findViewById(R.id.recyclerView);

        Person p1 = new Person(R.drawable.category,"John","987678987");
        Person p2 = new Person(R.drawable.music,"Jennie","347678987");
        Person p3 = new Person(R.drawable.folder,"Jim","23789873");
        Person p4 = new Person(R.drawable.todo,"Jack","566789873");
        Person p5 = new Person(R.drawable.contact,"Joe","456789873");

        personList = new ArrayList<>();
        personList.add(p1); //0
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5); // n-1

        personAdapter = new PersonAdapter(this,R.layout.list_item,personList);

        //personRecyclerAdapter = new PersonRecyclerAdapter(this,R.layout.list_item,personList);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(linearLayoutManager); // ListView
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        //recyclerView.setLayoutManager(gridLayoutManager); // GridView

        //recyclerView.setAdapter(personRecyclerAdapter);

        listView.setAdapter(personAdapter);
        //listView.setOnItemClickListener(this);
        //gridView.setAdapter(personAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);
        //setContentView(R.layout.activity_my_grid_view);
        //setContentView(R.layout.activity_my_recycler_view);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Person person = personList.get(position); // Obtain the selected object
        Toast.makeText(this,"You Selected: "+person.name,Toast.LENGTH_LONG).show();
    }
}
