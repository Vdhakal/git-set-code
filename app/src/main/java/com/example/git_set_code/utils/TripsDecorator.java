package com.example.git_set_code.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 */
public class TripsDecorator extends RecyclerView.ItemDecoration {
    private final int verticalSpaceHeight;

    /**
     *
     * @param verticalSpaceHeight
     */
    public TripsDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    /**
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
        outRect.top=10;
    }
}
