package com.lorenzo.abruti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by PC on 10/04/2018.
 */

public abstract class AbstractContainer extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.nav, menu); //create a menu, links it to the menu argument
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_cards:
                startActivity(new Intent(this,ACA.class));
                break;
/*
            case R.id.seek_cards:
                startActivity(new Intent(this, SeekCards_Activity.class));
                break;

            case R.id.sort_classes:
                startActivity(new Intent(this, SeekCards_Activity.class));
                break;
*/
            case R.id.activity_expected:
                startActivity(new Intent(this, DeveFare.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}