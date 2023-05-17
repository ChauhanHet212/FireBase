package com.example.firebase.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firebase.R;
import com.example.firebase.databinding.FragmentAddProductBinding;

public class AddProductFragment extends Fragment {

    FragmentAddProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        binding = FragmentAddProductBinding.bind(view);

        return view;
    }
}