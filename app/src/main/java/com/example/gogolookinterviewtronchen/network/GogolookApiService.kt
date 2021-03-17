
import com.example.gogolookinterviewtronchen.BuildConfig
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private const val HOST_NAME = "pixabay.com"
private const val BASE_URL = "https://$HOST_NAME/api/"
private const val KEY = "20701341-4fa246cd8748d84fad02a9897"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface GogolookApiService {

    @GET("https://$HOST_NAME/api/")
    suspend fun getSearchResult(@Query("key") key : String = KEY , @Query("q") inputString : String): SearchResult

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object GogolookApi {
    val retrofitService : GogolookApiService by lazy { retrofit.create(GogolookApiService::class.java) }
}