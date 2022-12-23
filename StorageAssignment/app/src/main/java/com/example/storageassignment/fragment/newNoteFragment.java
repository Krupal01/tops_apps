package com.example.storageassignment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storageassignment.Dao.noteDao;
import com.example.storageassignment.Database.taskDatabase;
import com.example.storageassignment.R;
import com.example.storageassignment.databinding.FragmentNewNoteBinding;
import com.example.storageassignment.entity.NoteEntity;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link newNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newNoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public newNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newNoteFragment newInstance(String param1, String param2) {
        newNoteFragment fragment = new newNoteFragment();
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

    private FragmentNewNoteBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewNoteBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    private taskDatabase db;
    private noteDao dao;
    private NoteEntity noteEntity;
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = taskDatabase.getInstance(getContext());
        dao = db.noteDao();

        Bundle bundle = getArguments();
        if (bundle != null){
            noteEntity = bundle.getParcelable("note");
        }

        if (noteEntity != null){
            binding.NoteDetail.setText(noteEntity.noteDetail);
        }

        binding.addNote.setOnClickListener(v -> {
            if (noteEntity == null) {
                noteEntity = new NoteEntity();
                noteEntity.noteDetail = binding.NoteDetail.getText().toString();
                dao.insertNote(noteEntity);
                Toast.makeText(getContext(), "hii", Toast.LENGTH_SHORT).show();
            }
            else {
                noteEntity.noteDetail = binding.NoteDetail.getText().toString();
                dao.updateNote(noteEntity);
            }
        });






    }
}