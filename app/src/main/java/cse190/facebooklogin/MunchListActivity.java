package cse190.facebooklogin;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by josh on 11/10/2015.
 */
public class MunchListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MunchListFragment();
    }

    public void createPost(View view) {
        Intent intent = new Intent(this, CreatePostActivity.class);
        startActivity(intent);
    }

}
