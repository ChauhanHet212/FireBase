package com.example.firebase.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebase.R;
import com.example.firebase.StartActivity;
import com.example.firebase.databinding.FragmentAddProductBinding;

public class AddProductFragment extends Fragment {

    FragmentAddProductBinding binding;
    String method;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.bind(inflater.inflate(R.layout.fragment_add_product, container, false));

        method = StartActivity.preferences.getString("method", "h");
        if (method.equals("google")){
            binding.card.setCardBackgroundColor(getResources().getColor(R.color.light_green));
            binding.addBtn.setBackgroundColor(getResources().getColor(R.color.light_green));
        } else if (method.equals("email")){
            binding.card.setCardBackgroundColor(getResources().getColor(R.color.light_blue));
            binding.addBtn.setBackgroundColor(getResources().getColor(R.color.light_blue));
        } else if (method.equals("phone")){
            binding.card.setCardBackgroundColor(getResources().getColor(R.color.light_yellow));
            binding.addBtn.setBackgroundColor(getResources().getColor(R.color.light_yellow));
        }

        return binding.getRoot();
    }
}