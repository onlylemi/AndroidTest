//
// Created by mi on 16-9-27.
//
#include "com_onlylemi_jnitest_MainActivity.h"

JNIEXPORT jstring JNICALL Java_com_onlylemi_jnitest_MainActivity_get(JNIEnv * env, jobject obj) {
    printf("invoke set from C++\n");
    return env->NewStringUTF(cs);
}

JNIEXPORT void JNICALL Java_com_onlylemi_jnitest_MainActivity_set(JNIEnv* env, jobject obj, jstring string){
    printf("invoke set from C++\n");
    char* str = (char*) env->GetStringUTFChars(string, NULL);
    cs = str;
    printf("%s\n", str);
    env->ReleaseStringUTFChars(string, str);
}

