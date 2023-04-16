package com.example.ldt.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ldt.R;
import com.example.ldt.databinding.FragmentMainBinding;

/**
 * @author Erika Iwata
 * @since 4/12/23 <br>
 * Title: Project 2 <br>
 * Description: Main Fragment
 */

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * On Create method
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * On Create View method - Displays fragment
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View binding
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //Egg idle animation
        binding.ivMiddleScreen.setImageResource(R.drawable.animation_egg_idle);
        AnimationDrawable eggIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen.getDrawable();
        eggIdleAnimation.start();

        return view;
    }

}