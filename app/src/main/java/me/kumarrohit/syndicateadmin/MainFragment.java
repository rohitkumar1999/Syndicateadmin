package me.kumarrohit.syndicateadmin;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    TextView tvMainStatus ;
     FirebaseDatabase firebaseDatabase ;
    String str ;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false) ;
        tvMainStatus = view.findViewById(R.id.main_status) ;
        DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference() ;
        DatabaseReference reference = databaseReference.child("admin_app");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("check", "onDataChange: "+dataSnapshot);

                str = dataSnapshot.child("is_hni_present").getValue().toString() ;
                Log.d("check", "onDataChange:"+str);
                Log.d("is present", str);
                tvMainStatus.setText(str);



                // Declare adapter and set here

                // OR... adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("LogFragment", "loadLog:onCancelled", databaseError.toException());
            }
        };
        reference.addValueEventListener(valueEventListener);









        return view;
    }


            @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }

}
