/*
 * DroidFix Project
 * file ClassComputer.java  is  part of DroidFix
 * The MIT License (MIT)  Copyright (c) 2015 Bunny Blue.
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package io.github.bunnyblue.droidfix.classcomputer;

import com.android.dx.command.Main;
import io.github.bunnyblue.droidfix.classcomputer.cache.Configure;
import io.github.bunnyblue.droidfix.classcomputer.classes.ClassInject;
import io.github.bunnyblue.droidfix.classcomputer.classes.ClassObject;
import io.github.bunnyblue.droidfix.classcomputer.proguard.MappingMapper;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipEntrySource;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;

/**
 * Created by BunnyBlue on 11/10/15.
 */
public class ClassComputer {


//    public static void main(String[] args) {
//        Configure.getInstance().setBuildRootDir("E:/DroidFix/app");
//        Configure.getInstance().setBuildType("debug");
//        Configure.getInstance().init();
//        ArrayList<ClassObject> diffClasses=new ArrayList<ClassObject>();
//        ClassObject classObject=new ClassObject("233","io.test");
//        diffClasses.add(classObject);
//        copyDiffClasses(diffClasses,Configure.getInstance().getBuildRootDir());
//
//
//
//
 //   }
    public static void copyDiffClasses(ArrayList<ClassObject> diffClasses, String rootPath) {

        for (ClassObject classObject : diffClasses) {
            classObject.getClassName().replaceAll(".", "/");

            String subPath=classObject.getClassName().replaceAll("\\.","/");
            if ( subPath.lastIndexOf("/")!=-1) {
                subPath=subPath.substring(0, subPath.lastIndexOf("/"));
                subPath=rootPath+"/"+subPath;
                subPath=subPath.replaceAll("\\\\","/");
                File subDir=new File(subPath);
                subDir.mkdirs();
                File localClass=new File(classObject.getLocalPath());
                try {
                    FileUtils.copyFileToDirectory(localClass, subDir);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.err.println("Copy diff class "+localClass.getAbsolutePath());



            }


        }
    }
    public static void main(String[] args) {
        String action = args[0];

        String buildRootDir = args[1];
        String buildType = args[2];
        Configure.getInstance().setBuildRootDir(buildRootDir);
        Configure.getInstance().setBuildType(buildType);
        Configure.getInstance().init();
        if ("injectCode".equals(action)) {
            injectClasses();
        } else if ("cacheClassesTable".equals(action)) {
            writeClassCache();
        } else if ("diffPatch".equals(action)) {
            computerDiffClases();
        }else if ("buildPkg".equals(action)){
            processDiffClassToApk(Configure.getInstance().getDiffClassesDir());
        }

    }
    public  static void  processDiffClassToApk(String patchClassDir){
        File patchClassesDir=new File(patchClassDir);
        File patchjar=new File(patchClassesDir.getParentFile().getAbsolutePath()+File.separator+"classes.jar");
        File patchDex=new File(patchClassesDir.getParentFile().getAbsolutePath()+File.separator+"patch.apk");
        ZipUtil.pack(patchClassesDir,patchjar);
        String dxArgs[]=new String[]{"--dex","--output="+patchDex.getAbsolutePath(),patchjar.getAbsolutePath()};
        Main.main(dxArgs);
//        File patchPkg=new File(patchClassesDir.getAbsolutePath()+File.separator+"patch.apk");
//      //  ZipEntrySource []zipEntrySources=new ZipEntrySource[1];
//        ZipUtil.packEntry(patchDex,patchPkg);


    }

    public static void injectClasses() {

        ClassInject.injectConstructionClasses();
    }

    public static void writeClassCache() {

        MappingMapper mappingMapper = new MappingMapper();
        mappingMapper.processRawClasses();
        mappingMapper.writeNewClassCache(mappingMapper.processRawClasses());

    }

    public static void computerDiffClases() {

        MappingMapper mappingMapper = new MappingMapper();
        mappingMapper.processRawClasses();

        mappingMapper.computeDiffObject();

    }
}
