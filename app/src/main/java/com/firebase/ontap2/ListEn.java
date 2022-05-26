package com.firebase.ontap2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ontap2.database.EnDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ListEn extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView idListView;
    private java.util.List<En> list;
    static int id;
    static EditText txtname;
    static EditText txtdep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_en);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference("ToDo");
        mDatabase.keepSynced(true);

        idListView = (ListView) findViewById(R.id.list);
        txtname = findViewById(R.id.editTextTextPersonName3);
        txtdep = findViewById(R.id.editTextTextPersonName4);
        Button btn = findViewById(R.id.button3);
        Button btnu = findViewById(R.id.button4);
        Button btnd = findViewById(R.id.button5);

        loadlist();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                En e = new En();
                e.setName(txtname.getText().toString());
                e.setDep(txtdep.getText().toString());

                EnDB.getInstance(getBaseContext()).enDao().insertEn(e);
                mDatabase.setValue(EnDB.getInstance(getBaseContext()).enDao().findAllEnSync());
                loadlist();
            }
        });

        btnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                En e = new En();
                e.setId(id);
                e.setName(txtname.getText().toString());
                e.setDep(txtdep.getText().toString());

                EnDB.getInstance(getBaseContext()).enDao().insertEn(e);
                mDatabase.setValue(EnDB.getInstance(getBaseContext()).enDao().findAllEnSync());
                loadlist();
            }
        });

        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnDB.getInstance(getBaseContext()).enDao().delete(id);
                mDatabase.setValue(EnDB.getInstance(getBaseContext()).enDao().findAllEnSync());
                loadlist();
            }
        });


    }

    private void loadlist() {
        list = new ArrayList<>();

        try {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        En c = postSnapshot.getValue(En.class);
                        EnDB.getInstance(getBaseContext()).enDao().insertEn(c);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception ex){

        }

        list = EnDB.getInstance(getBaseContext()).enDao().findAllEnSync();
        Adap adapter = new Adap(getBaseContext(), R.layout.item, list);
        idListView.setAdapter(adapter);
    }
}