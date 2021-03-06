package com.test.opower.goodrecite.word_page.select_wbook;

import android.view.View;

import com.test.opower.goodrecite.R;
import com.test.opower.goodrecite.model.BaseActivity;
import com.test.opower.goodrecite.model.BtmBtnFragment;
import com.test.opower.goodrecite.model.WaveClkBtn;
import com.test.opower.goodrecite.model.ViewCtrl;
import com.test.opower.goodrecite.word_page.word_main.WordMainCtrl;

/**
 * Created by opower on 16-6-27.
 */
public class SelWBookCtrl extends ViewCtrl implements BtmBtnFragment.BtmBtnVwCtrl
{
	private SelWBookCtrl(BaseActivity act)
	{
		super(act);
	}

	@Override
	public void toCurFragment(Object pam)
	{
		//收集页面控件
		collectCtlFromView(null);

		//将主界面替换成单词本选择界面
		activity.getFragmentManager()
				.beginTransaction()
				.addToBackStack(null)
				.replace(R.id.lytWdsCtt, SelWBookFragment.ins())
				.commit();

		//将底部按钮框替换成下载单词本按钮
		activity.getFragmentManager()
				.beginTransaction()
				.addToBackStack(null)
				.replace(R.id.lytWdsBtm, new BtmBtnFragment()
						.setBtnId(R.id.btnSelWbkBtm)
						.setBtnTxt(R.string.start_dld_wb)
						.setViewCtrl(this))
				.commit();
	}

	@Override
	public void bindBtmBtn(View vw)
	{
		//开始下载单词本
		((WaveClkBtn) vw).setOnClickListener(DldWBookListener.ins());
	}

	@Override
	public void popBackFragment(Object pam)
	{
		//将主控制器的视图类型强制设定
		WordMainCtrl.ins().setViewType(WordMainCtrl.ViewType.WORD_MAIN);

		//根据读入的参数，判断是否强制执行popBackStack
		//因为当用户使用后退键时，系统会默认执行一个popBackStack
		if(pam != null
		&& pam.getClass() == Boolean.class
		&& (Boolean) pam)
		{
			activity.getFragmentManager().popBackStack();
		}

		//跳转到下载单词本页面需要两个Fragment
		activity.getFragmentManager().popBackStack();
	}

	private static SelWBookCtrl instance = null;
	public static SelWBookCtrl ini(BaseActivity act)
	{
		instance = new SelWBookCtrl(act);	return instance;
	}
	public static SelWBookCtrl ins()	{ return instance; }
}
