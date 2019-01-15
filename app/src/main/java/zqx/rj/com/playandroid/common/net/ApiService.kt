package zqx.rj.com.playandroid.common.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import zqx.rj.com.mvvm.http.response.BaseResponse
import zqx.rj.com.mvvm.http.response.EmptyRsp
import zqx.rj.com.playandroid.account.data.bean.LoginRsp
import zqx.rj.com.playandroid.account.data.bean.RegisterRsp
import zqx.rj.com.playandroid.common.search.data.bean.HotKeyRsp
import zqx.rj.com.playandroid.common.search.data.bean.SearchResultRsp
import zqx.rj.com.playandroid.home.data.bean.BannerRsp
import zqx.rj.com.playandroid.home.data.bean.CommonWebRsp
import zqx.rj.com.playandroid.home.data.bean.HomeArticleRsp
import zqx.rj.com.playandroid.mine.data.bean.CollectRsp
import zqx.rj.com.playandroid.navigation.data.bean.NaviCategoryRsp
import zqx.rj.com.playandroid.project.data.bean.ProjectRsp
import zqx.rj.com.playandroid.project.data.bean.ProjectTreeRsp
import zqx.rj.com.playandroid.system.data.bean.TopTreeRsp
import zqx.rj.com.playandroid.system.data.bean.TreeArticleRsp
import zqx.rj.com.playandroid.todo.data.bean.PageRsp
import zqx.rj.com.playandroid.todo.data.bean.TodoRsp
import zqx.rj.com.playandroid.wechat.data.bean.WeChatNameRsp
import zqx.rj.com.playandroid.wechat.data.bean.WxArticleRsp

/**
 * author：  han7jia
 * created： 2018/10/10 16:43
 * desc：    Api  (来自 hongyang大神的玩Android Api -> http://www.wanandroid.com/blog/show/2 )
 */
interface ApiService {

    @POST("/user/login")
    fun getLogin(@Query("username") username: String,
                 @Query("password") password: String): Observable<BaseResponse<LoginRsp>>

    @POST("/user/register")
    fun getRegister(@Query("username") username: String, @Query("password") password: String,
                    @Query("repassword") repassword: String): Observable<BaseResponse<RegisterRsp>>

    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<List<BannerRsp>>>

    @GET("/article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<BaseResponse<HomeArticleRsp>>

    @GET("/hotkey/json")
    fun getHotKey(): Observable<BaseResponse<List<HotKeyRsp>>>

    @POST("/article/query/{page}/json")
    fun search(@Path("page") page: Int, @Query("k") key: String): Observable<BaseResponse<SearchResultRsp>>

    @GET("/friend/json")
    fun getCommonWeb(): Observable<BaseResponse<List<CommonWebRsp>>>

    @GET("/navi/json")
    fun getCategory(): Observable<BaseResponse<List<NaviCategoryRsp>>>

    @GET("/tree/json")
    fun getTree(): Observable<BaseResponse<List<TopTreeRsp>>>

    @GET("/article/list/{page}/json")
    fun getArticleTree(@Path("page") page: Int, @Query("cid") id: Int): Observable<BaseResponse<TreeArticleRsp>>

    @GET("/lg/collect/list/{page}/json")
    fun getCollectArticle(@Path("page") page: Int): Observable<BaseResponse<CollectRsp>>

    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Observable<BaseResponse<EmptyRsp>>

    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Observable<BaseResponse<EmptyRsp>>

    @POST("/lg/uncollect/{id}/json")
    fun unMyCollect(@Path("id") id: Int, @Query("originId") originId: Int): Observable<BaseResponse<EmptyRsp>>

    @GET("/project/tree/json")
    fun getProjectTree(): Observable<BaseResponse<List<ProjectTreeRsp>>>

    @GET("/project/list/{page}/json")
    fun getProjects(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ProjectRsp>>

    @GET("/wxarticle/chapters/json")
    fun getWeChatName(): Observable<BaseResponse<List<WeChatNameRsp>>>

    @GET("/wxarticle/list/{id}/{page}/json")
    fun getWxArticle(@Path("id") id: Int, @Path("page") page: Int)
            : Observable<BaseResponse<WxArticleRsp>>

    @GET("/lg/todo/v2/list/{page}/json")
    fun getTodoList(@Path("page") page: Int,
                    @Query("status") status: Int,
                    @Query("type") type: Int,
                    @Query("priority") priority: Int,
                    @Query("orderby") orderby: Int)
            : Observable<BaseResponse<PageRsp<TodoRsp>>>

    @POST("/lg/todo/done/{id}/json")
    fun finishTodo(@Path("id") id: Int,
                   @Query("status") status: Int)
            : Observable<BaseResponse<EmptyRsp>>

    @POST("/lg/todo/delete/{id}/json")
    fun deleteTodo(@Path("id") id: Int): Observable<BaseResponse<EmptyRsp>>

    @POST("/lg/todo/add/json")
    fun saveTodo(@Query("title") title: String,
                 @Query("date") time: String,
                 @Query("type") type: Int,
                 @Query("content") content: String): Observable<BaseResponse<EmptyRsp>>

    @POST("/lg/todo/update/{id}/json")
    fun updateTodo(@Path("id") id: Int,
                   @Query("title") title: String,
                   @Query("date") time: String,
                   @Query("status") status: Int,
                   @Query("type") type: Int,
                   @Query("content") content: String,
                   @Query("priority") priority: Int
    ): Observable<BaseResponse<EmptyRsp>>
}