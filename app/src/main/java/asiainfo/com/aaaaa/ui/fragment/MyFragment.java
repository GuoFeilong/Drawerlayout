package asiainfo.com.aaaaa.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asiainfo.com.aaaaa.R;
import asiainfo.com.aaaaa.ui.view.CustomCircleIcon;

/**
 * Created by jsion on 15/9/23.
 */
public class MyFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private List<ItemModel> mDatas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentContent = inflater.inflate(R.layout.fragment_content, container, false);
        initRecyclerView(fragmentContent);
        initRecyclerViewEvent();
        return fragmentContent;
    }

    /**
     * 初始化recycler事件
     */
    private void initRecyclerViewEvent() {
        mDatas = new ArrayList<>();
        ItemModel itemModel = new ItemModel("Jsion", "帝都", "19", R.mipmap.gridview_icon_02s_friends, R.mipmap.img_0);
        mDatas.add(itemModel);

        itemModel = new ItemModel("Melody", "魔都", "10", R.mipmap.gridview_icon_03_data_usage, R.mipmap.img_1);
        mDatas.add(itemModel);

        itemModel = new ItemModel("Mliya", "性都", "88", R.mipmap.gridview_icon_04s_discovery, R.mipmap.img_2);
        mDatas.add(itemModel);

        itemModel = new ItemModel("Rose", "商都", "76", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_3);
        mDatas.add(itemModel);

        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_4);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_5);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_6);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_7);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_8);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_9);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_10);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_11);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_12);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_13);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_14);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_15);
        mDatas.add(itemModel);
        itemModel = new ItemModel("God", "都都", "88", R.mipmap.gridview_icon_05_quick_order, R.mipmap.img_16);
        mDatas.add(itemModel);

        mAdapter = new HomeAdapter();
        StaggeredGridLayoutManager sta = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(sta);

//      mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//      mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 初始化recyclerview
     */
    private void initRecyclerView(View fr) {
        mRecyclerView = (RecyclerView) fr.findViewById(R.id.id_recyclerview);
    }


    /**
     * recy适配器
     */
    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getContext()).inflate(R.layout.item_recycler_view, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ItemModel itemModel = mDatas.get(position);

//            holder.nickPic.setImageResource(itemModel.getBigPick());


            holder.nickPic.setImageBitmap(itemModel.getSuoLueTu());
            holder.picCount.setText(itemModel.getPicCount());
            holder.nickName.setText(itemModel.getNickName());
            holder.address.setText(itemModel.getAddress());
            holder.nickIcon.setmIconDrawable(itemModel.getNickPick());
            holder.nickIcon.setmMenuText("");
            CustomCircleIcon.OnMyAfterChangeListener listener = new CustomCircleIcon.OnMyAfterChangeListener() {
                @Override
                public void myAfterChange(String text, int icon) {

                }
            };
            holder.nickIcon.setMyAfterChangeListener(listener);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView nickName;
            TextView address;
            TextView picCount;
            CustomCircleIcon nickIcon;
            ImageView nickPic;

            public MyViewHolder(View view) {
                super(view);
                nickName = (TextView) view.findViewById(R.id.tv_name);
                address = (TextView) view.findViewById(R.id.tv_address);
                picCount = (TextView) view.findViewById(R.id.tv_pic_count);
                nickIcon = (CustomCircleIcon) view.findViewById(R.id.cci_icon);
                nickPic = (ImageView) view.findViewById(R.id.iv_icon_pic);
            }
        }
    }

    class ItemModel {
        String nickName;
        String address;
        String picCount;
        int nickPick;
        int bigPick;
        Bitmap suoLueTu;


        public ItemModel(String nickName, String address, String picCount, int nickPick, int bigPick) {
            this.nickName = nickName;
            this.address = address;
            this.picCount = picCount;
            this.nickPick = nickPick;
            this.bigPick = bigPick;


            Drawable drawable = getResources().getDrawable(bigPick);
            BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap bitmap = bd.getBitmap();

            suoLueTu = zoomImage(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5);
        }

        public String getNickName() {
            return nickName;
        }

        public String getAddress() {
            return address;
        }

        public String getPicCount() {
            return picCount;
        }

        public int getNickPick() {
            return nickPick;
        }

        public int getBigPick() {
            return bigPick;
        }


        public Bitmap getSuoLueTu() {
            return suoLueTu;
        }

        public void setSuoLueTu(Bitmap suoLueTu) {
            this.suoLueTu = suoLueTu;
        }
    }


    /***
     * 图片的缩放方法
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }
}


