package bg.codingschool.myapplication1.view.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import bg.codingschool.myapplication1.R;
import bg.codingschool.myapplication1.data.model.Dog;
import bg.codingschool.myapplication1.data.sqlite.provider.DogContentProvider;
import bg.codingschool.myapplication1.view.adapter.DogsAdapter;

/**
 * Created by Nikolay Vasilev on 8/10/2016.
 */
public class RouterActivity extends Activity {

    private RecyclerView mDogsRecyclerView;
    private ArrayList<Dog> mDogsList = new ArrayList<>();
    private DogsAdapter mDogsAdapter;
    private boolean mIsActivityOnFocus = false;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mDogsRecyclerView = (RecyclerView) findViewById(R.id.myList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        mDogsRecyclerView.setLayoutManager(layoutManager);

        mDogsAdapter = new DogsAdapter(mDogsList);
        mDogsRecyclerView.setAdapter(mDogsAdapter);

        mIsActivityOnFocus = true;

        AsyncTask<String, ArrayList<Dog>, ArrayList<Dog>> getDogsFromDbAsyncTask
                = new AsyncTask<String, // type of input parameters
                ArrayList<Dog>, // type of progress
                ArrayList<Dog> // type of the result
                >() {

            @Override
            protected ArrayList<Dog> doInBackground(String... names) {
                ArrayList<Dog> dogs = new ArrayList<>();

                for (String searchForName : names) {
                    DogContentProvider dogContentProvider
                            = new DogContentProvider(getApplicationContext());

                    dogs.addAll(dogContentProvider.getDogs(searchForName));

                    publishProgress(dogs);
                }

                return dogs;
            }

            @Override
            protected void onProgressUpdate(ArrayList<Dog>... values) {
                super.onProgressUpdate(values);

                populateViews(values[0]);
            }

            @Override
            protected void onPostExecute(ArrayList<Dog> requestedDogs) {
                super.onPostExecute(requestedDogs);

                // hide loading

                populateViews(requestedDogs);
            }

            private void populateViews(final ArrayList<Dog> requestedDogs) {
                if (mIsActivityOnFocus) {
                    runOnUiThread(new Runnable() { // only for activity
                        @Override
                        public void run() {
                            mDogsList.clear();
                            mDogsList.addAll(requestedDogs);
                            mDogsAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        };

        getDogsFromDbAsyncTask.execute("Stamat1");
    }

    @Override
    protected void onResume() {
        super.onResume();

        mIsActivityOnFocus = true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        mIsActivityOnFocus = false;
    }
}
