package pt.com.ua.foodoncampus;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

import org.json.JSONException;

import java.util.ArrayList;

public class CanteensList extends FragmentActivity implements ItemsListFragment.OnItemSelectedListener {
    private boolean isTwoPane = false;
    private String jsonResults;
    private ParserEmentas parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_list);
        parser = new ParserEmentas();
        jsonResults = parser.retrieveMenu();
        try {
            String[] canteens = parser.getCanteens(jsonResults);
            for (int i = 0; i <canteens.length; i++) {
                Item.addItem(canteens[i], parser.getMenusDataFromJson(jsonResults, canteens[i]));
            }
        } catch (JSONException e) {
        }
        determinePaneLayout();
    }

    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
            ItemsListFragment fragmentItemsList =
                    (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.all);
            fragmentItemsList.setActivateOnItemClick(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public void onItemSelected(Item item) {
        if (isTwoPane) { // single activity with list and detail
            // Replace frame layout with correct detail fragment
            ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(item);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItem);
            ft.commit();
        } else { // separate activities
            // launch detail activity using intent
            Intent i = new Intent(this, ItemDetailActivity.class);
            i.putExtra("item", item);
            startActivity(i);
        }
    }
}
