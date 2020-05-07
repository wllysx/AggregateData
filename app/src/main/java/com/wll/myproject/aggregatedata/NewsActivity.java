package com.wll.myproject.aggregatedata;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wll.myproject.aggregatedata.adapter.MyViewPagerAdapter;
import com.wll.myproject.aggregatedata.address.GetRequestAddress;
import com.wll.myproject.aggregatedata.entity.newsproduct.Product;
import com.wll.myproject.aggregatedata.entity.newsproduct.Reason;
import com.wll.myproject.aggregatedata.fragment.ProductFragment;
import com.wll.myproject.aggregatedata.util.HttpApi;
import com.wll.myproject.aggregatedata.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity implements OnItemClickListener, MyScrollView.OnScrollChangedListener {

    List<String> localImages;


    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.m_news_toolbar)
    Toolbar mNewsToolbar;
    @BindView(R.id.m_tablayout)
    TabLayout mTablayout;
    @BindView(R.id.m_viewpager)
    ViewPager mViewpager;
    MyViewPagerAdapter myViewPagerAdapter;
    static List<String> mtitles = new ArrayList<>();
    List<ProductFragment> fragmentList;
    List<Product> mListProducts;
    Reason reason;

    static {
        mtitles.add( "头条" );
        mtitles.add( "社会" );
        mtitles.add( "国内" );
        mtitles.add( "国际" );
        mtitles.add( "娱乐" );
        mtitles.add( "体育" );
        mtitles.add( "军事" );
        mtitles.add( "科技" );
        mtitles.add( "财经" );
        mtitles.add( "时尚" );

    }

    static List<String> mtitleE = new ArrayList<>();

    static {
        mtitleE.add( "top" );
        mtitleE.add( "shehui" );
        mtitleE.add( "guonei" );
        mtitleE.add( "guoji" );
        mtitleE.add( "yule" );
        mtitleE.add( "tiyu" );
        mtitleE.add( "junshi" );
        mtitleE.add( "keji" );
        mtitleE.add( "caijing" );
        mtitleE.add( "shishang" );
    }

    Boolean istrue = true;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case 0:
                    /**
                     * 重点：改变数据进行更新时，不要改变数据的原地址,要先清空数据，
                     * 再将新的数据进行套壳进去才能完成更新。
                     */
                    mListProducts = reason.getResult().getData();
                    if (fragmentList.size() == 0) {
                        if (mListProducts != null) {
                            for (int i = 0; i < mtitles.size(); i++) {
                                //这里带入程序来访问数据。获得list数据集合。
                                fragmentList.add( new ProductFragment( mListProducts, i ) );
                            }
                        }

                    } else {
                        fragmentList.get( position ).setMListProducts( mListProducts );
                    }

                    initBanner();

                    if (istrue) {
                        istrue = false;
                        initViewPager();
                        initTab();
                    }
                    Log.i( "wll11:", "thread:" + Thread.currentThread().getName() );
                    break;
                case 1:
                    mListProducts.clear();
                    fragmentList.clear();
                    for (int i = 0; i < 10; i++) {
                        mListProducts.add( new Product() );
                    }
                    for (int i = 0; i < 10; i++) {
                        fragmentList.add( new ProductFragment( mListProducts, i ) );
                    }
                    initBanner();
                    if (istrue) {
                        istrue = false;
                        initViewPager();
                        initTab();

                    }
                    break;
                case 2:

                    break;
            }
        }
    };
    @BindView(R.id.m_convenientBanner)
    ConvenientBanner mConvenientBanner;
    @BindView(R.id.m_myscrollview)
    MyScrollView myScrollView;
    @BindView(R.id.ll_inner)
    LinearLayout llInner;
    @BindView(R.id.m_white_ll)
    LinearLayout mWhiteLl;
    @BindView(R.id.ll_outer)
    LinearLayout llOuter;
    @BindView(R.id.m_gd_ll)
    LinearLayout mGdLl;
    @BindView(R.id.m_con_ll)
    LinearLayout mConLl;
    @BindView(R.id.m_refresh)
    SmartRefreshLayout mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news );
        ButterKnife.bind( this );
        initToolbar();
        fragmentList = new ArrayList<>();
        localImages = new ArrayList<>();
        mListProducts = new ArrayList<>();
        initData();
        initListener();
        initRefresh();
    }

    private void initRefresh() {
//        mRefresh.setRefreshHeader(new ClassicsHeader( this ).setSpinnerStyle( SpinnerStyle.FixedBehind ) );
//
//        mRefresh.setEnableHeaderTranslationContent(true);
        mRefresh.setRefreshHeader( new ClassicsHeader( this ) );
        mRefresh.setOnRefreshListener( new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData( position );
                if (reason.getResult() != null) {
                    mRefresh.finishRefresh();
                    mRefresh.setReboundDuration( 500 );
                    return;
                }
                mRefresh.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        mRefresh.finishRefresh( false );
                    }
                } ,5000);
            }
        } );
    }

    private void initListener() {
        myScrollView.setOnScrollChangedListener( this );
//        mConLl.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent( NewsActivity.this, MyWebViewActivity.class );
//                intent.putExtra( "weburl", mListProducts.get(imagePosition).getUrl() );
//                startActivity( intent );
//            }
//        } );
    }

    private void initToolbar() {
        mNewsToolbar.setVisibility( View.GONE );
        mNewsToolbar.setNavigationIcon( R.mipmap.backs );
        mNewsToolbar.getBackground().setAlpha( 0 );  //先设置透明
        //设置text为透明。
        textView.setTextColor( Color.argb( 0, 255, 255, 255 ) );
        mNewsToolbar.setNavigationOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        } );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags);
        }
    }



    public void initBanner() {
        List<String> newLocalImage = new ArrayList<>();

        for (int i = 0; i < mListProducts.size(); i++) {

            newLocalImage.add( mListProducts.get( i ).getThumbnailPicS() );
        }
        localImages.clear();
        localImages.addAll( newLocalImage );

        mConvenientBanner.setPages( new CBViewHolderCreator() {
            @Override
            public LocalImageHolderView createHolder(View itemView) {
                return new LocalImageHolderView( itemView );
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_localimage;
            }
        }, localImages ).setPageIndicator( new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused} )
                .setPageIndicatorAlign( ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL )
                .setOnPageChangeListener( new OnPageChangeListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    }

                    @Override
                    public void onPageSelected(int index) {

                    }
                } )
                .setOnItemClickListener( this );


    }

    //对轮廓图进行点击事件的监听。
    @Override
    public void onItemClick(int position) {
        String url = mListProducts.get( position ).getUrl();
        Intent intent = new Intent( this, MyWebViewActivity.class );
        intent.putExtra( "weburl", url );
        startActivity( intent );
    }

    @Override
    public void onScrollChanged(NestedScrollView who, int l, int t, int oldWidth, int oldHeight) {
        //这个高度是一直不变的。3/5这个尺寸很合适。。
        float height = mConvenientBanner.getHeight();  //获取图片的高度
        /**
         * toolbar 渐变背景 偏移量控制
         */
        Log.i( "wll11", "oldheight:" + oldHeight + "height:" + height );
        //从300高度开始进行toolbar和里面文本的透明度的改变。。
        if (oldHeight > 100) {
            mNewsToolbar.setVisibility( View.VISIBLE );
            if (oldHeight < height) {
                int i = Float.valueOf( oldHeight / height * 255 ).intValue();    //i 有可能小于 0
                mNewsToolbar.getBackground().setAlpha( Math.max( i, 0 ) );   // 0~255 透明度
                /**
                 * 字体颜色透明度控制
                 */
                textView.setTextColor( Color.argb( i, 255, 255, 255 ) );
            } else {
                mNewsToolbar.getBackground().setAlpha( 255 );
                textView.setTextColor( Color.argb( 255, 255, 255, 255 ) );
            }
        } else {
            //重新初始化toolbar，让他变成透明色。
            mNewsToolbar.setVisibility( View.GONE );
            initToolbar();
        }

//        /**
//         * 悬浮控制
//         */
        if (oldHeight > (searchLayoutTop - 50)) {
            if (mGdLl.getParent() != llOuter) {
                llInner.removeView( mGdLl );
                llOuter.removeAllViews();
                llOuter.addView( mGdLl );
                //固定后的控件背景颜色
                llOuter.setBackgroundColor( getResources().getColor( R.color.qmui_config_color_white ) );

                //心态有点爆炸，当固定区域固定后，下面布局会直接跳上来，这里做一个空白布局的显示和异常来使其不那么突兀。。。
                mWhiteLl.setVisibility( View.INVISIBLE );
            }
        } else {
            if (mGdLl.getParent() != llInner) {
                mWhiteLl.setVisibility( View.GONE );
                llOuter.removeView( mGdLl );
                llInner.removeAllViews();
                llInner.addView( mGdLl );
                llOuter.setBackgroundColor( Color.argb( 0, 255, 255, 255 ) );
            }
        }

    }


    private int searchLayoutTop;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged( hasFocus );
        if (hasFocus) {
            searchLayoutTop = mConvenientBanner.getBottom() - dp2px( 73 );//获取searchLayout的顶部位置
            Log.i( "wll11", "searchLayoutTop:" + searchLayoutTop );
        }

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics() );
    }

    //图片类。
    public class LocalImageHolderView extends Holder<String> {
        private ImageView imageView;

        public LocalImageHolderView(View itemView) {
            super( itemView );
        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById( R.id.ivPost );

        }

        @Override
        public void updateUI(String data) {
            RequestOptions options = new RequestOptions().placeholder( R.mipmap.loading ).error( R.mipmap.error );
            Glide.with( NewsActivity.this ).load( data ).apply( options ).into( imageView );
        }
    }


    int position = 0;

    private void initData() {
        requestData( position );
    }

    FragmentManager fragmentManager;

    private void initViewPager() {
        fragmentManager = getSupportFragmentManager();
        myViewPagerAdapter = new MyViewPagerAdapter( fragmentManager, 0, fragmentList );
        mViewpager.setAdapter( myViewPagerAdapter );
        myViewPagerAdapter.setTitles( mtitles );
    }


    TextView textView1;

    private void initTab() {
        mTablayout.setupWithViewPager( mViewpager );
        TabLayout.Tab tab = mTablayout.getTabAt( 0 );
        if (tab != null) {
            textView1 = (TextView) LayoutInflater.from( this ).inflate( R.layout.layout_tab, null, false );
            textView1.setText( tab.getText() + "" );
            textView1.setTextSize( 26 );
            tab.setCustomView( textView1 );
        }

        mTablayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if (tab != null) {
                    textView1.setText( tab.getText() );
                    tab.setCustomView( textView1 );
                    requestData( position );
                    //直接更新。
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView( null );
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );

    }


    private void requestData(int position) {
        Toast.makeText( NewsActivity.this, "第：" + position, Toast.LENGTH_SHORT ).show();
        String url = GetRequestAddress.getNews( mtitleE.get( position ) );
        HttpApi.generalRequest( url, new HttpApi.HttpRequestListener<String>() {
            @Override
            public void requestListener(String result) {
                Log.i( "wll11", result );
                try {
                    reason = JSON.parseObject( result, Reason.class );
                    if (reason.getResult() == null) {
                        Message message = Message.obtain();
                        message.what = 1;
                        handler.sendMessage( message );

                    } else {
                        Message message = Message.obtain();
                        message.what = 0;
                        handler.sendMessage( message );
                    }
                } catch (JSONException e) {
                    if (e.toString().contains( "网络异常" )) {
                        Toast.makeText( NewsActivity.this, "网络异常，请检查网络。", Toast.LENGTH_SHORT ).show();
                    } else {
                        Toast.makeText( NewsActivity.this, "系统繁忙，请稍后重试。", Toast.LENGTH_SHORT ).show();
                    }
                    finish();
                }


            }
        }, new ArrayList<>(), new ArrayList<>(), false, "get", "" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConvenientBanner.startTurning( 5000 );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mConvenientBanner.stopTurning();
    }
}
