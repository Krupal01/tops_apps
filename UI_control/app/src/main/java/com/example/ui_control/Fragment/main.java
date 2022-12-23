package com.example.ui_control.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ui_control.A6_cart.shopFragment;
import com.example.ui_control.Gmail.GmailHomeFragment;
import com.example.ui_control.ImageGrid.imageFragment;
import com.example.ui_control.ListItems.IpNameFragment;
import com.example.ui_control.ObjectPassActivity2;
import com.example.ui_control.R;
import com.example.ui_control.Whatapp.WpActivity;
import com.example.ui_control.databinding.FragmentMainBinding;
import com.example.ui_control.dialog.AlertDialog_BlankFragment;
import com.example.ui_control.dialog.CustomToastFragment;
import com.example.ui_control.dialog.Datepeker_Fragment;
import com.example.ui_control.dialog.DialogOnListFragment;
import com.example.ui_control.dialog.Dialog_to_toast_Fragment;
import com.example.ui_control.dialog.TwoDateFragment;
import com.example.ui_control.dialog.multipleSelectListDialogFragment;
import com.example.ui_control.employee.employeeFragment;
import com.example.ui_control.inputFormEmployee.formFragment;
import com.example.ui_control.inputNumber;
import com.example.ui_control.localHTMLActivity2;
import com.example.ui_control.main_Recycler.pageAdapter;
import com.example.ui_control.main_Recycler.pages;
import com.example.ui_control.menu.ContextMenuOnListFragment;
import com.example.ui_control.menu.LogOutFragment;
import com.example.ui_control.menu.RecyclerPopupFragment;
import com.example.ui_control.recycler.demoFragment;
import com.example.ui_control.toolbar.toolbarActivity;
import com.example.ui_control.toolbar.toolbarFragment;
import com.example.ui_control.webView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main extends Fragment implements pageAdapter.pagesClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment main.
     */
    // TODO: Rename and change types and number of parameters
    public static main newInstance(String param1, String param2) {
        main fragment = new main();
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

    private FragmentMainBinding binding;
    private ArrayList<pages> page;
    private pageAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding = FragmentMainBinding.inflate(inflater , container , false);
      setHasOptionsMenu(true);
      return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
                    builder.setTitle("Exit");
                    builder.setMessage("are you sure");
                    builder.setPositiveButton("yes", (dialog, which) -> {
                        getActivity().finish();
                    });
                    builder.setNegativeButton("no", (dialog, which) -> {
                        Toast.makeText(getContext(),"nice decision" , Toast.LENGTH_SHORT).show();
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                return false;
            }
        });

//step1
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//step2: make class and adapter for recyler view (arraylist is key vlaue sturture so we need to make class)
        page = new ArrayList<pages>();
        page.add(new pages("radio", new radio(),null));
        page.add(new pages("webView", null, webView.class));
        page.add(new pages("radiocolor", new radioColor(),null));
        page.add(new pages("reverse", new Reverse(),null));
        page.add(new pages("textSize", new textSize(),null));
        page.add(new pages("inputNumber", null , inputNumber.class));
        page.add(new pages("seekbarColor", new seekbarColor(),null));
        page.add(new pages("spinner",new spinner(),null));
        page.add(new pages("checkConnectivity" , new checkConnectivity() , null));
        page.add(new pages("adapter", new Adapter(),null));
        page.add(new pages("simple_adapter", new simple_Adapter(),null));
        page.add(new pages("employeeSimpleAdapter", new Employee_Detail_SimpleAdapter(),null));
        page.add(new pages("employee",new employeeFragment(),null));
        page.add(new pages("demo",new demoFragment() , null));
        page.add(new pages("image",new imageFragment(),null));
        page.add(new pages("whatapp",null, WpActivity.class));
        page.add(new pages("shop", new shopFragment(),null));
        page.add(new pages("horizontal", new horizontalScrollFragment(),null));
        page.add(new pages("form2", new formFragment(),null));
        page.add(new pages("nameList", new IpNameFragment(),null));
        page.add(new pages("AlertDialoge", new AlertDialog_BlankFragment(),null));
        page.add(new pages("ListDialoge", new DialogOnListFragment(),null));
        page.add(new pages("multipleListDialoge", new multipleSelectListDialogFragment(),null));
        page.add(new pages("Date pikcer", new Datepeker_Fragment(),null));
        page.add(new pages("custom Dialog", new Dialog_to_toast_Fragment(),null));
        page.add(new pages("custom Toast", new CustomToastFragment(),null));
        page.add(new pages("calculate days", new TwoDateFragment(),null));
        page.add(new pages("profile with menu", new LogOutFragment(),null));
        page.add(new pages("contextMenu with list", new ContextMenuOnListFragment(),null));
        page.add(new pages("Recycler menu", new RecyclerPopupFragment(),null));
        page.add(new pages("Gmail", new GmailHomeFragment(),null));
        page.add(new pages("webViewAssets", null, localHTMLActivity2.class));
        page.add(new pages("object pass to webview", null, ObjectPassActivity2.class));
        page.add(new pages("table layout", new tableLayoutFragment(),null));
        page.add(new pages("toolbar", new toolbarFragment(),null));
        page.add(new pages("toolbar Activity", null, toolbarActivity.class));
        page.add(new pages("Image Around Text", new ImageAroundTextFragment(),null));
        page.add(new pages("time picker", new TimePickerFragment(),null));
       



//step3 give element from main to adpater
        adapter = new pageAdapter(this);
        adapter.setpage(page);

//step4
        binding.recyclerView.setAdapter(adapter);
    }




    //step5
    private void changeActivity(Class<? extends Activity> theClass){
        Intent intent=new Intent(getContext(), theClass);
        startActivity(intent);
    }

    private void switchFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,fragment)
                .addToBackStack(main.class.getName())
                .commit();
    }




    @Override
    public void onClickListener(pages pages) {
        if(pages.getFragment()==null){
            changeActivity(pages.getActivityClass());
        }else{
            switchFragment(pages.getFragment());
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.manu2,menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Log_out){
            getActivity().setTitle("Log out");
        }
        else if(item.getItemId()==R.id.profile) {
            getActivity().setTitle("profile");
        }
        else if (item.getItemId()==R.id.search){

            SearchView searchView = (SearchView) item.getActionView();

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    // inside on query text change method we are
                    // calling a method to filter our recycler view.
                    filter(newText);
                    return false;
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<pages> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (pages item : page) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }
}
