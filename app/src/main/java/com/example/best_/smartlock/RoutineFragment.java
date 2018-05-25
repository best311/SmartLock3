package com.example.best_.smartlock;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.best_.smartlock.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoutineFragment extends Fragment {


    private Post post;
    private TextInputLayout textInputLayoutPost;
    private TextInputEditText textInputEditTextPost;
    private DatabaseHelper databaseHelper;

    public RoutineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_routine, container, false);
        Button btn = rootView.findViewById(R.id.buttonPost);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataSQL();
                Intent itn = new Intent(getActivity(), RoomActivity.class);
                startActivity(itn);
            }
        });

        textInputEditTextPost = (TextInputEditText) rootView.findViewById(R.id.textInputEditTextPost);
        post = new Post();
        databaseHelper = new DatabaseHelper(getActivity());

        // Inflate the layout for this fragment
        return rootView;
    }

    private void postDataSQL(){
        post.setPost(textInputEditTextPost.getText().toString().trim());
        long row = databaseHelper.addPost(post);
        if(row != -1){
            Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getActivity(), "Fail", Toast.LENGTH_LONG).show();
        }
    }

}
