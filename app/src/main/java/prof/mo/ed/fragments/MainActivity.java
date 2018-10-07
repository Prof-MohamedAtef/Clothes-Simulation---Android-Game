package prof.mo.ed.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;

import prof.mo.ed.fragments.data.AndroidImageAssets;
import prof.mo.ed.fragments.ui.AndroidMeActivity;
import prof.mo.ed.fragments.ui.BodyPartFragment;
import prof.mo.ed.fragments.ui.MasterListFragment;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTowPane=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout)!=null){
            mTowPane=true;

            Button nextButton=(Button)findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            GridView gridView=(GridView)findViewById(R.id.gridView);
            gridView.setNumColumns(2);

            if (savedInstanceState==null){
                BodyPartFragment headFragment=new BodyPartFragment();
                BodyPartFragment bodyFragment=new BodyPartFragment();
                BodyPartFragment legFragment=new BodyPartFragment();

                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                headFragment.setmListIndex(1);


                getFragmentManager().beginTransaction()
                        .replace(R.id.head_container, headFragment, "frags")
                        .commit();

                getFragmentManager().beginTransaction()
                        .replace(R.id.head_container, bodyFragment, "frags")
                        .commit();


                getFragmentManager().beginTransaction()
                        .replace(R.id.head_container, legFragment, "frags")
                        .commit();

            }
        }else {
            mTowPane=false;
        }
    }

    @Override
    public void OnImageSelected(int position) {
        Toast.makeText(this,"Position = "+position,Toast.LENGTH_SHORT).show();
        int bodyPartNumber=position/12;
        int listIndex=position-12*bodyPartNumber;

        if (mTowPane){
            BodyPartFragment bodyPartFragment=new BodyPartFragment();
            switch (bodyPartNumber){
                case 0:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setmListIndex(listIndex);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.head_container,bodyPartFragment)
                            .commit();
                    break;
                case 1:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                    bodyPartFragment.setmListIndex(listIndex);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyPartFragment)
                            .commit();
                    break;
                case 2:
                    bodyPartFragment.setmImageIds(AndroidImageAssets.getLegs());
                    bodyPartFragment.setmListIndex(listIndex);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, bodyPartFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        }else {
            switch (bodyPartNumber){
                case 0: headIndex=listIndex;
                    break;
                case 1:bodyPartNumber=listIndex;
                    break;
                case 2:legIndex=listIndex;
                    break;
                default:
                    break;
            }

            Bundle b=new Bundle();

            b.putInt("headIndex",headIndex);
            b.putInt("bodyIndex",bodyIndex);
            b.putInt("legIndex",legIndex);

            final Intent intent=new Intent(getApplicationContext(), AndroidMeActivity.class);
            intent.putExtras(b);

            Button nextButton=(Button)findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }
}
