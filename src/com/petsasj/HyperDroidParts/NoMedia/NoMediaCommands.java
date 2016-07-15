package com.petsasj.HyperDroidParts.NoMedia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.petsasj.HyperDroidParts.Util.RootConstants;
import com.stericson.RootTools.RootTools;

import android.os.Environment;
import android.util.Log;

public class NoMediaCommands {

	static String TAG = "**File Creator";
	static boolean dir;

	public static void makeFolder(String param) {
		try {
			dir = new File("/data/.androidmeda/"+param).mkdir(); // should fail
			dir = new File("/data/.androidmeda/"+param).mkdirs(); // should 
		} finally {
			Log.d("*** Success ***", "Made Folders");
		}
	}

	public static void removeFolder(String param) {
		try {
			dir = (new File("/data/.androidmeda/"+param).delete());
		} finally {
			Log.d("*** Success ***", "Made Folders");
		}
	}

	/**
	 * 
	 * @param   directoryPath   The full path to the directory to place the .nomedia file
	 * @return   Returns true if the file was successfully written or appears to already exist
	 */

	public static boolean writeNoMediaFile(String directoryPath)	{
		if (Environment.MEDIA_MOUNTED.equals(RootConstants.storageState)) {
			try {
				File noMedia = new File (directoryPath, ".nomedia");
				if (noMedia.exists()) {
					Log.i ( TAG, ".no media appears to exist already, returning without writing a new file" );
					return true;
				}
				FileOutputStream noMediaOutStream = new FileOutputStream (noMedia);
				noMediaOutStream.write(0);
				noMediaOutStream.close();
			} catch (Exception e) {
				Log.e(TAG, "error writing file");            
				e.printStackTrace();
				return false;
			}
		} else {
			Log.e(TAG, "storage appears unwritable");
			return false;
		}
		return true;
	}

	public static void removeNoMedia(String hoe) {
		try {
			dir = (new File(hoe+"/.nomedia").delete());
		} finally {
			Log.d("*** Success ***", "Removed Folders");
		}
	}

	public static void writeInit() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(RootConstants.CLEANSD_LOCATION));
			out.write("#!/system/bin/sh");
			out.newLine();
			out.write("#");
			out.newLine();
			out.write("#Adding Sleep\n\n#Deleting folders");
			out.newLine();
			out.write("sleep 20");
			out.newLine();
			out.newLine();
			out.write("#Deleting folders");
			out.newLine();
			out.write("#Fuck you developers who don't use");
			out.newLine();
			out.write("#sdcard Android Data folder");
			out.newLine();
			out.close();
		} catch (IOException e)	{
			Log.i("HDP", "ERROR, shit happened when i was writing stuff");	
		} return;
	}

	public static void addInit(String param) {
		try {
			FileWriter fstream = new FileWriter(RootConstants.CLEANSD_LOCATION, true);
			BufferedWriter fbw = new BufferedWriter(fstream);
			fbw.write("rm -r "+param);
			fbw.newLine();
			fbw.close();
		}	catch (Exception e) {
			Log.d("HDP", "Error: " + e.getMessage());
		}
	}

	public static void doCopyCleanSD() {
		String command1 = "cp -f "+RootConstants.CLEANSD_LOCATION+" "+RootConstants.CLEANSD_INITD;
		String command2 = "chmod 755 "+RootConstants.CLEANSD_INITD;
		String command3 = "rm "+RootConstants.CLEANSD_LOCATION;
		try {
			RootTools.remount("/system", "rw");
			RootTools.sendShell(
					new String[] {
							command1,
							command2,
							command3}, 0, null
					);
			RootTools.remount("/system", "ro");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());    
		}
	}

	public static void doRemoveCleanSD() {
		String command1 = "rm "+RootConstants.CLEANSD_INITD;
		try {
			RootTools.remount("/system", "rw");
			RootTools.sendShell(
					new String[] {
							command1}, 0, null
					);
			RootTools.remount("/system", "ro");
		} catch (Exception e) {
			Log.d("*RootCommands", "Unexpected error in : "+e.getMessage());    
		}
	}
}