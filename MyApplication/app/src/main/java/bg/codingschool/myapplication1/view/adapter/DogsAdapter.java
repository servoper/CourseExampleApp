package bg.codingschool.myapplication1.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bg.codingschool.myapplication1.R;
import bg.codingschool.myapplication1.data.model.Dog;
import bg.codingschool.myapplication1.view.adapter.viewholders.DogViewHolder;

/**
 * Created by Nikolay Vasilev on 7/12/2016.
 */
public class DogsAdapter extends RecyclerView.Adapter<DogViewHolder> {


    private ArrayList<Dog> mDogs;

    public DogsAdapter(ArrayList<Dog> dogs) {
        mDogs = dogs;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_dog, parent, false);
        return new DogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        Dog dog = mDogs.get(position);

        holder.nameTextView.setText(dog.name);
        holder.ageTextView.setText(dog.age);
    }

    @Override
    public int getItemCount() {
        return mDogs.size();
    }
}
