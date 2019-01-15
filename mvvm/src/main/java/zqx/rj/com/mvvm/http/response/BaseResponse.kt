package zqx.rj.com.mvvm.http.response

/**
 * author：  han7jia
 * created： 2018/10/11 18:29
 * desc：    返回数据 基类
 */
open class BaseResponse<T>(var data: T, var errorCode: Int = -1, var errorMsg: String = "")