package com.example.firebase.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityAutoCompleteBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class AutoCompleteActivity extends AppCompatActivity {

    private ActivityAutoCompleteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAutoCompleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Places.initialize(getApplicationContext(),"AIzaSyAub_azx4iViQoXcjZaPHD0yjEJlkREH9M");

        binding.edtSearch.setOnClickListener(v -> {

            List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS);

            Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fieldList)
                    .build(this);
            startActivityForResult(intent,101);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==101 && resultCode==RESULT_OK){
            Place place= Autocomplete.getPlaceFromIntent(data);
            binding.edtSearch.setText(place.getAddress());
            binding.tvLonLat.setText(place.getName());
        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            // TODO: Handle the error.
            Status status = Autocomplete.getStatusFromIntent(data);
            Log.i("status", status.getStatusMessage());
        } else if (resultCode == RESULT_CANCELED) {
            // The user canceled the operation.
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}