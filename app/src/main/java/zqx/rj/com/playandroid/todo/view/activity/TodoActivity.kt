package zqx.rj.com.playandroid.todo.view.activity

import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.startActivity
import zqx.rj.com.mvvm.base.BaseActivity
import zqx.rj.com.mvvm.common.Preference
import zqx.rj.com.mvvm.common.constant.Constant
import zqx.rj.com.mvvm.state.callback.todo.TodoContext
import zqx.rj.com.playandroid.R
import zqx.rj.com.playandroid.todo.view.fragment.TodoFragment


class TodoActivity : BaseActivity() {

    // 当前显示的 fragment
    private lateinit var mCurrentFragment: Fragment

    // 当前 类型 工作1  学习2 生活3  0 默认全部
    // SharedPreference 保存type类型
    private var type by Preference(Constant.TODO_TYPE, 0)

    private val mTodoFragment by lazy {
        TodoFragment.getInstance(Constant.TODO_STATUS, getString(R.string.finish), R.color.colorPrimaryDark)
    }

    private val mFinishFragment by lazy {
        TodoFragment.getInstance(Constant.FINISH_STATUS, getString(R.string.reduction), R.color.green)
    }

    override fun getLayoutId(): Int = R.layout.activity_todo

    override fun initView() {
        super.initView()

        setToolBar(toolbar, getStringType(type))
        setDefaultFragment()
        initFloatButton()
        initNavigationBar()
    }

    private fun getStringType(type: Int): String {
        return when (type) {
            Constant.ALL -> getString(R.string.all)
            Constant.WORK -> getString(R.string.work)
            Constant.STUDY -> getString(R.string.study)
            Constant.LIFE -> getString(R.string.life)
            else -> getString(R.string.all)
        }
    }

    /**
     *  设置默认 fragment为 待办todo fragment
     */
    private fun setDefaultFragment() {
        mCurrentFragment = mTodoFragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.content, mTodoFragment).commit()
    }

    private fun initFloatButton() {
        mFabAdd.setOnClickListener { startActivity<AddTodoActivity>() }
    }

    private fun initNavigationBar() {
        with(mNavigationBar) {
            setMode(BottomNavigationBar.MODE_FIXED)

            addItem(BottomNavigationItem(R.mipmap.ic_todo, R.string.todo))
            addItem(BottomNavigationItem(R.mipmap.ic_finish, R.string.finish))
            // 设置底部 BottomBar 默认选中 plan
            setFirstSelectedPosition(0)
            // 初始化
            initialise()
            // 设置 button 点击事件
            setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
                override fun onTabReselected(position: Int) {}

                override fun onTabUnselected(position: Int) {}

                override fun onTabSelected(position: Int) {
                    when (position) {
                        Constant.TODO -> goTo(mTodoFragment)
                        Constant.FINISH -> goTo(mFinishFragment)
                    }
                }
            })
        }
    }

    // 复用 fragment
    private fun goTo(to: Fragment) {
        if (mCurrentFragment != to) {
            val transaction = supportFragmentManager.beginTransaction()
            if (to.isAdded)
                transaction.hide(mCurrentFragment).show(to)
            else
                transaction.hide(mCurrentFragment).add(R.id.content, to)
            transaction.commit()
            mCurrentFragment = to
        }
    }

    /**
     *  右上角 类别菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.todo_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //  全部, 工作1  学习2 生活3
        when (item?.itemId) {
            R.id.todo_all -> {
                toolbar.title = getString(R.string.all)
                //通知 子 fragment 更新数据
                TodoContext.notifyTodoTypeChange(Constant.ALL)
            }
            R.id.todo_work -> {
                toolbar.title = getString(R.string.work)
                TodoContext.notifyTodoTypeChange(Constant.WORK)
            }
            R.id.todo_study -> {
                toolbar.title = getString(R.string.study)
                TodoContext.notifyTodoTypeChange(Constant.STUDY)
            }
            R.id.todo_life -> {
                toolbar.title = getString(R.string.life)
                TodoContext.notifyTodoTypeChange(Constant.LIFE)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() = finish()
}
