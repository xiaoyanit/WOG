package netsurfers.gicp.net;

import netsurfers.gicp.net.common.Constants;
import netsurfers.gicp.net.common.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;

/**
 * MenuActivity is the menu activity of the game. Extends
 * {@link android.app.Activity}. This layout may be change when player select
 * the option. Then you can see the different screen
 * 
 * @author Ziliang Wang
 * @author E-mail: Lional.King@gmail.com
 */
public class MenuActivity extends Activity {

	/**
	 * Called when the activity is first created
	 */
	private boolean mGameRuning;
	private Context mContext;
	
	Button mBtnNewGame;
	Button mBtnLoadGame;
	Button mBtnSetting;
	Button mBtnInstruction;
	Button mBtnExitGame;
	TabHost mTabHost;
	private OnClickListener mLsnNewGame = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setContentView(R.layout.create_character);
			Button btnDone=(Button)findViewById(R.id.btn_done);
			Button btnRevert=(Button)findViewById(R.id.btn_revert);
			btnDone.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent Intent = new Intent();
					Intent.putExtra("stopGameKey", 0);
					setResult(Constants.RESULT_NEW_OK, Intent);
					finish();
				}
			});
			btnRevert.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					initialize();
				}
			});
		}
	};
	private OnClickListener mLsnNewGame2 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent Intent = new Intent();
			Intent.putExtra("stopGameKey", 0);
			setResult(Constants.RESULT_OK, Intent);
			finish();
		}
		
	};
	private OnClickListener mLsnLoadGame = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String[] projection = {
					"_id",
					"name",
					"sex",
					"class",
					"level"
			};
			final Cursor c = getContentResolver().query(Constants.DATABASE_CONTENT_URI[0], projection, null, null, null);
			ListView listview = (ListView)findViewById(R.layout.load_character);
			SimpleCursorAdapter adapter=new SimpleCursorAdapter(mContext,R.layout.load_character_info,c,projection,
					new int[]{R.id.tv_character_id,R.id.tv_character_name,R.id.tv_character_sex,R.id.tv_character_gang,R.id.tv_character_level});
			if(c.moveToFirst()) {
				listview.setAdapter(adapter);
				OnItemClickListener listener = new OnItemClickListener() {
	
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						Intent Intent = new Intent();
						Intent.putExtra("stopGameKey", 0);
						Intent.putExtra("setPlayerIDKey", arg3);
						setResult(Constants.RESULT_LOAD_OK, Intent);
						finish();
					}
					
				};
				listview.setOnItemClickListener(listener);
				setContentView(listview);
			}
			else
				initialize();
			c.close();
		}
	};
	private OnClickListener mLsnSetting = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setContentView(R.layout.setting);
			Button btnControlRevert=(Button)findViewById(R.id.btn_back_control_setting);
			btnControlRevert.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					initialize();
				}
			});
			Button btnVedioRevert=(Button)findViewById(R.id.btn_back_vedio_setting);
			btnVedioRevert.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					initialize();
				}
			});
			Button btnSoundRevert=(Button)findViewById(R.id.btn_back_sound_setting);
			btnSoundRevert.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					initialize();
				}
			});
			Button btnOtherRevert=(Button)findViewById(R.id.btn_back_other_setting);
			btnOtherRevert.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					initialize();
				}
			});
			try {
				mTabHost = (TabHost)findViewById(R.id.th_setting);
				mTabHost.setup();
				mTabHost.addTab(mTabHost.newTabSpec("tab1")
						.setContent(R.id.ll_control_setting)
						.setIndicator("Control"));
				mTabHost.addTab(mTabHost.newTabSpec("tab2")
						.setContent(R.id.ll_vedio_setting)
						.setIndicator("Vedio"));
				mTabHost.addTab(mTabHost.newTabSpec("tab3")
						.setContent(R.id.ll_sound_setting)
						.setIndicator("Sound"));
				mTabHost.addTab(mTabHost.newTabSpec("tab4")
						.setContent(R.id.ll_other_setting)
						.setIndicator("Other"));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	};
	private OnClickListener mLsnInstruction = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	private OnClickListener mLsnExitGame = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(mGameRuning) {
				Tools.AlertDialog(mContext, "warning", "You'll lost the character when you don't save game!");
				mGameRuning = false;
				initialize();
			}
			else {
				Intent Intent = new Intent();
				Intent.putExtra("stopGameKey", 1);
				setResult(Constants.RESULT_STOP, Intent);
				finish();
			}
		}
	};

	/**
	 * Called when the activity is being started
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		Bundle bundle = getIntent().getExtras();
		mGameRuning = bundle.getBoolean("GameRuningKey");
		
		initialize();
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyLongPress(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Initialize this activity when created or restart
	 */
	protected void initialize() {

		setContentView(R.layout.main_menu);
		
		mBtnNewGame = (Button) this.findViewById(R.id.btn_new_game);
		mBtnLoadGame = (Button) this.findViewById(R.id.btn_load_game);
		mBtnSetting = (Button) this.findViewById(R.id.btn_setting);
		mBtnInstruction = (Button) this.findViewById(R.id.btn_instruction);
		mBtnExitGame = (Button) this.findViewById(R.id.btn_exit_game);
		
		if(mGameRuning) {
			mBtnNewGame.setText(R.string.btn_new_game2);
			mBtnExitGame.setText(R.string.btn_exit_game2);
			mBtnNewGame.setOnClickListener(mLsnNewGame2);
		}
		else {
			mBtnNewGame.setText(R.string.btn_new_game);
			mBtnExitGame.setText(R.string.btn_exit_game);
			mBtnNewGame.setOnClickListener(mLsnNewGame);
		}
		
		mBtnLoadGame.setOnClickListener(mLsnLoadGame);
		mBtnSetting.setOnClickListener(mLsnSetting);
		mBtnInstruction.setOnClickListener(mLsnInstruction);
		mBtnExitGame.setOnClickListener(mLsnExitGame);
	}
}
