# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidStudio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

################gson##################
-keep class com.google.gson.** {*;}
#-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.** {
    <fields>;
    <methods>;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-dontwarn com.google.gson.**


#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
 #优化  不优化输入的类文件
-dontoptimize
 #预校验
-dontpreverify
 #混淆时是否记录日志
-verbose
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*

 #不混淆R类
-keep public class com.example.administrator.mvp.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#java8 retrolambda
-dontwarn java.lang.invoke.*

####################fragmentation#####################
-keep class me.yokeyword.fragmentation.** {*;}
-dontwarn me.yokeyword.fragmentation.**

# 保持哪些类不被混淆
-keep class com.example.administrator.mvp.model.entity.** { *; } #实体类不参与混淆
#-keep class com.example.administrator.mvp.model.greendao.** { *; } #实体类不参与混淆
-keep class com.example.administrator.mvp.common.widget.** { *; } #自定义控件不参与混淆
-keep public class * extends android.view
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
###混淆调试用
-dontwarn io.reactivex.**
-keep class io.reactivex.** { *; }
-dontwarn com.jakewharton.rxbinding
-keep class com.jakewharton.rxbinding.** { *; }
-dontwarn com.ogaclejapan.**
-keep class com.ogaclejapan.smarttablayout.** { *; }
-dontwarn com.trello.**
-keep class com.trello.** { *; }
-dontwarn javax.annotation.**
-keep class javax.annotation.** { *; }

#retrofit + rxjava
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

################<span></span>混淆保护自己项目的部分代码以及引用的第三方jar包library#########################
#injector不能被混淆
-keep class com.example.administrator.mvp.common.injector.** { *; }
#injector不能被混淆 end

#常用参数类
-keep class com.example.administrator.mvp.common.utils.** { *; }
#常用参数类end

#如果引用了v4或者v7包
-dontwarn android.support.**
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
#7.0之后的，都改成了Bind
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
# Butterknife end

#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#解决Android design 混淆问题
-keep class android.support.design.widget.** { *; }
-keep interface android.support.design.widget.** { *; }
-dontwarn android.support.design.**

# #  ######## greenDao混淆  ##########
# # -------------------------------------------
#-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
#public static java.lang.String TABLENAME;
#}
#-keep class **$Properties

## If you do not use SQLCipher:
#-dontwarn org.greenrobot.greendao.database.**
## If you do not use Rx:
#-dontwarn rx.**

#green dao 2.0+
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
#green dao end


