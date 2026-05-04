package com.example.lab7_dev.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab7_dev.R;
import com.example.lab7_dev.adapter.CelebrityAdapter;
import com.example.lab7_dev.service.CelebrityManager;

public class MainListActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    private CelebrityAdapter celebrityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        mainRecyclerView = findViewById(R.id.celebrityRecyclerView);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        celebrityAdapter = new CelebrityAdapter(this, CelebrityManager.getUniqueInstance().retrieveAll());
        mainRecyclerView.setAdapter(celebrityAdapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Galerie des Stars");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (celebrityAdapter != null) {
                        celebrityAdapter.getFilter().filter(newText);
                    }
                    return true;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_share) {
            String sharingText = "Découvrez l'application Stars Gallery - La galerie des célébrités !";
            String mimeType = "text/plain";

            ShareCompat.IntentBuilder.from(this)
                    .setType(mimeType)
                    .setChooserTitle("Partager Stars Gallery")
                    .setText(sharingText)
                    .startChooser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}