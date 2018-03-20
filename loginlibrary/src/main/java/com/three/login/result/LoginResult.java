package com.three.login.result;


import com.three.login.bean.BaseToken;
import com.three.login.bean.BaseUser;

/**
 * Created by onetouch on 2017/11/21.
 */

public class LoginResult {


    private BaseToken mToken;

    private BaseUser mUserInfo;

    private int mPlatform;

    public LoginResult(int platform, BaseToken token) {
        mPlatform = platform;
        mToken = token;
    }

    public LoginResult(int platform, BaseToken token, BaseUser userInfo) {
        mPlatform = platform;
        mToken = token;
        mUserInfo = userInfo;
    }

    public int getPlatform() {
        return mPlatform;
    }

    public void setPlatform(int platform) {
        this.mPlatform = platform;
    }

    public BaseToken getToken() {
        return mToken;
    }

    public void setToken(BaseToken token) {
        mToken = token;
    }

    public BaseUser getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(BaseUser userInfo) {
        mUserInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "mToken=" + mToken +
                ", mUserInfo=" + mUserInfo +
                ", mPlatform=" + mPlatform +
                '}';
    }
}
