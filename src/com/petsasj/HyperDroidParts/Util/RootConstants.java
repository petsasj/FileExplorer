package com.petsasj.HyperDroidParts.Util;

import java.io.File;

import android.os.Environment;

public class RootConstants {

	public static String DATA_PATH = "/data/data/com.petsasj.HyperDroidParts/petsasj/";
	public static String storageState = Environment.getExternalStorageState();
	public static String BACKUP_FILE_NAME = new File(Environment.getExternalStorageDirectory(), "Android/data/com.petsasj.HyperDroidParts/Backup/").getAbsolutePath();
	public static final String INIT_D = "/system/etc/init.d/";
	public static final String ETC_LOCATION = "/system/etc/";
	public static final String SAVE_SCENARIO = "/data/data/com.petsasj.HyperDroidParts/petsasj/S11Scenario";
	public static final String SAVE_MODE = "/data/data/com.petsasj.HyperDroidParts/petsasj/S12Mode";
	public static final String GPS_LOCATION = "/data/data/com.petsasj.HyperDroidParts/petsasj/gps/";
	public static final String WAKE_LOCATION = "/data/data/com.petsasj.HyperDroidParts/petsasj/wake/";
	public static final String HDP_LOCATION = "/system/etc/hdp/";
	public static final String TOUCHWIZ_LOCATION = "/data/data/com.sec.android.app.twlauncher/";
	public static final String CLEANSD_LOCATION = "/data/data/com.petsasj.HyperDroidParts.NoMedia/S14CleanSD";
	public static final String CLEANSD_INITD = "/system/etc/init.d/S14CleanSD";
}