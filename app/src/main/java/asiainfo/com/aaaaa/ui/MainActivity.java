package asiainfo.com.aaaaa.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import asiainfo.com.aaaaa.R;
import asiainfo.com.aaaaa.ui.fragment.MyFragment;
import asiainfo.com.aaaaa.ui.view.CustomCircleIcon;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mSlideMenu;
    private List<MenuModle> menuModles;
    private Toolbar toolbar;
    private RelativeLayout mContent;

    private MyFragment mBlankFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        menuModles = new ArrayList<MenuModle>();
        MenuModle m = new MenuModle("Friends", R.mipmap.gridview_icon_02s_friends);
        menuModles.add(m);

        m = new MenuModle("Usage", R.mipmap.gridview_icon_03_data_usage);
        menuModles.add(m);
        m = new MenuModle("Budget", R.mipmap.gridview_icon_03s_budget);
        menuModles.add(m);
        m = new MenuModle("Discovery", R.mipmap.gridview_icon_04s_discovery);
        menuModles.add(m);
        m = new MenuModle("QuickOrder", R.mipmap.gridview_icon_05_quick_order);
        menuModles.add(m);
    }

    /**
     * 初始化view
     */
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mSlideMenu = (ListView) findViewById(R.id.lv_menus);
        mContent = (RelativeLayout) findViewById(R.id.rl_content);
        mBlankFragment = new MyFragment();

    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        // Title
        toolbar.setTitle("Toolbar");

        // App Logo
        // toolbar.setLogo(R.drawable.ic_launcher);
        // Sub Title
        //toolbar.setSubtitle("Sub title");

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        toolbar.setNavigationIcon(R.mipmap.perm_group_system_tools);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);


        MenuAdapter adapter = new MenuAdapter();
        adapter.setMenuModles(menuModles);
        mSlideMenu.setAdapter(adapter);
        mSlideMenu.setOnItemClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.rl_content, mBlankFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 关闭侧边栏
        mDrawerLayout.closeDrawer(Gravity.LEFT);

        // 切换内容区域
    }


    class MenuAdapter extends BaseAdapter {
        private List<MenuModle> menuModles;
        Holder holder = null;

        public void setMenuModles(List<MenuModle> menuModles) {
            this.menuModles = menuModles;
        }

        @Override
        public int getCount() {
            return menuModles.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            convertView = View.inflate(MainActivity.this, R.layout.item_slide_menu, null);
            final CustomCircleIcon temp = (CustomCircleIcon) convertView.findViewById(R.id.cci_menu);


            temp.setmIconDrawable(menuModles.get(position).menuIcon);
            temp.setmMenuText(menuModles.get(position).menuText);
            CustomCircleIcon.OnMyAfterChangeListener listener = new CustomCircleIcon.OnMyAfterChangeListener() {
                @Override
                public void myAfterChange(String text, int icon) {

                }
            };
            temp.setMyAfterChangeListener(listener);

            return convertView;
        }
    }

    class MenuModle {
        private String menuText;
        private int menuIcon;

        public MenuModle(String menuText, int menuIcon) {
            this.menuText = menuText;
            this.menuIcon = menuIcon;
        }

        public String getMenuText() {
            return menuText;
        }

        public void setMenuText(String menuText) {
            this.menuText = menuText;
        }

        public int getMenuIcon() {
            return menuIcon;
        }

        public void setMenuIcon(int menuIcon) {
            this.menuIcon = menuIcon;
        }
    }

    class Holder {
        CustomCircleIcon menuView;
    }

}
