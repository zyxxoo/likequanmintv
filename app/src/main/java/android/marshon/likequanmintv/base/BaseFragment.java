package android.marshon.likequanmintv.base;

import android.app.Activity;
import android.marshon.likequanmintv.di.component.FragmentComponent;
import android.marshon.likequanmintv.librarys.mvpbase.BaseView;
import android.marshon.likequanmintv.librarys.base.BaseActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import javax.annotation.Nullable;


/**
 * Created by Marshon.Chen on 2016/6/1.
 * DESC:
 */
public abstract class BaseFragment extends Fragment implements BaseView {
    protected View rootView;
    protected BaseActivity mActivity;
    protected FragmentComponent mFragmentComponent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View inflate = inflater.inflate(layoutId, null);
        ScrollView scrollView;
        if (canScroll()){
            scrollView = new ScrollView(inflate.getContext());
            scrollView.addView(inflate);
            rootView=scrollView;
        }else {
            rootView=inflate;
        }
        initView(rootView);
        return  rootView;
    }

    @Override
    public void onActivityCreated(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public boolean canScroll(){
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity= (BaseActivity) activity;
    }

    protected abstract  int getLayoutId();
    protected abstract void initView(View rootView);
    protected abstract void initData();

    public <T extends View> T find(int viewId)
    {
        View view = rootView.findViewById(viewId);
        return (T) view;
    }

    @Override
    public void showProgress(@Nullable String msg) {
        mActivity.showProgress(msg);
    }


    public void showToast(String msg){
        mActivity.showToast(msg);
    }
    public void showLongToast(String msg){
//        Toast.makeText(APP.getContext(),""+content,Toast.LENGTH_LONG).show();
        mActivity.showLongToast(msg);
    }

    @Override
    public void hideProgress() {
        mActivity.hideProgress();
    }

    @Override
    public void showAlert(String msg) {
        mActivity.showAlert(msg);
    }
}
