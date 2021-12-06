package com.example.proymovilii;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proymovilii.Adapters.WishList;
import com.example.proymovilii.Models.WishListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etEnterWish;
    private Button btnPublish;
    private RecyclerView rvWish;
    private WishList wishList;
    private View view;

    private WishListModel wish;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<WishListModel> arraywishList;
    private String path = "wish";

    public WishListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishListFragment newInstance(String param1, String param2) {
        WishListFragment fragment = new WishListFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        etEnterWish = view.findViewById(R.id.etEnterName);
        btnPublish = view.findViewById(R.id.btnPublish);

        rvWish = view.findViewById(R.id.rvWish);
        rvWish.setLayoutManager(new GridLayoutManager(getContext(), 1));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(path);

        wish = new WishListModel();
        arraywishList = new ArrayList<WishListModel>();
        rvWish.setAdapter(wishList);
        uptadeRV();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //limpiar el arraylist
                arraywishList = new ArrayList<WishListModel>();
                //Lo que tenemos que hacer es leer todos los datos porque este metodo se invoca cuando se determina que se actualizo el database(automatico)
                //Usaremos un foreach
                for(DataSnapshot snapshot1Aux: snapshot.getChildren()){
                    WishListModel petAuxiliar = snapshot1Aux.getValue(WishListModel.class);
                    arraywishList.add(petAuxiliar);
                }
                uptadeRV();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to read values", Toast.LENGTH_LONG).show();

            }
        });

        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String wishString = etEnterWish.getText().toString().trim();
                    if (wishString.equals("")){
                        etEnterWish.setError("Please insert a wish");
                        etEnterWish.requestFocus();
                        return;
                    }

                    //Verificar si es necesario lo siguiente
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference(path);
                    //En este ejemplo vamos a crear un key para el "Registro":
                    String keyString = databaseReference.push().getKey();
                    wish = new WishListModel(keyString, wishString);
                    //Añora podemos realizar la insercion del objeto pet:
                    databaseReference.child(keyString).setValue(wish);
                    Toast.makeText(getContext(), "Succesfull wish saved", Toast.LENGTH_LONG).show();
                    cleanForm();

                }catch (Exception ex){
                    Toast.makeText(getContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;
    }

    private void uptadeRV() {
        wishList = new WishList(arraywishList);
        rvWish.setAdapter(wishList);
    }

    private void cleanForm() {
        etEnterWish.setText("");
    }
}