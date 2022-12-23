package com.example.realmstorage.taska;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmstorage.R;
import com.example.realmstorage.databinding.FragmentAtaskBinding;
import com.example.realmstorage.note.noteObject;
import com.example.realmstorage.task.TaskObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AtaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AtaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AtaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AtaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AtaskFragment newInstance(String param1, String param2) {
        AtaskFragment fragment = new AtaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentAtaskBinding binding;
    private Realm realm1;
    private AtaskObject object = null;
    private RealmResults<AtaskObject> tasklist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding = FragmentAtaskBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RealmConfiguration config=new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .name(getString(R.string.app_name)).build();

        realm1= Realm.getInstance(config);

        getNoteList();

        binding.taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
            }
        });

    }



    private void showAlertDialog(int position) {
        object=tasklist.get(position);
        new AlertDialog.Builder(getContext())
                .setTitle("Select Operation")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.dialog2, null);
                        new AlertDialog.Builder(getContext())
                                .setView(view)
                                .setPositiveButton("update", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText etDetail = view.findViewById(R.id.TaskDetail);
                                        EditText etDate = view.findViewById(R.id.TaskDate);
                                        EditText etPriority = view.findViewById(R.id.TaskPriority);

                                        view.findViewById(R.id.TaskId).setEnabled(false);
                                        realm1.executeTransaction(transactionRealm->{
                                            AtaskObject theUser=transactionRealm.where(AtaskObject.class).equalTo("Id",object.Id).findFirst();
                                            theUser.TaskDetail=etDetail.getText().toString();
                                            theUser.date = etDate.getText().toString();
                                            theUser.priority = etPriority.getText().toString();
                                        });

                                        Toast.makeText(getContext(), "User Saved!!!", Toast.LENGTH_SHORT).show();
                                        getNoteList();
                                    }
                                })
                                .create()
                                .show();

                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm1.executeTransaction(transactionRealm->{
                            AtaskObject noteObject=transactionRealm.where(AtaskObject.class).equalTo("Id", object.Id).findFirst();
                            noteObject.deleteFromRealm();
                        });
                        getNoteList();
                    }
                })
                .setNeutralButton("complete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        realm1.executeTransaction(transactionRealm->{
                            AtaskObject theUser=transactionRealm.where(AtaskObject.class).equalTo("Id",object.Id).findFirst();
                            theUser.priority = "completed";
                        });
                        Toast.makeText(getContext(),"task was completed", Toast.LENGTH_SHORT).show();
                        getNoteList();
                    }
                })
                .create().show();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu1,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.NewNote){

            LayoutInflater inflater = this.getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog2, null);

            new AlertDialog.Builder(getContext())
                    .setView(view)
                    .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText etDetail = view.findViewById(R.id.TaskDetail);
                            EditText etId = view.findViewById(R.id.TaskId);
                            EditText etPriority = view.findViewById(R.id.TaskPriority);
                            EditText etDate = view.findViewById(R.id.TaskDate);

                            AtaskObject object1 = new AtaskObject();
                            object1.Id = etId.getText().toString();
                            object1.TaskDetail = etDetail.getText().toString();
                            object1.date = etDate.getText().toString();
                            object1.priority = etPriority.getText().toString();
                            realm1.executeTransaction(transactionRealm  -> {
                                transactionRealm.insert(object1);
                                Toast.makeText(getContext(), "User Saved!!!", Toast.LENGTH_SHORT).show();
                            });
                            getNoteList();
                        }
                    })
                    .create()
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void getNoteList() {
        tasklist=realm1.where(AtaskObject.class).findAll();
        ArrayAdapter<AtaskObject> adapter= new ArrayAdapter<AtaskObject>(getContext(), android.R.layout.simple_list_item_1,tasklist){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position,convertView,parent);
                if(tasklist!=null){
                    if (tasklist.get(position).priority.equals("high")){
                        view.setBackgroundColor(Color.RED);
                    }else if (tasklist.get(position).priority.equals("low")){
                        view.setBackgroundColor(Color.BLUE);
                    }else if (tasklist.get(position).priority.equals("very low")){
                        view.setBackgroundColor(Color.GREEN);
                    }else if (tasklist.get(position).priority.equals("completed")){
                        view.setBackgroundColor(Color.GRAY);
                    }
                }

                return super.getView(position, convertView, parent);
            }
        };
        binding.taskList.setAdapter(adapter);
    }


}
