package com.example.shortcutexample;


import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    Button shortcut_one,shortcut_two;
    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_one, container, false);
        shortcut_one=v.findViewById(R.id.shortcutOne);
        shortcut_two=v.findViewById(R.id.shortcutTwo);
        shortcut_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
                    ShortcutManager shortcutManager = getActivity().getSystemService(ShortcutManager.class);
                    ShortcutInfo shortcutOne = new ShortcutInfo.Builder(getActivity(), "shortcutOne")
                            .setShortLabel(getActivity().getResources().getString(R.string.ShortcutOne))
                            .setLongLabel(getActivity().getResources().getString(R.string.ShortcutOne))
                            .setIcon(Icon.createWithResource(getActivity(), R.drawable.one))
                            .setIntents(new Intent[] {
                                    new Intent(getActivity(), MainActivity.class)
                                            .setAction("com.example.shortcutexample.MainActivity.FRAGMENTTWO")
                            })
                            .build();
                    shortcutManager.setDynamicShortcuts(Collections.singletonList(shortcutOne));
                }
                }
        });
        shortcut_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
                    ShortcutManager shortcutManager = getActivity().getSystemService(ShortcutManager.class);
                    ShortcutInfo shortcutTwo = new ShortcutInfo.Builder(getActivity(), "shortcutTwo")
                            .setShortLabel(getActivity().getResources().getString(R.string.ShortcutTwo))
                            .setLongLabel(getActivity().getResources().getString(R.string.ShortcutTwo))
                            .setIcon(Icon.createWithResource(getActivity(), R.drawable.two))
                            .setIntents(new Intent[] {
                                    new Intent(getActivity(), MainActivity.class)
                                            .setAction("com.example.shortcutexample.MainActivity.FRAGMENTTHREE")
                            })
                            .build();
                    List<ShortcutInfo> getShortcut=shortcutManager.getDynamicShortcuts();
                    if(getShortcut.size()>0){
                        ShortcutInfo shortcutOne=getShortcut.get(0);
                        getShortcut.add(shortcutOne);
                        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcutOne,shortcutTwo));
                    }else{
                        shortcutManager.setDynamicShortcuts(Collections.singletonList(shortcutTwo));
                    }

                }
            }
        });

        // To remove any Shortcut Dynamically you can use
        
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getActivity().getSystemService(ShortcutManager.class);
                List<String> disableId=new ArrayList<>();
                disableId.add("shortcutOne");
                shortcutManager.disableShortcuts(disableId);
        }*/
        return v;
    }

}
