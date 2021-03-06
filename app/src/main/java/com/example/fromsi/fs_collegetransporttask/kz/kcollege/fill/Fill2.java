package com.example.fromsi.fs_collegetransporttask.kz.kcollege.fill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.fromsi.fs_collegetransporttask.R;
import com.example.fromsi.fs_collegetransporttask.kz.kcollege.OnDataPass;

import java.util.ArrayList;

public class Fill2 extends Fragment implements View.OnClickListener {
    private int index1, index2;
    private ArrayList<EditText> editTexts1;
    private int index = 0;
    private LinearLayout.LayoutParams params;
    private LinearLayout ll;
    private Button button;
    private ViewGroup root;
    private OnDataPass mDataPasser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_fill2, container, false);
        final int scale = (int) getActivity().getResources().getDimension(R.dimen.norm);
        index1 = 0;
        ll = root.findViewById(R.id.llmain);
        editTexts1 = new ArrayList<>();
        params = new LinearLayout.LayoutParams(scale, LinearLayout.LayoutParams.MATCH_PARENT);
        button = root.findViewById(R.id.start);
        button.setOnClickListener(this);
        for (int i = 0; i < getActivity().getIntent().getIntExtra("np2", 2); i++) {
            index1++;
            index2 = 0;
            LinearLayout linearLayout = new LinearLayout(root.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(params);
            for (int j = getActivity().getIntent().getIntExtra("np", 2) * i;
                 j < getActivity().getIntent().getIntExtra("np", 2) * (i + 1); j++) {
                EditText editText;
                editText = new EditText(getActivity());
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
                editText.setTextSize(getActivity().getResources().getDimension(R.dimen.textsize));
                index2++;
                editText.setHint("C" + index2 + index1);
                editTexts1.add(editText);
                linearLayout.addView(editTexts1.get(j));
            }
            ll.addView(linearLayout);
        }
        return root;
    }

    @Override
    public void onClick(View view) {
        int[][] arryaOt;
        int n1 = getActivity().getIntent().getIntExtra("np", 2);
        int n2 = getActivity().getIntent().getIntExtra("np2", 2);
        if (n1 == n2) {
            arryaOt = new int[n1][n2];
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < n2; j++) {
                    arryaOt[i][j] = Integer.parseInt(editTexts1.get(index).getText().toString());
                    index++;
                }
            }
        } else if (n1 > n2) {
            arryaOt = new int[n2][n1];
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < n1; j++) {
                    arryaOt[i][j] = Integer.parseInt(editTexts1.get(index).getText().toString());
                    index++;
                }
            }
        } else {
            arryaOt = new int[n2][n1];
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < n1; j++) {
                    arryaOt[i][j] = Integer.parseInt(editTexts1.get(index).getText().toString());
                    index++;
                }
            }
        }
        mDataPasser.onDataPass2(arryaOt, n1 + n2 - 1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDataPasser = (OnDataPass) context;
    }
}
