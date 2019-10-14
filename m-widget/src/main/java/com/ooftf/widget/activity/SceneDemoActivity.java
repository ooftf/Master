package com.ooftf.widget.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.transition.ChangeBounds;
import androidx.transition.Scene;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.widget.R;
import com.ooftf.widget.databinding.ActivitySceneDemoBinding;

@Route(path = RouterPath.Widget.Activity.SCENE_DEMO)
public class SceneDemoActivity extends AppCompatActivity {
    ActivitySceneDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scene_demo);
        Scene scene1 = new Scene(binding.container);
        Scene sceneForLayout = Scene.getSceneForLayout(binding.container, R.layout.scene_demo_2, this);
        Transition transition = new ChangeBounds();
        binding.button.setOnClickListener(v -> TransitionManager.go(sceneForLayout,transition));

    }
}
