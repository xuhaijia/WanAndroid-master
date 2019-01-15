package zqx.rj.com.mvvm.state.callback.todo

/**
 * author：  han7jia
 * create：  2019/1/3 19:09
 * desc：    type 监听器
 */
interface TypeChangeListener {
    fun typeChange(type: Int)

    fun refreshTodoList()
}