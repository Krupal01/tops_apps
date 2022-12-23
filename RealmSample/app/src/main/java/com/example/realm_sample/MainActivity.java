package com.example.realm_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.realm_sample.databinding.ActivityMainBinding;

import java.lang.reflect.Array;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Realm realm;
    private User theUser=null;
    private RealmResults<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        RealmConfiguration config=new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .name(getString(R.string.app_name)).build();

        realm=Realm.getInstance(config);

        binding.btnSubmit.setOnClickListener(v->{
            if(theUser==null) {
                insertUser();
            }else{
                updateUser(theUser.id);
            }
        });

//        binding.btnGetUsers.setOnClickListener(v->{
//            getAllUsers();
//        });

        getAllUsers();

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
            }
        });
    }

    private void showAlertDialog(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Select Operation")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        theUser=userList.get(position);
                        binding.etId.setText(String.valueOf(theUser.id));
                        binding.etFirstName.setText(theUser.firstName);
                        binding.etLastName.setText(theUser.lastName);
                        binding.etEmail.setText(theUser.email);

                        binding.etId.setEnabled(false);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        theUser=userList.get(position);
                        deleteUser(theUser.id);
                    }
                })
                .create().show();
    }

    private void getAllUsers() {
        userList=realm.where(User.class).findAll();
        ArrayAdapter<User> adapter=
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,userList);
        binding.listView.setAdapter(adapter);
    }

    private void insertUser() {
        User theUser=new User();
        theUser.id=Integer.parseInt(binding.etId.getText().toString());
        theUser.firstName=binding.etFirstName.getText().toString();
        theUser.lastName=binding.etLastName.getText().toString();
        theUser.email=binding.etEmail.getText().toString();

        realm.executeTransaction(transactionRealm  -> {
            transactionRealm.insert(theUser);
            Toast.makeText(this, "User Saved!!!", Toast.LENGTH_SHORT).show();
        });
        reset();
        getAllUsers();
    }

    private void updateUser(int _id){
        realm.executeTransaction(transactionRealm->{
            User theUser=transactionRealm.where(User.class).equalTo("id",_id).findFirst();
            theUser.firstName=binding.etFirstName.getText().toString();
            theUser.lastName=binding.etLastName.getText().toString();
            theUser.email=binding.etEmail.getText().toString();
        });
        reset();
        getAllUsers();
    }

    private void deleteUser(int _id ){
        realm.executeTransaction(transactionRealm->{
            User theUser=transactionRealm.where(User.class).equalTo("id",_id).findFirst();
            theUser.deleteFromRealm();
        });
        reset();
        getAllUsers();
    }

    private void reset() {
        theUser=null;
        binding.etId.setText("");
        binding.etFirstName.setText("");
        binding.etLastName.setText("");
        binding.etEmail.setText("");
        binding.etId.setEnabled(true);
    }
}