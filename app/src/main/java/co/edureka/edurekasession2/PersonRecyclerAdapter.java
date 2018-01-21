package co.edureka.edurekasession2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ishantkumar on 21/01/18.
 */

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.ViewHolder>{


    Context context;
    int resource;
    ArrayList<Person> objects;

    public PersonRecyclerAdapter(Context context, int resource, ArrayList<Person> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Extracting the Object from ArrayList
        Person person = objects.get(position);

        // Extracting the data from Object and putting it on list_item
        holder.imageView.setBackgroundResource(person.image);
        holder.txtName.setText(person.name);
        holder.txtPhone.setText(person.phone);

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    // ViewHolder shall hold the Views of list_item
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtName;
        TextView txtPhone;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtPhone = itemView.findViewById(R.id.textViewPhone);
        }
    }

}
