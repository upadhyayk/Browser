package edu.temple.browser;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button goButton;
    EditText editText;
    WebView webView;
    BrowserFragment browserFragment;
    String currentUrl;
    myAdapter myAdapter;
    ViewPager viewPager;
    int totalFragments = 1;
    int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String Home_Page = "http://google.com";

        //set up Toolbar
        goButton = (Button) findViewById(R.id.goButton);
        editText = (EditText) findViewById(R.id.editText);
        webView = (WebView) findViewById(R.id.webView);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        myAdapter = new myAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUrl = editText.getText().toString();
                browserFragment = BrowserFragment.newInstance(currentUrl);
                myAdapter.addUrl(currentUrl);
                myAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(currentFragment);
                currentFragment++;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.web_browser_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        int menuId = menuItem.getItemId();
        if(menuId == R.id.action_new_tab){
            totalFragments++;
            currentUrl = " ";
            editText.setText(currentUrl);
            browserFragment = BrowserFragment.newInstance(currentUrl);
            viewPager.setCurrentItem(currentFragment);
        }else if(menuId == R.id.action_next_tab){
            currentFragment++;
            viewPager.setCurrentItem(currentFragment);
        }else if(menuId == R.id.action_prev_tab){
            currentFragment--;
            viewPager.setCurrentItem(currentFragment);
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
