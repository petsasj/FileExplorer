package com.petsasj.HyperDroidParts.Util;

import android.util.Log;

import com.petsasj.HyperDroidParts.Util.RootConstants;

import com.stericson.RootTools.RootTools;

public class RootCommands {

	public static void doGps(String param) {
		String command1 = "cp -f "+RootConstants.GPS_LOCATION+param+"/gps.conf "+RootConstants.ETC_LOCATION;
		String command2 = "chown 0.0 "+RootConstants.ETC_LOCATION+"gps.conf";
		String command3 = "chmod 755 "+RootConstants.ETC_LOCATION+"gps.conf";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doBanimation(String param) {
		String command1 = "cp -f "+RootConstants.HDP_LOCATION+"banimation/"+param+"/bootanimation.zip /system/media";
		String command2 = "chown 0.0 /system/media/bootanimation.zip";
		String command3 = "chmod 644 /system/media/bootanimation.zip";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doWake(String param) {
		String command1 = "cp -f "+RootConstants.WAKE_LOCATION+param+"/sec_key.kl /system/usr/keylayout/";
		String command2 = "chown 0.0 /system/usr/keylayout/sec_key.kl";
		String command3 = "chmod 644 /system/usr/keylayout/sec_key.kl";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doBsound(String param) {
		String command1 = "cp -f "+RootConstants.HDP_LOCATION+"bsound/"+param+"/PowerOn.wav "+RootConstants.ETC_LOCATION;
		String command2 = "chown 0.0 "+RootConstants.ETC_LOCATION+"PowerOn.wav";
		String command3 = "chmod 644 "+RootConstants.ETC_LOCATION+"PowerOn.wav";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doBsoundRM() {
		try {
			RootTools.sendShell("rm "+RootConstants.ETC_LOCATION+"PowerOn.wav");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doMountNTFS() {
		String command1 = "chown 0.0 /system/xbin/ntfs-3g";
		String command2 = "chmod 755 /system/xbin/ntfs-3g";
		String command3 = "/system/xbin/ntfs-3g /dev/block/mmcblk1p1 /mnt/sdcard/external_sd";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doMountOnBoot() {
		String command1 = "cp -f /data/data/com.petsasj.HyperDroidParts/petsasj/mount/S09Mount "+RootConstants.INIT_D;
		String command2 = "chmod 750 "+RootConstants.INIT_D+"S09Mount";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doRightToLeft() {
		String command1 = "cp -f "+RootConstants.HDP_LOCATION+"rtl/lib/*.so /system/lib/";
		String command2 = "chown 0.0 /system/lib/*.so";
		String command3 = "chmod 644 /system/lib/*.so";
		String command4 = "cp -f "+RootConstants.HDP_LOCATION+"rtl/framework/framework.jar /system/framework/";
		String command5 = "chown 0.0 /system/framework/framework.jar";
		String command6 = "chmod 644 /system/framework/framework.jar";
		String command7 = "cp -f "+RootConstants.HDP_LOCATION+"rtl/fonts/*.ttf /system/fonts/";
		String command8 = "chown 0.0 /system/fonts/*.ttf";
		String command9 = "chmod 644 /system/fonts/*.ttf";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3,
							command4,
							command5,
							command6,
							command7,
							command8,
							command9}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doTWBackup() {
		String command1 = "cp -f "+RootConstants.TOUCHWIZ_LOCATION+"databases/launcher.db " +RootConstants.BACKUP_FILE_NAME;
		String command2 = "cp -f "+RootConstants.TOUCHWIZ_LOCATION+"shared_prefs/launcher.xml " +RootConstants.BACKUP_FILE_NAME;
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doTWRestore() {
		String command1 = "rm "+RootConstants.TOUCHWIZ_LOCATION+"databases/launcher.db";
		String command2 = "rm "+RootConstants.TOUCHWIZ_LOCATION+"shared_prefs/launcher.xml";
		String command3 = "mv "+RootConstants.BACKUP_FILE_NAME+"launcher.db "+RootConstants.TOUCHWIZ_LOCATION+"databases/";
		String command4 = "mv "+RootConstants.BACKUP_FILE_NAME+"launcher.xml "+RootConstants.TOUCHWIZ_LOCATION+"shared_prefs/";
		String command5 = "chmod 660 "+RootConstants.TOUCHWIZ_LOCATION+"launcher.db";
		String command6 = "chmod 660 "+RootConstants.TOUCHWIZ_LOCATION+"launcher.xml";
		String command7 = "cp -f "+RootConstants.TOUCHWIZ_LOCATION+"databases/launcher.db " +RootConstants.BACKUP_FILE_NAME;
		String command8 = "cp -f "+RootConstants.TOUCHWIZ_LOCATION+"shared_prefs/launcher.xml " +RootConstants.BACKUP_FILE_NAME;
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3,
							command4,
							command5,
							command6,
							command7,
							command8}, 0, null
					);
			RootTools.killProcess("com.sec.android.app.twlauncher");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doTW(final String param) {
		try {
			RootTools.sendShell("sh "+RootConstants.DATA_PATH+"twlauncher/launcher"+param+".sh");
			RootTools.killProcess("com.sec.android.app.twlauncher");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}


	public static void doTWRestart() {
		try {
			RootTools.killProcess("com.sec.android.app.twlauncher");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doBatteryBackup() {
		try {
			RootTools.sendShell("cp -f /data/system/batterystats.bin " +RootConstants.BACKUP_FILE_NAME);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doBatteryRestore() {
		String command1 = "rm /data/system/batterystats.bin";
		String command2 = "mv "+RootConstants.BACKUP_FILE_NAME+"batterystats.bin /data/system/";
		String command3 = "chown 0.0 /data/system/batterystats.bin";
		String command4 = "chmod 600 /data/system/batterystats.bin";
		String command5 = "cp -f /data/system/batterystats.bin " +RootConstants.BACKUP_FILE_NAME;
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3,
							command4,
							command5}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}


	public static void doMarket() {
		try {
			RootTools.killProcess("com.android.vending");
			RootTools.sendShell("rm -r /data/data/com.android.vending/*");
			RootTools.killProcess("com.android.vending");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doPermissions() {
		try {
			RootTools.sendShell("/sbin/busybox sh /system/bin/fix_permissions");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}

	public static void doCopyInit() {
		String command1 = "cp -f "+RootConstants.SAVE_MODE+" "+RootConstants.INIT_D;
		String command2 = "cp -f "+RootConstants.SAVE_SCENARIO+" "+RootConstants.INIT_D;
		String command3 = "chmod 750 "+RootConstants.INIT_D+"S11Scenario";
		String command4 = "chmod 750 "+RootConstants.INIT_D+"S12Mode";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2, 
							command3,
							command4}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());    
		}
	}

	public static void doRemoveInit() {
		String command1 = "rm "+RootConstants.INIT_D+"S11Scenario";
		String command2 = "rm "+RootConstants.INIT_D+"S12Mode";
		try {
			RootTools.sendShell(
					new String[] {
							command1,
							command2}, 0, null
					);
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());    
		}
	}

	public static void doReload() {
		RootTools.restartAndroid();
	}

	public static void doReboot() {
		try {
			RootTools.sendShell("reboot");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());                    
		} 
	}
}