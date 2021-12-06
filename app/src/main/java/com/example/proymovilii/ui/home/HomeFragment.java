package com.example.proymovilii.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proymovilii.R;
import com.example.proymovilii.Visualizador_ChainSawMan;
import com.example.proymovilii.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ImageView ivChainsawMan, ivShinguekiNoKyojin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ivShinguekiNoKyojin = root.findViewById(R.id.ivShinguekiNoKyojin);
        ivChainsawMan = root.findViewById(R.id.ivChainsawMan);

        ivChainsawMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Visualizador_ChainSawMan.class);
                startActivity(intent);            }
        });

        return root;
    }

    public void Manga1(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}