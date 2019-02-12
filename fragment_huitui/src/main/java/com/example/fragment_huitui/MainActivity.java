package com.example.fragment_huitui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager mFragmentManager;
    private FrameLayout mFramelayout;
    /**
     * 首页
     */
    private RadioButton mRbHome;
    /**
     * 页面
     */
    private RadioButton mRbCart;
    /**
     * 我的
     */
    private RadioButton mRbCategory;
    /**
     * 视屏
     */
    private RadioButton mRbPersonal;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // 获取Fragment管理者
        mFragmentManager = getSupportFragmentManager();
        // 先默认添加fragment1
        addFragment(new Fragment1(), "fragment1");

    }


    private void initView() {
        mFramelayout = (FrameLayout) findViewById(R.id.framelayout);
        mRbHome = (RadioButton) findViewById(R.id.rb_home);
        mRbHome.setOnClickListener(this);
        mRbCart = (RadioButton) findViewById(R.id.rb_cart);
        mRbCart.setOnClickListener(this);
        mRbCategory = (RadioButton) findViewById(R.id.rb_category);
        mRbCategory.setOnClickListener(this);
        mRbPersonal = (RadioButton) findViewById(R.id.rb_personal);
        mRbPersonal.setOnClickListener(this);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rb_home:
                addFragment(new Fragment1(), "fragment1");
                break;
            case R.id.rb_cart:
                addFragment(new Fragment2(), "fragment2");
                break;
            case R.id.rb_category:
                addFragment(new Fragment3(), "fragment3");
                break;
            case R.id.rb_personal:
                addFragment(new Fragment4(), "fragment4");
                break;
        }
    }
    public void addFragment(Fragment fragment, String tag) {
        // 开启事务
        FragmentTransaction beginTransaction = mFragmentManager
                .beginTransaction();
        // 执行事务,添加Fragment
        beginTransaction.add(R.id.framelayout, fragment, tag);
        // 添加到回退栈,并定义标记
        beginTransaction.addToBackStack(tag);
        // 提交事务
        beginTransaction.commit();

    }
    /**
     * 按返回键，逐渐退到上个fragment
     */
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断当前按键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
            // 判断当前回退栈中的fragment个数,
            if (backStackEntryCount > 1) {
                // 立即回退一步
                mFragmentManager.popBackStackImmediate();
                // 获取当前退到了哪一个Fragment上,重新获取当前的Fragment回退栈中的个数
                FragmentManager.BackStackEntry backStack = mFragmentManager
                        .getBackStackEntryAt(mFragmentManager
                                .getBackStackEntryCount() - 1);
                // 获取当前栈顶的Fragment的标记值
                String tag = backStack.getName();
                // 判断当前是哪一个标记
                if ("fragment1".equals(tag)) {
                    // 设置首页选中
                    mRbHome.setChecked(true);
                } else if ("fragment2".equals(tag)) {
                    // 设置购物车的tag
                    mRbCart.setChecked(true);
                } else if ("fragment3".equals(tag)) {
                    mRbCategory.setChecked(true);
                } else if ("fragment4".equals(tag)) {
                    mRbPersonal.setChecked(true);
                }
            } else {
                //回退栈中只剩一个时,退出应用
                finish();
            }
        }
        return true;
    }*/

    /**
     * 按返回键无论打开了多少，直接退到首页fragment
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断当前按键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = mFragmentManager.getBackStackEntryCount();
            // 回退栈中至少有多个fragment,栈底部是首页
            if (backStackEntryCount > 1) {
                // 如果回退栈中Fragment个数大于一.一直退出
                while (mFragmentManager.getBackStackEntryCount() > 1) {
                    mFragmentManager.popBackStackImmediate();
                    //选中第一个界面
                    mRbHome.setChecked(true);
                }
            } else {
                finish();
            }

        }
        return true;
    }
}








