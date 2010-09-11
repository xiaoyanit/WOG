package netsurfers.gicp.net.common;

public class Constants {
	public static float SCREEM_WIDTH_DEFAULT = 320F;
	public static float SCREEM_HEIGHT_DEFAULT = 480F;
	public static float SCREEM_HALF_WIDTH = SCREEM_WIDTH_DEFAULT/2;
	public static float SCREEM_HALF_HEIGHT = SCREEM_HEIGHT_DEFAULT/2;
	public static final long FRAME_INTERVAL = 65;
	public static final String DATABASE_TABLE_NAME[] = {
		"character",				// 0��Ҵ浵��
		"character_equipmentsets",	// 1���װ����
		"character_inventory",		// 2��ұ�����Ʒ��
		"character_quest",			// 3��������
		"character_spell",			// 4����书��
		"creature",					// 5����ֲ���
		"creature_ai",				// 6����AI��
		"creature_loot_template",	// 7��������
		"creature_template",		// 8������Ϣ��
		"gameobject",				// 9��Ϸ����ֲ���
		"gameobject_questrelation",	//10��Ϸ��������
		"gameobject_loot_template",	//11��Ϸ��������
		"gameobject_template",		//12��Ϸ�����Ϣ��
		"item_template",			//13��Ʒ��Ϣ��
		"npc_trainer",				//14npcѵ��ʦ��
		"npc_vendor",				//15npc�ۻ�ʦ��
		"playercreateinfo",			//16���ﴴ����Ϣ��
		"playercreateinfo_item",	//17���ﴴ����Ʒ��
		"playercreateinfo_spell",	//18���ﴴ���书��
		"quest_template",			//19������Ϣ��
		"spell_chain"				//20�书���Ա�
	};
	public static final String DATABASE_TABLE00_COLUMN_NAME[] = {
		"guid",
		"test"
	};
	public static enum PLAYER_STATE {
		NOTHING,
		UP,
		DOWN,
		LEFT,
		RIGHT
	};
}