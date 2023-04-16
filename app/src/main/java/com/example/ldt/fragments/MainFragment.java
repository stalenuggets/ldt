package com.example.ldt.fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ldt.R;
import com.example.ldt.databinding.FragmentMainBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View binding
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.ivMiddleScreen.setImageResource(R.drawable.animation_egg_idle);
        AnimationDrawable eggIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen.getDrawable();
//        eggIdleAnimation.run();

        binding.ivMiddleScreen.post(new Starter(eggIdleAnimation));

//        binding.ivEgg.setBackgroundResource(R.drawable.animation_egg_idle);
//        AnimationDrawable eggIdleAnimation = (AnimationDrawable) binding.ivEgg.getBackground();
//        eggIdleAnimation.run();

        return view;
    }

    class Starter implements Runnable {

        AnimationDrawable eggIdleAnimation;

        Starter(AnimationDrawable eggIdleAnimation) {
            this.eggIdleAnimation = eggIdleAnimation;
        }

        public void run() {
            eggIdleAnimation.start();
        }
    }

}