package martinbzdqsm.com.parallaxscrollimageview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import martinbzdqsm.com.parallaxscrollimageview_master.ParallaxImageView;
import martinbzdqsm.com.parallaxscrollimageview_master.Utils;

/**
 * Created by Martin on 2016/7/11 0011.
 */
public class StaggerGridRecyclerViewAdapter extends RecyclerView.Adapter<StaggerGridRecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "StaggerGrid";

    private RequestManager manager;
    private LayoutInflater mInflater;
    private static final float DEFAULT_PARALLAX_RATIO = 0.2f;
    private int itemWidth;

    public StaggerGridRecyclerViewAdapter(RequestManager manager, LayoutInflater mInflater) {
        this.manager = manager;
        this.mInflater = mInflater;
        itemWidth = Utils.getScreenWidth(mInflater.getContext()) / 2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder;
        viewHolder = new MyViewHolder(
                mInflater.inflate(R.layout.recycler_item, parent, false));
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return ids2.length;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final float imgRatio = ratios[position] - DEFAULT_PARALLAX_RATIO;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth, (int) (itemWidth * imgRatio));
        holder.linearLayout.setLayoutParams(params);//为了防止瀑布流闪动，在重新刷新组件的时候就需要确定好宽高
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.parallaxImageView.getLayoutParams();
        layoutParams.weight = itemWidth;
        layoutParams.height = (int) (itemWidth * ratios[position]);
        holder.parallaxImageView.setLayoutParams(layoutParams);
        manager.load(ids2[position]).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                holder.parallaxImageView.setParallax_Drawable(resource, imgRatio, DEFAULT_PARALLAX_RATIO);
            }
        });
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        ParallaxImageView parallaxImageView;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            linearLayout = (LinearLayout) view.findViewById(R.id.item_linear);
            parallaxImageView = (ParallaxImageView) view.findViewById(R.id.img);
        }
    }

    private int[] ids2 = new int[]{
            R.drawable.test1, R.drawable.test9, R.drawable.test10, R.drawable.test11,
            R.drawable.test12, R.drawable.test13, R.drawable.test14, R.drawable.test15,
            R.drawable.test16, R.drawable.test17, R.drawable.test18, R.drawable.test19,
            R.drawable.test20, R.drawable.test21, R.drawable.test22, R.drawable.test23,
            R.drawable.test24, R.drawable.test25,
            R.drawable.test26, R.drawable.test28, R.drawable.test27, R.drawable.test29,
            R.drawable.test30, R.drawable.test31, R.drawable.test32
    };

    private float[] ratios = new float[]{
            0.9680328f,
            0.7854545f,
            1.3f,
            1.2709193f,
            1.168f,
            1.3f,
            1.131111f,
            1.2983606f,
            1.3419102f,
            1.3019607f,
            1.1333333f,
            1.2297521f,
            1.340041f,
            1.278424f,
            1.5751479f,
            1.3015015f,
            1.2746717f,
            1.2209591f,
            1.0028985f,
            1.0821011f,
            1.3f,
            1.3015015f,
            1.4747f,
            1.3f,
            1.33333f,
    };

}


