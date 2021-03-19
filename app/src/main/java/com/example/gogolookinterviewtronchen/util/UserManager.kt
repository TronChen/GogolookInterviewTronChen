import com.example.gogolookinterviewtronchen.GogolookApplication

object UserManager {

    const val HISTORY = "history"
    const val HISTORY_VALUE = "history_value"

    // get(), set()實現拿、放access_token功能
    var history: String?
        get() {
            return GogolookApplication.INSTANCE.getSharedPreferences(HISTORY, 0)
                .getString(HISTORY_VALUE,null)
        }
        set(value) {
            GogolookApplication.INSTANCE.getSharedPreferences(HISTORY,0).edit()
                .putString(HISTORY_VALUE, value).apply()
        }
}