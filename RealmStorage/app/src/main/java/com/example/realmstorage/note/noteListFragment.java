package com.example.realmstorage.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.realmstorage.R;
import com.example.realmstorage.databinding.FragmentNoteListBinding;
import com.example.realmstorage.task.TaskObject;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link noteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class noteListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public noteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment noteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static noteListFragment newInstance(String param1, String param2) {
        noteListFragment fragment = new noteListFragment();
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

    private FragmentNoteListBinding binding;
    private Realm realm1;
    private noteObject object = null;
    private RealmResults<noteObject> notelist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding = FragmentNoteListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RealmConfiguration config=new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .name(getString(R.string.app_name)).build();

        realm1=Realm.getInstance(config);

        getNoteList();

        binding.noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
            }
        });

//        registerForContextMenu(binding.noteList);
    }

//    @Override
//    public void onCreateContextMenu(@NonNull  ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        getActivity().getMenuInflater().inflate(R.menu.menu2,menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == R.id.delet){
//            int i = binding.noteList.getSelectedItem().toString().charAt(15);
//            realm1.executeTransaction(transactionRealm->{
//                noteObject noteObject=transactionRealm.where(noteObject.class).equalTo("id", i ).findFirst();
//                noteObject.deleteFromRealm();
//            });
//            getNoteList();
//        }
//        else if (item.getItemId() == R.id.update){
//            realm1.executeTransaction(transactionRealm->{
//                noteObject theUser=transactionRealm.where(noteObject.class).equalTo("id",binding.noteList.getSelectedItem().toString()).findFirst();
//
//                LayoutInflater inflater = this.getLayoutInflater();
//                View view = inflater.inflate(R.layout.dialog1, null);
//                new AlertDialog.Builder(getContext())
//                        .setView(view)
//                        .setPositiveButton("update", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                EditText detail_editText = view.findViewById(R.id.newNoteDetail);
//                                theUser.NoteDetail=detail_editText.getText().toString();
//                                Toast.makeText(getContext(), "User Saved!!!", Toast.LENGTH_SHORT).show();
//                                getNoteList();
//                            }
//                        })
//                        .create()
//                        .show();
//
//            });
//
//            getNoteList();
//        }
//
//        return super.onContextItemSelected(item);
//    }

    private void showAlertDialog(int position) {
        object=notelist.get(position);
        new AlertDialog.Builder(getContext())
                .setTitle("Select Operation")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LayoutInflater inflater = getLayoutInflater();
                        View view = inflater.inflate(R.layout.dialog1, null);
                        new AlertDialog.Builder(getContext())
                                .setView(view)
                                .setPositiveButton("update", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText detail_editText = view.findViewById(R.id.newNoteDetail);
                                        view.findViewById(R.id.TaskId).setEnabled(false);
                                        realm1.executeTransaction(transactionRealm->{
                                            noteObject theUser=transactionRealm.where(noteObject.class).equalTo("Id",object.Id).findFirst();
                                            theUser.NoteDetail=detail_editText.getText().toString();
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
                        noteObject noteObject=transactionRealm.where(noteObject.class).equalTo("Id", object.Id).findFirst();
                        noteObject.deleteFromRealm();
                        });
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
            View view = inflater.inflate(R.layout.dialog1, null);

            new AlertDialog.Builder(getContext())
                    .setView(view)
                    .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EditText detail_editText = view.findViewById(R.id.newNoteDetail);
                            EditText Id_editText = view.findViewById(R.id.newNoteId);
                            noteObject object1 = new noteObject();
                            object1.Id = Id_editText.getText().toString();
                            object1.NoteDetail = detail_editText.getText().toString();
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
        notelist=realm1.where(noteObject.class).findAll();
        ArrayAdapter<noteObject> adapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,notelist);
        binding.noteList.setAdapter(adapter);
    }


}