package com.home.jss.keycard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListPasswordAdapter extends BaseAdapter
{
    private ArrayList<String> mPasswordsList;
    private Context mContext;

    public ListPasswordAdapter(Context context, ArrayList<String> passwordsList)
    {
        mPasswordsList = passwordsList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mPasswordsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPasswordsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.password_list_item, null);
        }

        TextView passwordKey = (TextView) convertView.findViewById(R.id.textViewKey);
        TextView password = (TextView) convertView.findViewById(R.id.textViewPassword);

        passwordKey.setText(String.valueOf(position + 1));
        password.setText(mPasswordsList.get(position));

        return convertView;
    }
}
