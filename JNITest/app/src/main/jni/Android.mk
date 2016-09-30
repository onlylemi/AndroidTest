LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := jni-test
LOCAL_SRC_FILE := com_onlylemi_jnitest_MainActivity.h

include $(BUILD_SHARED_LIBRARY)