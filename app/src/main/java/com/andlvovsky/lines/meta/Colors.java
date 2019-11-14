package com.andlvovsky.lines.meta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.graphics.Color.rgb;

public class Colors {
    public static final List<Integer> BALLS = Collections.unmodifiableList(Arrays.asList(
        rgb(255, 255, 0),
        rgb(0, 255, 0),
        rgb(0, 0, 255),
        rgb(255, 0, 0),
        rgb(128, 0, 128),
        rgb(0, 0, 0)));

    public static final int HIGHLIGHTED_BORDER = rgb(255, 128, 0);

    public static final int NORMAL_BORDER = rgb(50, 50, 50);

    private Colors() {}
}
