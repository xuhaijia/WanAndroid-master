package zqx.rj.com.playandroid.account.data.context

import android.content.Context
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import zqx.rj.com.mvvm.state.UserState
import zqx.rj.com.mvvm.state.callback.collect.CollectListener
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.account.view.LoginActivity

/**
 * author：  han7jia
 * created： 2018/10/18 10:00
 * desc：    未登录状态
 */
class LogoutState : UserState {
    override fun goTodoActivity(context: Context?) {
        goLoginActivity(context)
    }

    override fun collect(context: Context?, position: Int,listener: CollectListener) {
        goLoginActivity(context)
    }

    override fun login(context: Context?) {
        goLoginActivity(context)
    }

    override fun goCollectActivity(context: Context?) {
        goLoginActivity(context)
    }

    // 跳转到登录
    private fun goLoginActivity(context: Context?) {
        context?.let {
            it.toast(it.getString(R.string.please_login))
            it.startActivity<LoginActivity>()
        }
    }
}