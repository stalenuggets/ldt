package com.example.ldt.fragments;

import static android.os.Looper.getMainLooper;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ldt.R;
import com.example.ldt.databinding.FragmentMainBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.Health;
import com.example.ldt.db.UserDao;

import java.util.Random;

/**
 * @author Erika Iwata
 * @since 4/12/23 <br>
 * Title: Project 2 <br>
 * Description: Main Fragment
 */

public class MainFragment extends Fragment {

    private UserDao userDao;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * On Create method
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Setup onCreate
        super.onCreate(savedInstanceState);

    }

    /**
     * On Create View method - Displays fragment
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View binding
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //Build database
        userDao = Room.databaseBuilder(getContext(), AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().userDao();

        //Get username
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        String usr = sharedPref.getString("usr", "");

        //Find health entry corresponding to current user
        int id = userDao.findByUsername(usr).getUid();
        Health health = userDao.findById(id);

        //Play idle animation for corresponding tamagotchi
        if (health.getName().equals("Egg")) {
            eggIdleAnimation(view, binding, health, userDao);
        } else if (health.getName().equals("Tarakotchi")){
            tarakotchiIdleAnimation(view, binding);
        } else if (health.getName().equals("Hanatchi")) {
            hanatchiIdleAnimation(view, binding);
        }

        return view;
    }

    public void eggIdleAnimation(View view, FragmentMainBinding binding, Health health, UserDao userDao) {
        //Egg idle animation
        binding.ivMiddleScreen.setImageResource(R.drawable.animation_egg_idle);
        AnimationDrawable eggIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen.getDrawable();
        eggIdleAnimation.start();

        new Handler(getMainLooper()).postDelayed(() -> {
            binding.ivMiddleScreen.clearAnimation();
            binding.ivMiddleScreen.setVisibility(View.INVISIBLE);
        }, 10000); // 10 second

        new Handler(getMainLooper()).postDelayed(() -> {
            binding.ivEgg.setVisibility(View.VISIBLE);
        }, 10000); // 10 second

        new Handler(getMainLooper()).postDelayed(() -> {
            binding.ivEgg.setVisibility(View.INVISIBLE);
            updateTamaType(health, userDao);
        }, 11000); // 11 seconds

        //Check what type of tamagotchi hatched
        new Handler(getMainLooper()).postDelayed(() -> {
            if (health.getName().equals("Tarakotchi")) {
                tarakotchiIdleAnimation(view, binding);
            } else if (health.getName().equals("Hanatchi")) {
                hanatchiIdleAnimation(view, binding);
            }
        }, 11000); //11 seconds
    }

    //TODO - use rarity
    public void updateTamaType(Health health, UserDao userDao) {

        int randNum = new Random().nextInt(2);

        //Update tamagotchi type
        if (randNum == 0) {
            health.setName("Tarakotchi");
        } else {
            health.setName("Hanatchi");
        }
        userDao.updateHealth(health);
    }

    public void tarakotchiIdleAnimation(View view, FragmentMainBinding binding) {
        //Tarakotchi idle animation
        binding.ivMiddleScreen2.setImageResource(R.drawable.animation_tarakotchi_idle);
        AnimationDrawable tarakotchiIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen2.getDrawable();
        tarakotchiIdleAnimation.start();
    }

    public void hanatchiIdleAnimation(View view, FragmentMainBinding binding) {
        //Hanatchi idle animation
        binding.ivMiddleScreen2.setImageResource(R.drawable.animation_hanatchi_idle);
        AnimationDrawable hanatchiIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen2.getDrawable();
        hanatchiIdleAnimation.start();
    }
}
