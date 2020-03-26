package com.example.diarrheainmyarea;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import java.util.ArrayList;

import javax.sql.StatementEvent;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link realtimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class realtimeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<String> l1;
    Spinner spinner;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public realtimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment realtimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static realtimeFragment newInstance(String param1, String param2) {
        realtimeFragment fragment = new realtimeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_realtime, container, false);
        //final SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
        final Button logout = rootView.findViewById(R.id.logoutbtn);
        spinner = rootView.findViewById(R.id.spin);
        createlist();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                System.out.println(item.toString());
                if (item.toString().equals("City Wise")) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(final GoogleMap googleMap) {
                            int zoomin = 10;
                            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            googleMap.clear();


                            CameraPosition googlePlex = CameraPosition.builder()
                                    .target(new LatLng(21.172327, 72.788646))
                                    .zoom(zoomin)
                                    .bearing(0)
                                    .tilt(0)
                                    .build();


                            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));

                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(21.162344, 72.797566))
                                    .title("Surat")).setTag("Surat");


                            /*final DatabaseReference dbareacount = FirebaseDatabase.getInstance().getReference("Area/Surat");
                            dbareacount.addListenerForSingleValueEvent(new ValueEventListener() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                                        // System.out.println(ds.getKey());
                                        String location =ds.child("Location").getValue(String.class);
                                        String loc[] =location.split(",");
                                        googleMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(Double.parseDouble(loc[0]), Double.parseDouble(loc[1])))
                                                .title(ds.getKey())).setTag(ds.getKey());
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
 */
                        }
                    });


                } else {
                    SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(final GoogleMap googleMap) {
                            int zoomin = 13;
                            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                            googleMap.clear();

                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(final Marker marker) {


                                    //final DatabaseReference dbareacount = FirebaseDatabase.getInstance().getReference("Area/Surat");
                                    final DatabaseReference demo = FirebaseDatabase.getInstance().getReference("Area");


                                    demo.addListenerForSingleValueEvent(new ValueEventListener() {

                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String str3 = "";
                                            String key = "";
                                            String date = "";


                                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                if (ds.getKey().equals("Date")) {
                                                    key = ds.getKey();
                                                    for (DataSnapshot dsa : ds.getChildren()) {
                                                        date = dsa.getKey();
                                                        //System.out.println(dsa.child("totalcount").getValue());
                                                        str3 = String.valueOf(dsa.child("totalcount").getValue());
                                                    }

                                                }
                                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                                Date date1 = new Date();
                                                String d = format.format(date1);
                                                //System.out.println(format.format(date1));
                                                //   System.out.println(ds.child(key).getRef());

                                                if ((ds.child(key).child(d).child("totalcount").getValue() != null)) {

                                                    str3 = String.valueOf(ds.child(key).child(d).child("totalcount").getValue());
                                                    //System.out.println(ds.child("25-3-2020").child("totalcount").getValue());
                                                }

                                                for (DataSnapshot dsa : ds.getChildren()) {


                                                    String position = (String) (marker.getTag());
                                                    if (position.equals(dsa.getKey())) {
                                                        List<String> a = new ArrayList<>();
                                                        List<String> b = new ArrayList<>();

                                                        Bottom_Sheet bs = new Bottom_Sheet();
                                                        String str1 = dsa.getKey();
                                                        String str2 = String.valueOf(dsa.child("NumberOfTotalCases").getValue());

                                                        //System.out.println(ds.child(position).getKey());

                                                        for (DataSnapshot dsc : dsa.getChildren()) {
                                                            for (DataSnapshot dsd : dsc.getChildren()) {
                                                                System.out.println(dsd.getKey());
                                                                System.out.println(dsd.getValue());
                                                                System.out.println(String.valueOf(dsd.getKey()));
                                                                a.add(String.valueOf(dsd.getKey()));
                                                                b.add(String.valueOf(dsd.getValue()));


                                                            }

                                                        }


                                                        Bundle b1 = new Bundle();
                                                        b1.putString("title", str1);
                                                        b1.putString("numberofcasesinarea", str2);
                                                        b1.putString("totalcount", str3);
                                                        b1.putSerializable("map1", (Serializable) a);
                                                        b1.putSerializable("map2", (Serializable) b);
                                                        //.putString("map3",a);
                                                        bs.setArguments(b1);
                                                        bs.show(getChildFragmentManager(), "something");
                                                    }
                                                    else{

                                                        DatabaseReference demo = FirebaseDatabase.getInstance().getReference("Area");
                                                        demo.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                String str3 = "";
                                                                String key = "";
                                                                String date = "";
                                                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                                                    if (ds.getKey().equals("Date")) {
                                                                        key = ds.getKey();
                                                                        for (DataSnapshot dsa : ds.getChildren()) {
                                                                            date = dsa.getKey();
                                                                            //System.out.println(dsa.child("totalcount").getValue());
                                                                            str3 = String.valueOf(dsa.child("totalcount").getValue());
                                                                        }

                                                                    }
                                                                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                                                    Date date1 = new Date();
                                                                    String d = format.format(date1);
                                                                    //System.out.println(format.format(date1));
                                                                    //   System.out.println(ds.child(key).getRef());

                                                                    if ((ds.child(key).child(d).child("totalcount").getValue() != null)) {

                                                                        str3 = String.valueOf(ds.child(key).child(d).child("totalcount").getValue());
                                                                        //System.out.println(ds.child("25-3-2020").child("totalcount").getValue());
                                                                    }

                                                                    Toast.makeText(getContext(),"CityCount is:"+str3,Toast.LENGTH_SHORT).show();
                                                                }
                                                                //Toast.makeText(getContext(),"CityCount is:",Toast.LENGTH_SHORT).show();
                                                            }


                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });


                                                    }

                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }

                                    });
                                    return false;
                                }
                            });


                            CameraPosition googlePlex = CameraPosition.builder()
                                    .target(new LatLng(21.172327, 72.788646))
                                    .zoom(zoomin)
                                    .bearing(0)
                                    .tilt(0)
                                    .build();


                            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));

                            final DatabaseReference dbareacount = FirebaseDatabase.getInstance().getReference("Area/Surat");
                            dbareacount.addListenerForSingleValueEvent(new ValueEventListener() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                        // System.out.println(ds.getKey());
                                        String location = ds.child("Location").getValue(String.class);
                                        String loc[] = location.split(",");
                                        googleMap.addMarker(new MarkerOptions()
                                                .position(new LatLng(Double.parseDouble(loc[0]), Double.parseDouble(loc[1])))
                                                .title(ds.getKey())).setTag(ds.getKey());
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "logout btn clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return  rootView;
    }


        /*mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                int zoomin=13;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                googleMap.clear();

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) {


                       //final DatabaseReference dbareacount = FirebaseDatabase.getInstance().getReference("Area/Surat");
                        final DatabaseReference demo = FirebaseDatabase.getInstance().getReference("Area");



                        demo.addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String str3="";
                                String key ="";
                                String date="";


                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    if(ds.getKey().equals("Date")){
                                        key = ds.getKey();
                                        for(DataSnapshot dsa : ds.getChildren()){
                                            date = dsa.getKey();
                                            //System.out.println(dsa.child("totalcount").getValue());
                                            str3 = String.valueOf(dsa.child("totalcount").getValue());
                                        }

                                    }
                                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                    Date date1 = new Date();
                                    String d=format.format(date1);
                                    //System.out.println(format.format(date1));
                                 //   System.out.println(ds.child(key).getRef());

                                    if((ds.child(key).child(d).child("totalcount").getValue() !=null))
                                    {

                                        str3 = String.valueOf(ds.child(key).child(d).child("totalcount").getValue());
                                        //System.out.println(ds.child("25-3-2020").child("totalcount").getValue());
                                    }

                                    for (DataSnapshot dsa : ds.getChildren()) {


                                        String position = (String) (marker.getTag());
                                        if (position.equals(dsa.getKey())) {
                                             List<String> a = new ArrayList<>();
                                             List<String> b = new ArrayList<>();

                                            Bottom_Sheet bs = new Bottom_Sheet();
                                            String str1 = dsa.getKey();
                                            String str2 = String.valueOf(dsa.child("NumberOfTotalCases").getValue());

                                            //System.out.println(ds.child(position).getKey());

                                            for (DataSnapshot dsc : dsa.getChildren()) {
                                                for (DataSnapshot dsd : dsc.getChildren()) {
                                                    System.out.println(dsd.getKey());
                                                    System.out.println(dsd.getValue());
                                                    System.out.println(String.valueOf(dsd.getKey()));
                                                    a.add(String.valueOf(dsd.getKey()));
                                                    b.add(String.valueOf(dsd.getValue()));


                                                }

                                            }


                                            Bundle b1 = new Bundle();
                                            b1.putString("title", str1);
                                            b1.putString("numberofcasesinarea", str2);
                                            b1.putString("totalcount", str3);
                                            b1.putSerializable("map1",(Serializable)a);
                                            b1.putSerializable("map2",(Serializable)b);
                                            //.putString("map3",a);
                                            bs.setArguments(b1);
                                            bs.show(getChildFragmentManager(), "something");
                                        }

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });
                          return false;
                    }
                });


                    CameraPosition googlePlex = CameraPosition.builder()
                            .target(new LatLng(21.172327, 72.788646))
                            .zoom(zoomin)
                            .bearing(0)
                            .tilt(0)
                            .build();



                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));

                final DatabaseReference dbareacount = FirebaseDatabase.getInstance().getReference("Area/Surat");
                dbareacount.addListenerForSingleValueEvent(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           for (DataSnapshot ds: dataSnapshot.getChildren()) {
                           // System.out.println(ds.getKey());
                            String location =ds.child("Location").getValue(String.class);
                            String loc[] =location.split(",");
                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(loc[0]), Double.parseDouble(loc[1])))
                                    .title(ds.getKey())).setTag(ds.getKey());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                }
        });
        return  rootView;
    }
*/
    private void createlist(){
            l1=new ArrayList<>();
            l1.add("Area Wise");
            l1.add("City Wise");
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_dropdown_item,l1);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(parent.getContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


