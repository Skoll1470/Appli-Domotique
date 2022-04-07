package com.example.domotique.ui.volet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.domotique.databinding.FragmentVoletBinding;
import com.example.domotique.ui.volet.VoletViewModel;

public class VoletFragment extends Fragment {

    private FragmentVoletBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VoletViewModel slideshowViewModel =
                new ViewModelProvider(this).get(VoletViewModel.class);

        binding = FragmentVoletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}