package com.onlylemi.test5_storage.entity;

/**
 * LoginResult
 *
 * @author: onlylemi
 * @time: 2016-06-13 10:56
 */
public class LoginResult {

    /**
     * resultCode : 1
     * resultMsg : success
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private int resultCode;
        private String resultMsg;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }
}
