package libs.mjn.prettydialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mJafarinejad on 8/15/2017.
 */

class PrettyDialogButton extends LinearLayout {

    Context context;
    Resources resources;
    PrettyDialogCallback callback;
    Integer default_background_color = R.color.pdlg_color_blue, background_color;
    Integer default_text_color = R.color.pdlg_color_white, text_color;
    String text;
    TextView tv;
    ImageView iv;
    Typeface tf;
    Float radius = 5.6f;

    public PrettyDialogButton(
            Context context,
            String text,
            int textColor,
            int background_color,
            Typeface tf,
            PrettyDialogCallback callback) {
        super(context);
        this.context = context;
        resources = context.getResources();
        this.text = text;
        this.text_color = textColor;
        this.background_color = background_color;
        this.tf = tf;
        this.callback = callback;
        this.radius = 5.6f;
        init();
    }

    public PrettyDialogButton(
            Context context,
            String text,
            int textColor,
            int background_color,
            Typeface tf,
            PrettyDialogCallback callback,
            Float radius) {
        super(context);
        this.context = context;
        resources = context.getResources();
        this.text = text;
        this.text_color = textColor;
        this.background_color = background_color;
        this.tf = tf;
        this.callback = callback;
        this.radius = radius;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.pdlg_button, this);
        }
        tv = (TextView) findViewById(R.id.tv_button);
        tv.setText(text);
        tv.setTextColor(resources.getColor(text_color==null?default_text_color:text_color));
        if(tf != null)
            tv.setTypeface(tf);
        setBackground();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback!=null) {
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            callback.onClick();
                        }
                    }, 150);
                }
            }
        });
    }

    public void setTypeface(Typeface tf){
        this.tf = tf;
        tv.setTypeface(tf);
    }

    private void setBackground() {
        setBackgroundDrawable(makeSelector(resources.getColor(background_color==null?default_background_color:background_color)));
    }

    private int getLightenColor(int color){
        double fraction = 0.2;
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        red = (int)Math.min(red + (red * fraction), 255);
        green = (int)Math.min(green + (green * fraction), 255);
        blue = (int)Math.min(blue + (blue * fraction), 255);
        int alpha = Color.alpha(color);
        return Color.argb(alpha, red, green, blue);
    }

    private StateListDrawable makeSelector(int color) {
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(150);
        GradientDrawable pressed_drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[] {getLightenColor(color),getLightenColor(color)});
//        pressed_drawable.setCornerRadius(resources.getDimensionPixelSize(R.dimen.pdlg_corner_radius));
        pressed_drawable.setCornerRadius(
                TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        radius,
                        resources.getDisplayMetrics()
                )
        );

        GradientDrawable default_drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[] {color,color});
//        default_drawable.setCornerRadius(resources.getDimensionPixelSize(R.dimen.pdlg_corner_radius));

        default_drawable.setCornerRadius(
            TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    radius,
                    resources.getDisplayMetrics()
            )
        );
        res.addState(new int[]{android.R.attr.state_pressed}, pressed_drawable);
        res.addState(new int[]{}, default_drawable);
        return res;
    }
}
