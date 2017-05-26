package com.tsi.android.tdlapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tsi.android.tdlapp.R;
import com.tsi.android.tdlapp.TodoItem;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    public static final String ITEM = "todo";
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);
        final TodoItem todoItem = getIntent().getExtras().getParcelable(ITEM);
        ((TextView) findViewById(R.id.description_text)).setText(todoItem.getTitle());



        ImageButton share = (ImageButton) findViewById(R.id.share_btn);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharingToSocialMedia(todoItem.getDescription());
            }
        });

        CheckBox checkBox = (CheckBox) findViewById(R.id.element_description_check);
        checkBox.setChecked(todoItem.isChecked());
    }

    public void SharingToSocialMedia(String description) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, description);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
