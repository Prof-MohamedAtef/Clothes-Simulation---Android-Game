package prof.mo.ed.fragments.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;

import prof.mo.ed.fragments.MainActivity;
import prof.mo.ed.fragments.data.AndroidImageAssets;

/**
 * Created by Prof-Mohamed Atef on 10/7/2018.
 */

public class MasterListFragment extends Fragment {

    public MasterListFragment() {

    }

    GridView gridView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_master_list,container,false);
        gridView=rootView.findViewById(R.id.gridView);
        MasterListAdapter mAdapter=new MasterListAdapter(getActivity(), AndroidImageAssets.getAll());
        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.OnImageSelected(position);
            }
        });
        return rootView;
    }

    public interface OnImageClickListener{
        void OnImageSelected(int position);
    }

    OnImageClickListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback= (OnImageClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must Implement onImageClickListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallback= (OnImageClickListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must Implement onImageClickListener");
        }
    }
}