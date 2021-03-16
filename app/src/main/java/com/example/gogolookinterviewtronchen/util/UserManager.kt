import com.example.gogolookinterviewtronchen.GogolookApplication

object UserManager {

    const val NAME = "name"
    const val NAME_VALUE = "name_value"
    const val EMAIL = "email"
    const val EMAIL_VALUE = "email_value"
    const val PHOTO = "photo"
    const val PHOTO_VALUE = "photo_value"



    // get(), set()實現拿、放access_token功能
    var name: String?
        get() {
            return GogolookApplication.INSTANCE.getSharedPreferences(NAME, 0)
                .getString(NAME_VALUE,null)
        }
        set(value) {
            GogolookApplication.INSTANCE.getSharedPreferences(NAME,0).edit()
                .putString(NAME_VALUE, value).apply()
        }

    var email: String?
        get() {
            return GogolookApplication.INSTANCE.getSharedPreferences(EMAIL, 0)
                .getString(EMAIL_VALUE,null)
        }
        set(value) {
            GogolookApplication.INSTANCE.getSharedPreferences(EMAIL,0).edit()
                .putString(EMAIL_VALUE, value).apply()
        }

    var photo: String?
        get() {
            return GogolookApplication.INSTANCE.getSharedPreferences(PHOTO, 0)
                .getString(PHOTO_VALUE,null)
        }
        set(value) {
            GogolookApplication.INSTANCE.getSharedPreferences(PHOTO,0).edit()
                .putString(PHOTO_VALUE, value).apply()
        }
}