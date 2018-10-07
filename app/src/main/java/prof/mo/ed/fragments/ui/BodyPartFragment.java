package prof.mo.ed.fragments.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

import prof.mo.ed.fragments.data.AndroidImageAssets;

/**
 * Created by Prof-Mohamed Atef on 10/7/2018.
 */

public class BodyPartFragment extends android.app.Fragment{


    public static final String TAG="BodyPartFragment";

    public static final String IMAGE_ID_LIST="image_ids";
    public static final String LIST_INDEX="list_index";



    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(IMAGE_ID_LIST,(ArrayList<Integer>) mImageIds);
        outState.putInt(LIST_INDEX,mListIndex);
    }

    List<Integer> mImageIds;

    public void setmListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    int mListIndex;

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState!=null){
            mImageIds=savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex=savedInstanceState.getInt(LIST_INDEX);
        }
        View rootView=inflater.inflate(R.layout.fragment_body_part,container,false);
        final ImageView imageView=(ImageView)rootView.findViewById(R.id.body_part_image_view);
        if(mImageIds!=null){
            imageView.setImageResource(mImageIds.get(mListIndex));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex<mImageIds.size()-1){
                        mListIndex++;
                    }else {
                        mListIndex=0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        }else {
            Log.v(TAG,"Null List of imageIDs");
        }
        return rootView;
    }
}