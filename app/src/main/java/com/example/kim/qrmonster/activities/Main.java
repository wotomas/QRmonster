package com.example.kim.qrmonster.activities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kim.catchmonster.activities.MainActivity;
import com.example.kim.qrmonster.R;
import com.example.kim.qrmonster.adapter.MonsterAdapter;
import com.example.kim.qrmonster.assets.MonsterImageView;
import com.example.kim.qrmonster.controller.CatchedMonsterController;
import com.example.kim.qrmonster.controller.MonsterController;
import com.example.kim.qrmonster.controller.QRcodeController;
import com.example.kim.qrmonster.storage.CatchedMonsterStorage;
import com.example.kim.qrmonster.storage.MonsterStorage;
import com.example.kim.qrmonster.storage.QRcodeStorage;
import com.example.kim.qrmonster.storage.disk.FileManager;
import com.example.kim.qrmonster.units.Monster;


public class Main extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    final static int requestCode = 0;
    final static int trainReqeustCode = 1;
    private static LinkedList<Monster> catchedList = new LinkedList<Monster>();
    private static MonsterStorage monsterStorage = new MonsterStorage();
    private static QRcodeStorage QRstorage = new QRcodeStorage();
    private static CatchedMonsterStorage catchedMonsterStorage = new CatchedMonsterStorage();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initializeControllers(this);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.layout_abs);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        setAdapter(actionBar);

    }

    private void setAdapter(final ActionBar actionBar) {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //mViewPager.getChildAt(0).setBackgroundResource(R.color.possible_result_points);


        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });



        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            if(mSectionsPagerAdapter.getPageTitle(i).equals("EXPLORE")) {
                View tabView = getLayoutInflater().inflate(R.layout.custom_actionbar_tab, null);
                TextView tabText = (TextView) tabView.findViewById(R.id.tabText);
                tabText.setText((mSectionsPagerAdapter.getPageTitle(i)));

                ImageView tabImage = (ImageView) tabView.findViewById(R.id.tabIcon);
                tabImage.setImageDrawable(getResources().getDrawable(R.drawable.explore));

                actionBar.addTab(
                        actionBar.newTab()
                                .setCustomView(tabView)
                                .setTabListener(this));
            } else if(mSectionsPagerAdapter.getPageTitle(i).equals("LIST")) {
                View tabView = getLayoutInflater().inflate(R.layout.custom_actionbar_tab, null);
                TextView tabText = (TextView) tabView.findViewById(R.id.tabText);
                tabText.setText((mSectionsPagerAdapter.getPageTitle(i)));

                ImageView tabImage = (ImageView) tabView.findViewById(R.id.tabIcon);
                tabImage.setImageDrawable(getResources().getDrawable(R.drawable.list));

                actionBar.addTab(
                        actionBar.newTab()
                                .setCustomView(tabView)
                                .setTabListener(this));
            } else if(mSectionsPagerAdapter.getPageTitle(i).equals("BATTLE")) {
                View tabView = getLayoutInflater().inflate(R.layout.custom_actionbar_tab, null);
                TextView tabText = (TextView) tabView.findViewById(R.id.tabText);
                tabText.setText((mSectionsPagerAdapter.getPageTitle(i)));

                ImageView tabImage = (ImageView) tabView.findViewById(R.id.tabIcon);
                tabImage.setImageDrawable(getResources().getDrawable(R.drawable.battle));

                actionBar.addTab(
                        actionBar.newTab()
                                .setCustomView(tabView)
                                .setTabListener(this));
            }
        }
    }
    private void initializeControllers(Main main) {
        //************************************
        //** empty the list for debugging
        //***********************************
        //FileManager.getInstance().deleteAllFile(main);
        //************************************
        //** end
        //***********************************
        MonsterController.getInstance().initMonsterStorage(monsterStorage, main);
        QRcodeController.getInstance().initQRcodeStorage(QRstorage, main);
        CatchedMonsterController.getInstance().initMonsterStorage(catchedMonsterStorage, main);
    }


    public void scanOnClick(View view) {
        //open QR scan activity
        Intent intent = new Intent(this, QRscan.class);
        //startActivity(intent);
        startActivityForResult(intent, requestCode);
    }
    public void trainOnClick(View view) {
        //open QR scan activity
        Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, trainReqeustCode);
    }

    protected void onActivityResult(int request, int result, Intent data) {
        Log.i("main/onActivityResult", "request: " + request + " result: " + result);
        switch(request) {
            case requestCode:
                if(result == RESULT_OK) {
                    //recreate fragment
                    //View view = getCurrentFocus();
                    finish();
                    startActivity(getIntent());
                }
                break;
            case trainReqeustCode:
                if(result == 99) {
                    //Log.i("main/onActivityResult", "request: " + request + " result: " + result);
                }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
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

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_explore).toUpperCase(l);
                case 1:
                    return getString(R.string.title_list).toUpperCase(l);
                case 2:
                    return getString(R.string.title_NFCbattle).toUpperCase(l);
            }
            return null;
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
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int mPage;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
           if(mPage == 1) {
               View rootView = inflater.inflate(R.layout.activity_explore, container, false);
               return getExploreView(rootView);


           } else if(mPage == 2) {
               View rootView = inflater.inflate(R.layout.activity_monster_list, container, false);
               return getMonsterListView(rootView);

           } else if(mPage == 3) {
               View rootView = inflater.inflate(R.layout.activity_nfcbattle, container, false);
               //TextView text = (TextView) rootView.findViewById(R.id.monster_list);
               //text.setText("Fragment #: " + mPage);
               return rootView;
           }

            return null;

        }

        private View getMonsterListView(View rootView) {
            ArrayList<Monster> array = new ArrayList<Monster>();
            catchedList = CatchedMonsterController.getInstance().getMonsterList();
            for(Monster monster: catchedList) {
                array.add(monster);
            }

            MonsterAdapter adapter;
            adapter = new MonsterAdapter(this.getActivity(), R.layout.custom_row_monster, array);

            ListView list = (ListView)rootView.findViewById(R.id.list);
            list.setAdapter(adapter);

            // //TextView text = (TextView) rootView.findViewById(R.id.qr_scan);
            //Log.i("Main/onCreateView", rootView.getClass().toString());
            //text.setText("Fragment #: " + mPage);
            return rootView;
        }

        private View getExploreView(View rootView) {
            Button button2 = (Button) rootView.findViewById(R.id.change_monster);
            Button button3 = (Button) rootView.findViewById(R.id.train_monster);

            Typeface typeface = Typeface.createFromAsset(rootView.getContext().getAssets(), "Pixel Countdown.otf");
            button2.setTypeface(typeface);
            button3.setTypeface(typeface);


            TextView hp = (TextView) rootView.findViewById(R.id.monster_hp);
            TextView name = (TextView) rootView.findViewById(R.id.monster_name);
            TextView att = (TextView) rootView.findViewById(R.id.monster_attack);
            TextView def = (TextView) rootView.findViewById(R.id.monster_defence);

            hp.setTypeface(typeface);
            name.setTypeface(typeface);
            att.setTypeface(typeface);
            def.setTypeface(typeface);

            catchedList = CatchedMonsterController.getInstance().getMonsterList();
            for(Monster monster: catchedList) {
                if(CatchedMonsterController.getInstance().isKeyMonster(monster)) {
                    MonsterImageView imageView = (MonsterImageView) rootView.findViewById(R.id.monster_image);
                    imageView.mainMode(true);
                    TypedArray array = null;
                    switch (monster.get_tier()){
                        case 1:
                            array = getResources().obtainTypedArray(R.array.tier_one_monster_images);
                            imageView.setImageResource(array.getResourceId(monster.get_image(), R.drawable.monster_1));
                            array.recycle();
                            break;
                        case 2:
                            array = getResources().obtainTypedArray(R.array.tier_two_monster_images);
                            imageView.setImageResource(array.getResourceId(monster.get_image(), R.drawable.monster_3));
                            array.recycle();
                            break;
                        case 3:
                            array = getResources().obtainTypedArray(R.array.tier_three_monster_images);
                            imageView.setImageResource(array.getResourceId(monster.get_image(), R.drawable.monster_10));
                            array.recycle();
                            break;
                        case 4:
                            array = getResources().obtainTypedArray(R.array.tier_four_monster_images);
                            imageView.setImageResource(array.getResourceId(monster.get_image(), R.drawable.monster_12));
                            array.recycle();
                            break;
                        case 5:
                            array = getResources().obtainTypedArray(R.array.tier_five_monster_images);
                            imageView.setImageResource(array.getResourceId(monster.get_image(), R.drawable.monster_19));
                            array.recycle();
                            break;
                    }

                    hp.setText("HP: " + Integer.toString(monster.get_health()));
                    name.setText(monster.get_name());
                    att.setText("Attack: " + Integer.toString(monster.get_attack()));
                    def.setText("Defence: " + Integer.toString(monster.get_defence()));
                }
            }


            //TextView text = (TextView) rootView.findViewById(R.id.monster_list);
            //text.setText("Fragment #: " + mPage);
            return rootView;
        }
    }

}
