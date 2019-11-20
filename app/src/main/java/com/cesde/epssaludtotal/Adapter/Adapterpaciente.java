package com.cesde.epssaludtotal.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cesde.epssaludtotal.Modelos.Pacientes;
import com.cesde.epssaludtotal.R;

import java.util.ArrayList;

public class Adapterpaciente  extends BaseAdapter {

    private ArrayList<Pacientes> list;
    private Pacientes model;
    private Context context;

    public Adapterpaciente(ArrayList<Pacientes> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemcito = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          //  itemcito = inflater.inflate(R.layout.item_cliente, viewGroup, false);
        }

      //  TextView tv_item_cliente_nombre = itemcito.findViewById(R.id.tv_item_cliente_nombre);
       // TextView tv_item_cliente_cedula = itemcito.findViewById(R.id.tv_item_cliente_cedula);

        model = list.get(i);

      //  tv_item_cliente_nombre.setText(model.getNombre());
       // tv_item_cliente_cedula.setText(model.getDocumento());

        return itemcito;

    }




}
