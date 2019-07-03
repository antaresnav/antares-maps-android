package com.antaresnav.maps.demo.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.antaresnav.maps.demo.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public abstract class BaseExampleActivity extends AppCompatActivity {

    public static final String EXTRA_SDK_TYPE = "com.antaresnav.maps.demo.SDK_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_demo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewStub contentStub = findViewById(R.id.content_stub);
        contentStub.setLayoutResource(getContentLayoutId());
        contentStub.inflate();

        FloatingActionButton fab = findViewById(R.id.view_source_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSourceCode();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_SDK_TYPE)) {
            switch (intent.getIntExtra(EXTRA_SDK_TYPE, -1)) {
                case ExampleDetails.SDK_TYPE_ANTARES:
                    getSupportActionBar().setTitle(R.string.sdk_type_antares);
                    break;
                case ExampleDetails.SDK_TYPE_GOOGLE:
                    getSupportActionBar().setTitle(R.string.sdk_type_google);
                    break;
            }
        }
    }

    protected abstract int getContentLayoutId();

    private void showSourceCode() {
        View sourceViewContent = getLayoutInflater().inflate(R.layout.content_source_view, null, false);
        final WebView webView = sourceViewContent.findViewById(R.id.web_view);
        final ProgressBar spinner = sourceViewContent.findViewById(R.id.spinner);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                spinner.setVisibility(View.INVISIBLE);
            }
        });
        webView.loadUrl("https://github.com/antaresnav/antares-maps-android/tree/master/app/src/main/java/" + getClass().getName().replace('.', '/') + ".java");

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(sourceViewContent);
        dialog.show();
    }

}