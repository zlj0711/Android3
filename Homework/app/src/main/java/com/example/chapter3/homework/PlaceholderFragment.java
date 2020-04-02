package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.solver.LinearSystem;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ListResourceBundle;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationView;
    private ListView items;
    private String data[] = {"item1","item2","item3","item4","item5","item6","item7"};
    private static final String TAG = "Fragment3";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        animationView = container.findViewById(R.id.animation_view);
        View view = inflater.inflate(R.layout.fragment_placeholder,container,false);
        items = view.findViewById(R.id.lv_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,data);
        items.setAdapter(adapter);
        items.setAlpha(0f);
        items.setVisibility(View.VISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                Log.d(TAG, "AnimationStart");
                ObjectAnimator outAnimator = ObjectAnimator.ofFloat(animationView,"alpha",1,0);
                outAnimator.setDuration(100);
                outAnimator.start();
//                animationView.animate()
//                        .alpha(0f)
//                        .setDuration(100);//这个会闪退

                ObjectAnimator inAnimator = ObjectAnimator.ofFloat(items,"alpha",0,1);
                inAnimator.setDuration(100);
                inAnimator.start();
//                items.animate()
//                        .alpha(1f)
//                        .setDuration(100)
//                        .setListener(null);//这个正常


            }
        }, 5000);

    }
}
