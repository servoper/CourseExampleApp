package bg.codingschool.myapplication1.view.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import bg.codingschool.myapplication1.R;

/**
 * Created by Nikolay Vasilev on 7/12/2016.
 */
public class DogViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView nameTextView;
    public TextView ageTextView;

    public DogViewHolder(View itemView) {
        super(itemView);

        nameTextView = (TextView) itemView.findViewById(R.id.name);
    }
}
