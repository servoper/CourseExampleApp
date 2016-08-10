package bg.codingschool.myapplication1.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import bg.codingschool.myapplication1.R;
import bg.codingschool.myapplication1.data.model.Dog;
import bg.codingschool.myapplication1.view.adapter.DogsAdapter;

public class Main2Activity extends Activity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private RecyclerView mDogsRecyclerView;
    private RecyclerView.LayoutManager mDogsLinearLayoutManager;
    private DogsAdapter mDogsAdapter;
    private ArrayList<Dog> mDogs;
    private RequestQueue mRequestQueue;
    private TextView mTitleTextView;
    private TextView mBodyTextView;
    private EditText mMyEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        mMyEdit = (EditText) findViewById(R.id.myText);
        Button mOpenNextScreen = (Button) findViewById(R.id.button);

        mOpenNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mMyEdit.getText().toString();
                if (!TextUtils.isEmpty(input)) {
                    Intent intent = new Intent(getApplicationContext(), MyActivity3.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
