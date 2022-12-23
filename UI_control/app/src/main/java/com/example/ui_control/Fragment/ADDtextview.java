package com.example.ui_control.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentADDtextviewBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ADDtextview#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ADDtextview extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ADDtextview() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ADDtextview.
     */
    // TODO: Rename and change types and number of parameters
    public static ADDtextview newInstance(String param1, String param2) {
        ADDtextview fragment = new ADDtextview();
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

    private FragmentADDtextviewBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout mainLayout=new LinearLayout(getContext());
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        TextView textView= new TextView(getContext());
        textView.setHint("error: if any");
        mainLayout.addView(textView);

        EditText n = new EditText(getContext());
        n.setHint("enten number");
        n.setTextSize(20);
        mainLayout.addView(n);


        Button btn = new Button(getContext());
        btn.setText("addtextview");
        btn.setOnClickListener(v -> {
            String i = n.getText().toString();
            int s=0 , j=1 ;

            try{
                if(!i.equals("")) {
                    s = Integer.parseInt(i);
                }
                while(j<=s){
                  TextView textView1 = new TextView(getContext());
                  textView1.setText("textview"+j);
                  mainLayout.addView(textView1);
                  j++;
                }
            }
            catch (Exception e){
                textView.setText(textView.getText().toString() +e.toString());
            }

        });
        mainLayout.addView(btn);


        return mainLayout;

    }


}