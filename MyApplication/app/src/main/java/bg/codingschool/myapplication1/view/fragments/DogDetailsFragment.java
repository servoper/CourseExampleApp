package bg.codingschool.myapplication1.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bg.codingschool.myapplication1.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DogDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DogDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogDetailsFragment extends Fragment {

    private static final String ARG_ID = "id";
    private static final String ARG_NAME = "name";
    private static final String ARG_AGE = "age";

    private String mName;
    private long mId;
    private short mAge;

    private OnFragmentInteractionListener mListener;

    public DogDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name the name of the dog.
     * @param age  the age of the dog.
     * @return A new instance of fragment DogDetailsFragment.
     */
    public static DogDetailsFragment newInstance(int id, String name, short age) {
        DogDetailsFragment fragment = new DogDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        args.putString(ARG_NAME, name);
        args.putShort(ARG_AGE, age);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
            mId = getArguments().getLong(ARG_ID);
            mAge = getArguments().getShort(ARG_AGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Context context = getActivity();
        String preferencesKey = context.getPackageName() + "PREFERENCES";
        SharedPreferences sharedPref = context.getSharedPreferences(
                preferencesKey, Context.MODE_PRIVATE);

        int userId;
        if (sharedPref.contains("my_user_id")) {
            int defaultValue = 0; // If value in prefs not exist for this key
            userId = sharedPref.getInt("my_user_id", defaultValue);
        } else {
            userId = getUserId();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("my_user_id", userId);
            editor.apply();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public int getUserId() {
        return 1;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
