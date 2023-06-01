package sg.edu.rp.c346.id22022096.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText addtasks;
    Button add;
    Button delete;
    Button clear;
    ListView lvtodo;
    Spinner spinner;

    ArrayList<String> altodo;
    ArrayAdapter<String> aatodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addtasks = findViewById(R.id.edittodo);
        add = findViewById(R.id.additem);
        delete = findViewById(R.id.deleteitem);
        clear = findViewById(R.id.clearitem);
        lvtodo = findViewById(R.id.listViewTodo);
        spinner = findViewById(R.id.spinner);

        altodo = new ArrayList<>();
        aatodo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, altodo);
        lvtodo.setAdapter(aatodo);

        // enhancement 1
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(18); //to set the text size of the spinner
                switch(position) {
                    case 0:
                        addtasks.setHint("enter a task to add");
                        addtasks.setText("");
                        delete.setTextColor(Color.WHITE);
                        delete.setEnabled(false);
                        add.setEnabled(true);
                        addtasks.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                        break;
                    case 1:
                        addtasks.setHint("enter a index to remove task");
                        addtasks.setText("");
                        add.setTextColor(Color.WHITE);
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        addtasks.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(v -> {
            String newtodo = addtasks.getText().toString();
            //check that if task input is empty, error message will be shown to indicate that empty task cannot be added to the list
            if (newtodo.isEmpty()) {
                Toast.makeText(MainActivity.this, "Empty task cannot be added", Toast.LENGTH_LONG).show();
            } else {
                altodo.add(newtodo);
                aatodo.notifyDataSetChanged();
                addtasks.setText(null);
            }
        });

        clear.setOnClickListener(v -> {
            altodo.clear();
            aatodo.notifyDataSetChanged();
        });

        delete.setOnClickListener(v -> {
            String index = addtasks.getText().toString();

            if (index.isEmpty()) {
                Toast.makeText(MainActivity.this, "No index has been entered", Toast.LENGTH_LONG).show();
            } else {
                int indexno = Integer.parseInt(index);
                if (altodo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else if (indexno < 0 || indexno >= altodo.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                } else {
                    altodo.remove(indexno);
                    aatodo.notifyDataSetChanged();
                }
            }

        });

    }
}