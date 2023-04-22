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
import com.example.ldt.activities.HomeActivity;
import com.example.ldt.databinding.ActivityHomeBinding;
import com.example.ldt.databinding.FragmentMainBinding;
import com.example.ldt.db.AppDatabase;
import com.example.ldt.db.Health;
import com.example.ldt.db.TamadexDao;
import com.example.ldt.db.UserDao;

import java.util.Random;

/**
 * @author Erika Iwata
 * @since 4/12/23 <br>
 * Title: Project 2 <br>
 * Description: Main Fragment
 */

public class MainFragment extends Fragment {

    //Declare fields
    private UserDao userDao;
    private TamadexDao tamadexDao;

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
        tamadexDao = Room.databaseBuilder(getContext(), AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries().build().tamadexDao();

        //Get username
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        String usr = sharedPref.getString("usr", "");

        //Find health entry corresponding to current user
        int id = userDao.findByUsername(usr).getUid();
        Health health = userDao.findByUid(id);

        //Play idle animation for corresponding tamagotchi
        if (health.getName().equals("Egg")) {
            eggIdleAnimation(view, binding, health, userDao, tamadexDao);
        } else if (health.getName().equals("Tarakotchi")){
            tarakotchiIdleAnimation(view, binding);
        } else if (health.getName().equals("Hanatchi")) {
            hanatchiIdleAnimation(view, binding);
        } else if (health.getName().equals("Zuccitchi")) {
            zuccitchiIdleAnimation(view, binding);
        }

        return view;
    }

    /**
     * Play egg idle animation using timers and handlers
     * This method calls setTamaType() to determine type of tamagotchi after hatching
     * @param view
     * @param binding
     * @param health
     * @param userDao
     * @param tamadexDao
     */
    public void eggIdleAnimation(View view, FragmentMainBinding binding, Health health, UserDao userDao, TamadexDao tamadexDao) {
        //Egg idle animation
        binding.ivMiddleScreen.setImageResource(R.drawable.animation_egg_idle);
        AnimationDrawable eggIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen.getDrawable();
        eggIdleAnimation.start();

        //Timer for egg idle animation
        new Handler(getMainLooper()).postDelayed(() -> {
            binding.ivMiddleScreen.clearAnimation();
            binding.ivMiddleScreen.setVisibility(View.INVISIBLE);
        }, 30000); // 30 second

        //Timer for egg hatching animation (Show hatched egg)
        new Handler(getMainLooper()).postDelayed(() -> {
            binding.ivEgg.setVisibility(View.VISIBLE);
        }, 30000); // 30 second

        //Timer for egg hatching animation (Disappear hatched egg)
        new Handler(getMainLooper()).postDelayed(() -> {
            binding.ivEgg.setVisibility(View.INVISIBLE);
            setTamaType(health, userDao, tamadexDao);
        }, 31000); // 31 seconds

        //Check what type of tamagotchi hatched
        new Handler(getMainLooper()).postDelayed(() -> {
            if (health.getName().equals("Tarakotchi")) {
                tarakotchiIdleAnimation(view, binding);
            } else if (health.getName().equals("Hanatchi")) {
                hanatchiIdleAnimation(view, binding);
            } else if (health.getName().equals("Zuccitchi")) {
                zuccitchiIdleAnimation(view, binding);
            }
        }, 31000); //31 seconds
    }

    /**
     * Plau tarakotchi idle animation
     * @param view
     * @param binding
     */
    public void tarakotchiIdleAnimation(View view, FragmentMainBinding binding) {
        //Tarakotchi idle animation
        binding.ivMiddleScreen2.setImageResource(R.drawable.animation_tarakotchi_idle);
        AnimationDrawable tarakotchiIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen2.getDrawable();
        tarakotchiIdleAnimation.start();
    }

    /**
     * Play hanatchi idle animation
     * @param view
     * @param binding
     */
    public void hanatchiIdleAnimation(View view, FragmentMainBinding binding) {
        //Hanatchi idle animation
        binding.ivMiddleScreen2.setImageResource(R.drawable.animation_hanatchi_idle);
        AnimationDrawable hanatchiIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen2.getDrawable();
        hanatchiIdleAnimation.start();
    }

    /**
     * Play zuccitchi idle animation
     * @param view
     * @param binding
     */
    public void zuccitchiIdleAnimation(View view, FragmentMainBinding binding) {
        //Zuccitchi idle animation
        binding.ivMiddleScreen2.setImageResource(R.drawable.animation_zuccitchi_idle);
        AnimationDrawable zuccitchiIdleAnimation = (AnimationDrawable) binding.ivMiddleScreen2.getDrawable();
        zuccitchiIdleAnimation.start();
    }

    /**
     * Sets the tamagotchi type after it hatches (based on rarity)
     * @param health
     * @param userDao
     * @param tamadexDao
     */
    public void setTamaType(Health health, UserDao userDao, TamadexDao tamadexDao) {

        //Random number from 1 to 100
        int randNum = new Random().nextInt(100) + 1;

        //Set values
        int firstRarity = tamadexDao.getAllRarities().get(0);
        int secondRarity = tamadexDao.getAllRarities().get(1);

        //Set tamagotchi
        String firstTama = tamadexDao.getAllNames().get(0);
        String secondTama = tamadexDao.getAllNames().get(1);
        String thirdTama = tamadexDao.getAllNames().get(2);

        //Update tamagotchi type
        if (randNum <= firstRarity) {
            health.setName(firstTama);
        } else if (randNum <= secondRarity) {
            health.setName(secondTama);
        } else {
            health.setName(thirdTama);
        }
        userDao.updateHealth(health);
    }
}
