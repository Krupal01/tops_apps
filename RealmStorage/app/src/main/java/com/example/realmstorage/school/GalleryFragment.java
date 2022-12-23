package com.example.realmstorage.school;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmstorage.R;
import com.example.realmstorage.databinding.FragmentGalleryBinding;
import com.example.realmstorage.note.noteObject;

import org.jetbrains.annotations.NotNull;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
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

    private FragmentGalleryBinding binding;
    private Realm realm1;
    private ImageObject object = null;
    private RealmResults<ImageObject> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding = FragmentGalleryBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RealmConfiguration config=new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .name(getString(R.string.app_name)).build();

        realm1=Realm.getInstance(config);

        getAllImage();

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
                            ImageObject object1 = new ImageObject();
                            Id_editText.setVisibility(view.GONE);
                            object1.path = detail_editText.getText().toString();
                            realm1.executeTransaction(transactionRealm  -> {
                                transactionRealm.insert(object1);
                                Toast.makeText(getContext(), "User Saved!!!", Toast.LENGTH_SHORT).show();
                            });
                            getAllImage();
                        }
                    })
                    .create()
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void getAllImage() {
        imageList=realm1.where(ImageObject.class).findAll();
        ArrayAdapter<ImageObject> adapter= new ArrayAdapter<ImageObject>(getContext(), android.R.layout.simple_list_item_1,imageList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position,convertView,parent);
                view.setBackground(Drawable.createFromPath(imageList.get(position).path));
                return super.getView(position, convertView, parent);
            }
        };
        binding.ImageList.setAdapter(adapter);
    }
}