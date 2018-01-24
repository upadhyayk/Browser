package edu.temple.browser;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by krati on 11/12/17.
 */

public class myAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> fragments = new ArrayList<String>();
    BrowserFragment browserFragment;

    public myAdapter(FragmentManager fragmentManager){
        super(fragmentManager);

    }

    @Override
    public Fragment getItem(int position) {
        browserFragment = BrowserFragment.newInstance(fragments.get(position).toString());
        return browserFragment;
    }

    //returns total number of items
    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getPosition(Object object){
        return POSITION_NONE;
    }

    public void addUrl (String url){
        fragments.add(url);
    }

    public void updateViewPager(int position, String url){
        fragments.set(position,url);
    }

    @Override
    public void startUpdate(ViewGroup container){
        super.startUpdate(container);
    }
}
