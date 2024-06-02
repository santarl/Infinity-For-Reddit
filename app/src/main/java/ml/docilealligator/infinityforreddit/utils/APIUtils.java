package ml.docilealligator.infinityforreddit.utils;

import android.util.Base64;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.util.Log;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;

/**
 * Created by alex on 2/23/18.
 */

public class APIUtils {
    public static final String OAUTH_URL = "https://www.reddit.com/api/v1/authorize.compact";
    public static final String OAUTH_API_BASE_URI = "https://oauth.reddit.com";
    public static final String API_BASE_URI = "https://www.reddit.com";
    public static final String API_UPLOAD_MEDIA_URI = "https://reddit-uploaded-media.s3-accelerate.amazonaws.com";
    public static final String API_UPLOAD_VIDEO_URI = "https://reddit-uploaded-video.s3-accelerate.amazonaws.com";
    public static final String REDGIFS_API_BASE_URI = "https://api.redgifs.com";
    public static final String IMGUR_API_BASE_URI = "https://api.imgur.com/3/";
    public static final String PUSHSHIFT_API_BASE_URI = "https://api.pushshift.io/";
    public static final String REVEDDIT_API_BASE_URI = "https://api.reveddit.com/";
    public static final String STREAMABLE_API_BASE_URI = "https://api.streamable.com";
    public static final String ONLINE_CUSTOM_THEMES_API_BASE_URI = "http://127.0.0.1";

    public static String CLIENT_ID;
    public static String USER_AGENT;
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_SECRET_KEY = "client_secret";
    public static final String IMGUR_CLIENT_ID = "Client-ID cc671794e0ab397";
    public static final String REDGIFS_CLIENT_ID = "1828d0bcc93-15ac-bde6-0005-d2ecbe8daab3";
    public static final String REDGIFS_CLIENT_SECRET = "TJBlw7jRXW65NAGgFBtgZHu97WlzRXHYybK81sZ9dLM=";
    public static final String RESPONSE_TYPE_KEY = "response_type";
    public static final String RESPONSE_TYPE = "code";
    public static final String STATE_KEY = "state";
    public static final String STATE = "23ro8xlxvzp4asqd";
    public static final String REDIRECT_URI_KEY = "redirect_uri";
    public static final String REDIRECT_URI = "http://127.0.0.1";
    public static final String DURATION_KEY = "duration";
    public static final String DURATION = "permanent";
    public static final String SCOPE_KEY = "scope";
    public static final String SCOPE = "identity edit flair history modconfig modflair modlog modposts modwiki mysubreddits privatemessages read report save submit subscribe vote wikiedit wikiread creddits modcontributors modmail modothers livemanage account modself";
    public static final String ACCESS_TOKEN_KEY = "access_token";

    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final String AUTHORIZATION_BASE = "bearer ";
    public static final String USER_AGENT_KEY = "User-Agent";

    public static final String GRANT_TYPE_KEY = "grant_type";
    public static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";
    public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";

    public static final String DIR_KEY = "dir";
    public static final String ID_KEY = "id";
    public static final String RANK_KEY = "rank";
    public static final String DIR_UPVOTE = "1";
    public static final String DIR_UNVOTE = "0";
    public static final String DIR_DOWNVOTE = "-1";
    public static final String RANK = "10";

    public static final String ACTION_KEY = "action";
    public static final String SR_NAME_KEY = "sr_name";

    public static final String API_TYPE_KEY = "api_type";
    public static final String API_TYPE_JSON = "json";
    public static final String RETURN_RTJSON_KEY = "return_rtjson";
    public static final String TEXT_KEY = "text";
    public static final String URL_KEY = "url";
    public static final String VIDEO_POSTER_URL_KEY = "video_poster_url";
    public static final String THING_ID_KEY = "thing_id";

    public static final String SR_KEY = "sr";
    public static final String TITLE_KEY = "title";
    public static final String FLAIR_TEXT_KEY = "flair_text";
    public static final String SPOILER_KEY = "spoiler";
    public static final String NSFW_KEY = "nsfw";
    public static final String CROSSPOST_FULLNAME_KEY = "crosspost_fullname";
    public static final String SEND_REPLIES_KEY = "sendreplies";
    public static final String KIND_KEY = "kind";
    public static final String KIND_SELF = "self";
    public static final String KIND_LINK = "link";
    public static final String KIND_IMAGE = "image";
    public static final String KIND_VIDEO = "video";
    public static final String KIND_VIDEOGIF = "videogif";
    public static final String KIND_CROSSPOST = "crosspost";
    public static final String RICHTEXT_JSON_KEY = "richtext_json";

