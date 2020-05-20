#include <jni.h>
#include <string>
#include <opencv2/imgproc/types_c.h>
#include "bitmap_utils.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_opencvjni_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jobject JNICALL
Java_com_example_opencvjni_MainActivity_opBitmap(JNIEnv *env, jobject instance, jobject bitmap,
                                            jobject argb8888) {
    Mat srcMat;
    Mat dstMat;
    bitmap2Mat(env, bitmap, &srcMat);
    cvtColor(srcMat, dstMat, CV_BGR2GRAY);//将图片的像素信息灰度化盛放在dstMat
    return createBitmap(env,dstMat,argb8888);//使用dstMat创建一个Bitmap对象
}