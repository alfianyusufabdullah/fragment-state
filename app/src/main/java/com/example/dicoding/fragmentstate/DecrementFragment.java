package com.example.dicoding.fragmentstate;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DecrementFragment extends Fragment {

    private static final String KEY_COUNTER = "counter";
    private int counter = 0;


    public DecrementFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_decrement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView tvDecrement = view.findViewById(R.id.tvDecrement);

        Button btnDecrement = view.findViewById(R.id.btnDecrement);
        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tvDecrement.setText(String.valueOf(counter));
            }
        });

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(KEY_COUNTER, 0);
        } else {
            counter = 0;
        }

        tvDecrement.setText(String.valueOf(counter));

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_COUNTER , counter);
        super.onSaveInstanceState(outState);
    }
}
