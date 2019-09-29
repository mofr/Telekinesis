package org.telekinesis.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.telekinesis.telekinesis.R;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabs = findViewById(R.id.tabs);
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons(tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new WindowsFragment(), "Control");
        adapter.addFrag(new WindowsFragment(), "Windows");
        adapter.addFrag(new WindowsFragment(), "System");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons(TabLayout tabs) {
        tabs.getTabAt(0).setIcon(R.drawable.ic_keyboard_24px);
        tabs.getTabAt(1).setIcon(R.drawable.ic_apps_24px);
        tabs.getTabAt(2).setIcon(R.drawable.ic_power_settings_new_24px);
    }
}
