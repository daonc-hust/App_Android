package com.example.congdao.sambaby;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    ImageView imgHeart, imgKiss;

    public static int arrayImage[] = {R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3, R.drawable.pic_4, R.drawable.pic_5, R.drawable.pic_6, R.drawable.pic_7, R.drawable.pic_8, R.drawable.pic_9, R.drawable.pic_11, R.drawable.pic_13};
    public static Animation animIn, animOut, animRotate, animTranslate, animScale;
    public static MediaPlayer mediaPlayer, dripSound, kissSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.i_need_your_love);
        mediaPlayer.start();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        imgHeart = (ImageView) findViewById(R.id.imageViewHeart);
        imgKiss = (ImageView) findViewById(R.id.imageViewKiss);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                imgHeart.setVisibility(View.INVISIBLE);
                imgHeart.startAnimation(animTranslate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.heart_translate));

                dripSound = MediaPlayer.create(getApplicationContext(), R.raw.drip);
                dripSound.start();
                dripSound.seekTo(1500);
            }
        });

        if (!fab.hasOnClickListeners()) {
            dripSound.stop();
        }

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgKiss.setVisibility(View.INVISIBLE);
                imgKiss.startAnimation(animScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.kiss_scale));

                kissSound = MediaPlayer.create(getApplicationContext(), R.raw.kiss);
                kissSound.start();
            }
        });

        if (!fab1.hasOnClickListeners()) {
            kissSound.stop();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.upload_settings) {
            startActivity(new Intent(MainActivity.this, UploadActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String TAB_SECTION = "tab";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int tab) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(TAB_SECTION, tab);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.relativeLayout);

            ViewFlipper viewFlipper = (ViewFlipper) rootView.findViewById(R.id.viewFlipper);

            animIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
            animOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
            animRotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);


            ListView listView;
            AdapterImage adapterImage;
            AdapterMusic adapterMusic;
            ArrayList<ImageViewSam> arrayListImage;
            ArrayList<Music> arraylistMusic;
            final ArrayList<Integer> listMusic;
            //final MediaPlayer mediaPlayer;
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText("Hello!");
//            textView.setText(getString(R.string.section_format, getArguments().getInt(KEY_COLOR)));

            switch (getArguments().getInt(TAB_SECTION)) {
                case 1:
                    listView = (ListView) rootView.findViewById(R.id.listView);

                    arrayListImage = new ArrayList<>();
                    arrayListImage.add(new ImageViewSam(1, R.drawable.pic_1));
                    arrayListImage.add(new ImageViewSam(2, R.drawable.pic_2));
                    arrayListImage.add(new ImageViewSam(3, R.drawable.pic_3));
                    arrayListImage.add(new ImageViewSam(4, R.drawable.pic_4));
                    arrayListImage.add(new ImageViewSam(5, R.drawable.pic_5));
                    arrayListImage.add(new ImageViewSam(6, R.drawable.pic_6));
                    arrayListImage.add(new ImageViewSam(7, R.drawable.pic_7));
                    arrayListImage.add(new ImageViewSam(8, R.drawable.pic_8));
                    arrayListImage.add(new ImageViewSam(9, R.drawable.pic_9));
                    arrayListImage.add(new ImageViewSam(10, R.drawable.pic_10));
                    arrayListImage.add(new ImageViewSam(11, R.drawable.pic_11));
//                    arrayListImage.add(new ImageViewSam(12, R.drawable.pic_12));
//                    arrayListImage.add(new ImageViewSam(13, R.drawable.pic_13));

                    adapterImage = new AdapterImage(getContext(), R.layout.image, arrayListImage);
                    listView.setAdapter(adapterImage);
                    break;
                case 2:
                    listView = (ListView) rootView.findViewById(R.id.listView);
                    listView.setBackgroundResource(R.drawable.music_bg);

                    listMusic = new ArrayList<>();
                    listMusic.add(R.raw.i_need_your_love);
                    listMusic.add(R.raw.all_we_know);
                    listMusic.add(R.raw.some_thing_just_like_this);
                    listMusic.add(R.raw.dusk_till_dawn);
                    listMusic.add(R.raw.we_cant_stop);
                    listMusic.add(R.raw.demons);
                    listMusic.add(R.raw.some_one_like_you);
                    listMusic.add(R.raw.symphony);
                    listMusic.add(R.raw.how_did_i_fall_in_love_with_you);
                    listMusic.add(R.raw.save_me);

                    arraylistMusic = new ArrayList<>();
                    arraylistMusic.add(new Music("I need your love", "Madilyn Bailey ft Jake Coco"));
                    arraylistMusic.add(new Music("All we know", "The Chainsmokers ft Phoebe Ryan"));
                    arraylistMusic.add(new Music("Somethings just like this", "The Chainsmokers ft Coldplay"));
                    arraylistMusic.add(new Music("Dusk till dawn", "ZAYN ft Sia"));
                    arraylistMusic.add(new Music("We can't stop", "Boyce Avenue ft Bea Miller "));
                    arraylistMusic.add(new Music("Demons", "Imagine Dragon"));
                    arraylistMusic.add(new Music("Some one like you", "Adele"));
                    arraylistMusic.add(new Music("Symphony", "Clean Bandit ft Zara Larsson"));
                    arraylistMusic.add(new Music("How did i fall in love with you", "Yao SiTing"));
                    arraylistMusic.add(new Music("Save me", "DEAMN"));

                    adapterMusic = new AdapterMusic(R.layout.music, getContext(), arraylistMusic);

                    listView.setAdapter(adapterMusic);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mediaPlayer.stop();
                            mediaPlayer = MediaPlayer.create(getContext(), listMusic.get(i));
                            mediaPlayer.start();
                        }
                    });

                    break;
                case 3:
                    for (int i = 0; i < arrayImage.length; i++) {
                        ImageView imageView = new ImageView(getContext());
                        imageView.setImageResource(arrayImage[i]);
                        viewFlipper.addView(imageView);
                    }

                    viewFlipper.setAnimation(animRotate);
                    viewFlipper.setInAnimation(animIn);
                    viewFlipper.setOutAnimation(animOut);
                    viewFlipper.setFlipInterval(3000);
                    viewFlipper.setAutoStart(true);
                    break;
                default:
                    relativeLayout.setBackgroundColor(Color.BLUE);
                    break;
            }

            return rootView;
        }
    }
}
