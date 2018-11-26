package com.gunar.startupweekend.fragments;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunar.startupweekend.MainActivity;
import com.gunar.startupweekend.R;
import com.gunar.startupweekend.model.HomeResponse;
import com.gunar.startupweekend.remote.ApiService;
import com.gunar.startupweekend.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Ver.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Ver extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private ApiService mAPIService;
    private List<HomeResponse> soliList = new ArrayList<>();
    private View view;
    private String nombre="", ingrediente="", preparacion="";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ver, container, false);
        view = v;
        if (savedInstanceState == null) {
            Bundle extras = this.getArguments();
            if(extras != null) {
                nombre = extras.getString("nombre");
                ingrediente = extras.getString("ingrediente");
                preparacion = extras.getString("preparacion");
            }
        }

        TextView ingredientes = (TextView) v.findViewById(R.id.ingredientes);
        ingrediente = ingrediente.substring(1, ingrediente.length()-1);
        ingrediente = ingrediente.replace("'", "");
        ingrediente = ingrediente.replace(",", "\n");

        ingredientes.setText(ingrediente);
        loadData();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    TextView uno, dos, tres;
    ImageView unoI, dosI, tresI;


    private void loadData(){

        mAPIService = ApiUtils.getApiServices();
        Call<ArrayList<HomeResponse>> respuesta = mAPIService.llamarGunar(nombre);

        respuesta.enqueue(new Callback<ArrayList<HomeResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeResponse>> call, Response<ArrayList<HomeResponse>> response) {
                if(response.isSuccessful()){
                    try {

                        soliList.addAll(response.body()) ;

                        uno = (TextView) view.findViewById(R.id.nombre1);
                        unoI = (ImageView) view.findViewById(R.id.link1);
                        uno.setText(soliList.get(0).getNombre());


                        byte[] decodedString = Base64.decode(soliList.get(0).getLink(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                        unoI.setImageBitmap(decodedByte);




                        dos = (TextView) view.findViewById(R.id.nombre2);
                        dosI = (ImageView) view.findViewById(R.id.link2);
                        dos.setText(soliList.get(1).getNombre());

                        byte[] decodedString2 = Base64.decode(soliList.get(1).getLink(), Base64.DEFAULT);
                        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);

                        dosI.setImageBitmap(decodedByte2);


                        tres = (TextView) view.findViewById(R.id.nombre3);
                        ImageView tresI = (ImageView) view.findViewById(R.id.link3);
                        tres.setText(soliList.get(2).getNombre());

                        byte[] decodedString3 = Base64.decode(soliList.get(2).getLink(), Base64.DEFAULT);
                        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);

                        tresI.setImageBitmap(decodedByte3);


                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {

                            }
                        }, 1500);

//                        Log.v("resultato", "LOGS" + datas.size());
//
//                        for (int i = 0; i < datas.size(); i++) {
//                            HomeResponse x = new HomeResponse(datas.get(i).getNombre(), datas.get(i).getLink());
//                            soliList.add(x);
//                        }



                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeResponse>> call, Throwable t) {
            }
        });
    }

}
