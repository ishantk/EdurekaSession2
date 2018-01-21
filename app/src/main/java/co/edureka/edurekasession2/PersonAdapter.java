package co.edureka.edurekasession2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ishantkumar on 21/01/18.
 */

public class PersonAdapter extends ArrayAdapter<Person> {

    Context context;
    int resource;
    ArrayList<Person> objects;

    public PersonAdapter(Context context, int resource, ArrayList<Person> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

                    // Inversion of Control
        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtName = view.findViewById(R.id.textViewName);
        TextView txtPhone = view.findViewById(R.id.textViewPhone);

        // Extracting the Object from ArrayList
        Person person = objects.get(position);

        // Extracting the data from Object and putting it on list_item
        imageView.setBackgroundResource(person.image);
        txtName.setText(person.name);
        txtPhone.setText(person.phone);

        return view;
    }
}