    public static final String FILEPATH_KEY = "filepath";
    public static final String MIMETYPE_KEY = "mimetype";

    public static final String LINK_KEY = "link";
    public static final String FLAIR_TEMPLATE_ID_KEY = "flair_template_id";
    public static final String FLAIR_ID_KEY = "flair_id";

    public static final String MAKE_FAVORITE_KEY = "make_favorite";

    public static final String MULTIPATH_KEY = "multipath";
    public static final String MODEL_KEY = "model";

    public static final String REASON_KEY = "reason";

    public static final String SUBJECT_KEY = "subject";
    public static final String TO_KEY = "to";

    public static final String NAME_KEY = "name";

    public static final String ORIGIN_KEY = "Origin";
    public static final String REVEDDIT_ORIGIN = "https://www.reveddit.com";
    public static final String REFERER_KEY = "Referer";
    public static final String REVEDDIT_REFERER = "https://www.reveddit.com/";


    public static void initialize(Context context) {
        // Load the API key and username from the file in internal storage
        try {
            // Open the file in internal storage
            FileInputStream fis = context.openFileInput("infinity_api_config.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("API_TOKEN=")) {
                    CLIENT_ID = line.substring("API_TOKEN=".length());
                } else if (line.startsWith("USERNAME=")) {
                    String username = line.substring("USERNAME=".length());
                    USER_AGENT = "android:personal-app:0.0.1 (by /u/" + username + ")";
                }
            Toast.makeText(context, "API config loaded for" + username, Toast.LENGTH_SHORT).show();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "API config file not found. Please provide API Token and Username.", Toast.LENGTH_SHORT).show();
            // If the file doesn't exist, prompt the user for API_TOKEN and USERNAME
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("API Configuration");
            final EditText inputToken = new EditText(context);
            final EditText inputUsername = new EditText(context);
            inputToken.setInputType(InputType.TYPE_CLASS_TEXT);
            inputUsername.setInputType(InputType.TYPE_CLASS_TEXT);
            inputToken.setHint("Enter API Token");
            inputUsername.setHint("Enter Username");
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(inputToken);
            layout.addView(inputUsername);
            builder.setView(layout);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String token = inputToken.getText().toString().trim();
                    String username = inputUsername.getText().toString().trim();
                    if (!token.isEmpty() && !username.isEmpty()) {
                        // Save the API_TOKEN and USERNAME to the file
                        try {
                            FileOutputStream fos = context.openFileOutput("infinity_api_config.txt", Context.MODE_PRIVATE);
                            fos.write(("API_TOKEN=" + token + "\n").getBytes());
                            fos.write(("USERNAME=" + username + "\n").getBytes());
                            fos.close();
                            // Update the static variables
                            CLIENT_ID = token;
                            USER_AGENT = "android:personal-app:0.0.1 (by /u/" + username + ")";
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error saving API config", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "API Token and Username cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }

    public static Map<String, String> getHttpBasicAuthHeader() {
        Map<String, String> params = new HashMap<>();
        String credentials = String.format("%s:%s", APIUtils.CLIENT_ID, "");
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        params.put(APIUtils.AUTHORIZATION_KEY, auth);
        return params;
    }

    public static Map<String, String> getOAuthHeader(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put(APIUtils.AUTHORIZATION_KEY, APIUtils.AUTHORIZATION_BASE + accessToken);
        // params.put(APIUtils.USER_AGENT_KEY, APIUtils.USER_AGENT);
        return params;
    }

    public static Map<String, String> getRedgifsOAuthHeader(String redgifsAccessToken) {
        Map<String, String> params = new HashMap<>();
        params.put(APIUtils.AUTHORIZATION_KEY, APIUtils.AUTHORIZATION_BASE + redgifsAccessToken);
        return params;
    }

    public static RequestBody getRequestBody(String s) {
        return RequestBody.create(s, MediaType.parse("text/plain"));
    }

    public static Map<String, String> getRevedditHeader() {
        Map<String, String> params = new HashMap<>();
        params.put(APIUtils.ORIGIN_KEY, APIUtils.REVEDDIT_ORIGIN);
        params.put(APIUtils.REFERER_KEY, APIUtils.REVEDDIT_REFERER);
        params.put(APIUtils.USER_AGENT_KEY, APIUtils.USER_AGENT);
        return params;
    }
}
