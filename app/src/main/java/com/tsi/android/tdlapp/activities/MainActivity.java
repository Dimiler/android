package com.tsi.android.tdlapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tsi.android.tdlapp.R;
import com.tsi.android.tdlapp.TodoItem;
import com.tsi.android.tdlapp.adapters.TodoListAdapter;
import com.tsi.android.tdlapp.database.DbHelper;
import com.tsi.android.tdlapp.preference.PreferencesManager;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    final DbHelper db = new DbHelper(this);
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<TodoItem> todoItems = db.getAllTodoItems();
        final TodoListAdapter adapter = new TodoListAdapter(todoItems, this);

        final ListView lView = (ListView) findViewById(R.id.todo_list);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem item = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DetailsActivity.ITEM, item);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        editText = (EditText) findViewById(R.id.input_text);

        Button button = (Button) findViewById(R.id.add_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() != 0) {
                    TodoItem newItem = new TodoItem(editText.getText().toString(), new Date(), false);
                    adapter.addItem(newItem);
                    db.addTodoItem(newItem);
                    editText.setText("");
                    editText.clearFocus();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        int color = PreferencesManager.getInstance(this).getColorPreference("color");
        editText.setTextColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_color) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dbUpdate(TodoItem todoItem) {
        db.updateItem(todoItem);
    }

    public void dbItemDelete(TodoItem todoItem) {
        db.deleteItem(todoItem);
    }
}
