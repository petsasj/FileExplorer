package com.petsasj.HyperDroidParts.NoMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.stericson.RootTools.RootTools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class NoMediaActivity extends ListActivity {

	SharedPreferences mPrefs;
	String fileFolder = "";

	private static final int DIALOG_ROOTNODIR = 202;
	private static final int DIALOG_NOROOTNODIR = 204;

	private Stack<File> dirStack = new Stack<File>();
	private File currentDir;
	private FileArrayAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		currentDir = new File("/sdcard/");
		fill(currentDir);

		firstRunPreferences();
		checkRootAndInit();

		/**
		 * not ready yet
		 * set option for long click
		 * perhaps multiselect
		 * 
		final Option o = items.get(position);  
		ListView list = getListView();
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				String scenario = String.valueOf(currentDir);
				writeScenario(scenario);
				return true;
			}
		});*/
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("FileManager", "Saving screen state");
		//outState.getSerializable(adapter);
	}

	private void fill(File f) {
		File[]dirs = f.listFiles();
		this.setTitle("Dir: "+f.getName());
		List<Option>dir = new ArrayList<Option>();
		List<Option>fls = new ArrayList<Option>();
		try {
			for(File ff: dirs) {
				if(ff.isDirectory()) {
					dir.add(new Option(ff.getName(),"Folder",ff.getAbsolutePath(),ff.lastModified()));
					fileFolder = String.valueOf(ff.getName());
				}
				else {
					fls.add(new Option(ff.getName(),"File Size: "+ff.length()+" bytes",ff.getAbsolutePath(),ff.lastModified()));
				}
			}
		} catch(Exception e) {
			Log.i("Yo", "yoyoyo something fucked up happened");
		}

		Collections.sort(dir);
		Collections.sort(fls);
		dir.addAll(fls);
		if(!f.getName().equalsIgnoreCase("sdcard"))
			dir.add(0,new Option("...","Parent Directory",f.getParent(),f.lastModified()));
		adapter = new FileArrayAdapter(NoMediaActivity.this,R.layout.file_viewer,dir);
		this.setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		if(o.getData().equalsIgnoreCase("folder")) {
			dirStack.push(currentDir);
			currentDir = new File(o.getPath());
			fill(currentDir);
		} else if(o.getData().equalsIgnoreCase("parent directory")) {
			currentDir = dirStack.pop();
			fill(currentDir);
		} else {
			onFileClick(o);
		}
	}

	public void onBackPressed() {
		if (dirStack.size() == 0) {
			finish();
			return;
		}
		currentDir = dirStack.pop();
		fill(currentDir);
	}

	private void onFileClick(Option o) {
		Toast.makeText(this, "File Clicked: "+o.getName(), Toast.LENGTH_SHORT).show();
	}

	public void checkRootAndInit() {
		String fullPath = "/system/etc/init.d";
		File dir = new File(fullPath);
		if (RootTools.isAccessGiven()) {
			if (dir.exists());
			else if ((!dir.exists())) {
				showDialog(DIALOG_ROOTNODIR);
			}
		} else if (!RootTools.isAccessGiven()) {
			if (getFirstRun()) {
				setRunned();
				showDialog(DIALOG_NOROOTNODIR);
			}
		}
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_ROOTNODIR:
			return new AlertDialog.Builder(this).setTitle("Caution").setIcon(android.R.drawable.ic_dialog_alert)
					.setMessage("You don't me to have an init.d folder. Does your kernel support it?\n\nCreate now?")
					.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							try {
								RootTools.sendShell("mkdir /system/etc/init.d");
								RootTools.sendShell("chmod 755 /system/etc/init.d");
							} catch (Exception e) {
								Log.d ("HDP", "Folder Created");
								e.printStackTrace();
							}
							Toast.makeText(getApplicationContext(), "Folder Created",Toast.LENGTH_SHORT).show();
						}}).setNegativeButton(android.R.string.no, null).create();
		case DIALOG_NOROOTNODIR:
			return new AlertDialog.Builder(this).setTitle("Caution").setIcon(android.R.drawable.ic_dialog_alert)
					.setMessage("You don't seem to have RootAccess.\n\nCleaning SD on boot won't work for you.")
					.setNeutralButton(android.R.string.yes, null).create();
		default:
			return super.onCreateDialog(id);
		}
	}

	/**
	 * get if this is the first run
	 *
	 * @return returns true, if this is the first run
	 */
	public boolean getFirstRun() {
		return mPrefs.getBoolean("firstRun", true);
	}

	/**
	 * store the first run
	 */
	public void setRunned() {
		SharedPreferences.Editor edit = mPrefs.edit();
		edit.putBoolean("firstRun", false);
		edit.commit();
	}

	/**
	 * setting up preferences storage
	 */
	public void firstRunPreferences() {
		Context mContext = this.getApplicationContext();
		mPrefs = mContext.getSharedPreferences("noMedia", 0); //0 = mode private. only this app can read these preferences
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.hdp_filemanager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//Handle Item selection
		switch (item.getItemId()) {
		case R.id.save_nomedia:
			String bitch = (currentDir).toString();
			NoMediaCommands.writeNoMediaFile(bitch);
			fill(currentDir);
			return true;
		case R.id.remove_nomedia:
			String hoe = (currentDir).toString();
			NoMediaCommands.removeNoMedia(hoe);
			fill(currentDir);
			return true;
		case R.id.cleansd_add:
			String cocksucker = (currentDir).toString();
			if (cocksucker.equals("/sdcard")||cocksucker.equals("/sdcard/DCIM")||cocksucker.equals("/sdcard/Android")||cocksucker.equals("/sdcard/data")) {
				Toast.makeText(this, "This folder has important stuff in it, stay way!", Toast.LENGTH_SHORT).show();
			} else {
				NoMediaCommands.writeInit();
				NoMediaCommands.addInit(cocksucker);
			}			
			return true;
		case R.id.rescan_nomedia:
			sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file:///mnt/sdcard")));
			sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file:///mnt/sdcard")));
			Toast.makeText(this, "This takes about 1 minute", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.cleansd_save:
			NoMediaCommands.doCopyCleanSD();
			return true;
		case R.id.cleansd_remove:
			NoMediaCommands.doRemoveCleanSD();
			return true;
		}
		return false;
	}
}