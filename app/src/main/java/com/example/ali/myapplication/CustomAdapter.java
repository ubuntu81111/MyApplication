package com.example.ali.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.util.ArrayList;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;


/**
 * Created by ali on 20-May-17.
 */

class CustomAdapter extends BaseAdapter {

    ArrayList myList = new ArrayList();
    LayoutInflater inflater;
    Context context;
    EditText price;
    BasePrice baseprice = new BasePrice();
    int goodsprice;

    public CustomAdapter(Context context, ArrayList myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListData getItem(int position) {
        return (ListData) myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder mViewHolder;

        if (convertView == null) {

            //// TODO: 20-May-17  layout_list item
            convertView = inflater.inflate(R.layout.customlayout_list_item, parent, false);
            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }

        ListData currentListData = getItem(position);

        mViewHolder.tvTitle.setText(currentListData.getTitle());
        mViewHolder.tvDesc.setText(currentListData.getDescription());

        mViewHolder.mMultiLineRadioGroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ViewGroup group, RadioButton button) {
                Toast.makeText(context,
                        button.getId() + " was clicked",
                        Toast.LENGTH_SHORT).show();
                switch (button.getId()) {
                    case 1:
                        goodsprice = 120;
                        baseprice.setPrice(goodsprice);
                        mViewHolder.price.setText("" + goodsprice);
                        break;

                    case 2:
                        goodsprice = 130;
                        baseprice.setPrice(goodsprice);
                        mViewHolder.price.setText("" + goodsprice);
                        break;

                    case 3:
                        goodsprice = 140;
                        baseprice.setPrice(goodsprice);
                        mViewHolder.price.setText("" + goodsprice);
                        break;

                    case 4:
                        goodsprice = 150;
                        baseprice.setPrice(goodsprice);
                        mViewHolder.price.setText("" + goodsprice);
                        break;

                    default:
                        goodsprice = (50 + 50);
                        baseprice.setPrice(goodsprice);
                        mViewHolder.price.setText("" + goodsprice);
                }


                notifyDataSetChanged();
            }
        });
        mViewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {
                    case R.id.radiosmall:
                        mViewHolder.price.setText("" + (baseprice.getPrice() + 10));
                        break;

                    case R.id.radiomedium:
                        mViewHolder.price.setText("" + (baseprice.getPrice() + 20));
                        break;

                    case R.id.radiolarge:
                        mViewHolder.price.setText("" + (baseprice.getPrice() + 40));
                        break;

                    default:
                        mViewHolder.price.setText("" + (baseprice.getPrice()));
                }
                notifyDataSetChanged();
            }
        });


        mViewHolder.realgroup.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(RadioRealButton button, final int position) {
                Toast.makeText(context, "Clicked! Position: " + position, Toast.LENGTH_SHORT).show();

            }
        });


//mViewHolder.
        //   mViewHolder.ivIcon.setImageResource(currentListData.getImgResId());
        // mViewHolder.ivTick.setImageResource(currentListData.getImgResId());
        return convertView;
    }

    public class ViewHolder {
        final MultiLineRadioGroup mMultiLineRadioGroup;
        final TextView tvTitle, tvDesc;
        Button btnforsmall, btnformedium, btnforlarge;
        ImageView ivIcon;
        ImageView ivTick;
        final RadioGroup radioGroup;
        final EditText price;
        final RadioRealButtonGroup realgroup;

        public ViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.customtvTitle);
            tvDesc = (TextView) item.findViewById(R.id.customtvDesc);
            radioGroup = (RadioGroup) item.findViewById(R.id.group);

            mMultiLineRadioGroup = (MultiLineRadioGroup) item.findViewById(R.id.main_activity_multi_line_radio_group);

            price = (EditText) item.findViewById(R.id.price);

            realgroup = (RadioRealButtonGroup) item.findViewById(R.id.realgroup);
          /*  price.setText(baseprice.getPrice());*/
            //     ivIcon = (ImageView) item.findViewById(R.id.ivIcon);
            //       ivTick = (ImageView) item.findViewById(R.id.imageViewTick);

        }
    }
/*


    final RadioRealButton button1 = (RadioRealButton) findViewById(R.id.button1);
    final RadioRealButton button2 = (RadioRealButton) findViewById(R.id.button2);

    RadioRealButtonGroup group = (RadioRealButtonGroup) findViewById(R.id.group);

// onClickButton listener detects any click performed on buttons by touch
        group.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener() {
        @Override
        public void onClickedButton(RadioRealButton button, int position) {
            Toast.makeText(MainActivity.this, "Clicked! Position: " + position, Toast.LENGTH_SHORT).show();
        }
    });

// onPositionChanged listener detects if there is any change in position
        group.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
        @Override
        public void onPositionChanged(RadioRealButton button, int position) {
            Toast.makeText(MainActivity.this, "Position Changed! Position: " + position, Toast.LENGTH_SHORT).show();
        }
    });

// onLongClickedButton detects long clicks which are made on any button in group.
// return true if you only want to detect long click, nothing else
// return false if you want to detect long click and change position when you release
        group.setOnLongClickedButtonListener(new RadioRealButtonGroup.OnLongClickedButtonListener() {
        @Override
        public boolean onLongClickedButton(RadioRealButton button, int position) {
            Toast.makeText(MainActivity.this, "Long Clicked! Position: " + position, Toast.LENGTH_SHORT).show();
            return false;
        }
    });

*/


}
