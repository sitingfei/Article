package cc.wudoumi.article.moudle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.wudoumi.article.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d("qian", "onCreateView:"+position);
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    public static Fragment newInstance(int position){
        BlankFragment blankFragment = new BlankFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        blankFragment.setArguments(bundle);

        return  blankFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");

        Log.d("Qian", "onCreate:"+position);
    }


    /**
     * 提供当前Fragment的主色调的Bitmap对象,供Palette解析颜色
     *
     * @return
     */
    public static int getBackgroundBitmapPosition(int selectViewPagerItem) {
        return drawables[selectViewPagerItem];
    }

    private int position;
    private static final int[] drawables = { R.mipmap.f, R.mipmap.s, R.mipmap.t, R.mipmap.fo,
            R.mipmap.fi, R.mipmap.fi, R.mipmap.fi, R.mipmap.fi };





    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Qian", "onDestroy:"+position);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Qian", "onDestroyView:"+position);
    }
}
