package com.antaresnav.maps.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antaresnav.maps.demo.R;
import com.antaresnav.maps.demo.utils.ItemClickSupport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ExampleAdapter exampleAdapter = new ExampleAdapter(this, Examples.EXAMPLES);
        RecyclerView recyclerView = findViewById(R.id.examples_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(exampleAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                ExampleDetails example = exampleAdapter.getItemAt(position);
                Intent intent = new Intent(MainActivity.this, example.getActivityClass());
                intent.putExtra(BaseExampleActivity.EXTRA_SDK_TYPE, example.getSdkType());
                startActivity(intent);
            }
        });
    }

}
