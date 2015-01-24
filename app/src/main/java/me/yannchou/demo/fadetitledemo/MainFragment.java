package me.yannchou.demo.fadetitledemo;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * @author YannChou(周彦斌)
 * @mail zhouyanbin1029@gmail.com
 * @time 15/1/24.18:15
 */
public class MainFragment extends Fragment {

    private LinearLayout titleLayout;
    private ImageView backgroundView;


    private ParallaxScrollView parallaxScrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container,
                false);
        init(rootView);
        addListener();
        return rootView;
    }

    private void addListener() {
        //滑动title变色监听
        parallaxScrollView.setOnTranslateListener(new ParallaxScrollView.OnTranslateListener() {

            @Override
            public void onTranslate(int percent) {
                if (percent < 0) {
                    percent = 0;
                }
                if (percent > 100) {
                    percent = 100;
                }
                ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.light_blue));
                int alpha = (int) (255 * (percent / 100f));
                drawable.setAlpha(alpha);
                titleLayout.setBackgroundDrawable(null);
                titleLayout.setBackgroundDrawable(drawable);

            }
        });

        parallaxScrollView.setOnSwipeListener(new ParallaxScrollView.OnSwipeListener() {

            @Override
            public void onSwiping(float deltaY) {

            }

            @Override
            public void onSwipeUp() {

            }
        });
    }

    // 取到title渐变色
    public Drawable getTransLateColor() {
        int percent = parallaxScrollView.getParallaxPercent();
        if (percent < 0) {
            percent = 0;
        }
        if (percent > 100) {
            percent = 100;
        }
        ColorDrawable drawable = new ColorDrawable(getResources().getColor(R.color.light_blue));
        int alpha = (int) (255 * (percent / 100f));
        drawable.setAlpha(alpha);
        return drawable;
    }

    @SuppressLint("NewApi")
    private void init(View rootView) {
        // 上划效果和title颜色渐变
        titleLayout = (LinearLayout) rootView.findViewById(R.id.home_title);
        parallaxScrollView = (ParallaxScrollView) rootView
                .findViewById(R.id.scrollView);
        backgroundView = (ImageView) rootView.findViewById(R.id.imageView);
        parallaxScrollView.setVerticalScrollBarEnabled(false);
        parallaxScrollView.setVerticalFadingEdgeEnabled(false);
        parallaxScrollView.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
        parallaxScrollView.setParallaxView(backgroundView);
        parallaxScrollView.requestChildFocus(null, null);
    }


}
