import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hedera.sdk.account.AccountGetBalance;
import com.hedera.sdk.account.AccountGetInfo;
import com.hedera.sdk.account.AccountGetRecords;
import com.hedera.sdk.account.AccountSend;
import com.hedera.sdk.account.HederaAccount;
import com.hedera.sdk.common.HederaAccountID;
import com.hedera.sdk.common.HederaTransactionAndQueryDefaults;
import com.hedera.sdk.common.HederaTransactionRecord;
import com.hedera.sdk.node.HederaNode;
import com.hedera.sdk.utilities.ExampleUtilities;


public class Main 
{
	public static void main(String[] args) throws Exception 
	{
		Properties prop = new Properties();
		InputStream input = null;
        HederaAccount account = new HederaAccount();
        HederaNode node = new HederaNode();
		HederaTransactionAndQueryDefaults txQueryDefaults = new HederaTransactionAndQueryDefaults();
		txQueryDefaults = ExampleUtilities.getTxQueryDefaults();
		String memo = "2018101620VALIUMPRESCNEU2345";
		//Byte memoToByte = Byte.valueOf(memo);
		txQueryDefaults.memo = memo;
    	account.txQueryDefaults = txQueryDefaults;
    	int counter = 0;
		try {
			input = new FileInputStream("node.properties");
			// load a properties file
			prop.load(input);
			node.setHostPort(prop.getProperty("nodeaddress"), Integer.valueOf(prop.getProperty("nodeport")));
			node.setAccountID(Long.parseLong(prop.getProperty("nodeAccountShard")), Long.parseLong(prop.getProperty("nodeAccountRealm")), Long.parseLong(prop.getProperty("nodeAccountNum")));
			// get the property value and print it out
	        account.accountNum = Long.parseLong(prop.getProperty("payingAccountNum"));
	        account.realmNum = Long.parseLong(prop.getProperty("payingAccountRealm"));
	        account.shardNum = Long.parseLong(prop.getProperty("payingAccountShard"));
	        account.setNode(node);
	        HederaAccount account2 = new HederaAccount();
	        account2.setHederaAccountID(new HederaAccountID(0, 0, 1004));
	        HederaTransactionAndQueryDefaults txQueryDefaults2 = new HederaTransactionAndQueryDefaults();
			txQueryDefaults2 = ExampleUtilities.getTxQueryDefaults();
	    	account2.txQueryDefaults = txQueryDefaults2;
	        //AccountSend.send(account, account2, 20000);
	        //Thread.sleep(2000);
	        //AccountGetBalance.getBalance(account2);
	        //Thread.sleep(2000);
	        //AccountGetRecords.getRecords(account);
	        
			List<HederaTransactionRecord> records = new ArrayList<HederaTransactionRecord>();
			records = account.getRecords();
			if (records != null) {
				
				for (HederaTransactionRecord record : records) {
					
					System.out.println(record.memo);
					System.out.println("-------------");
				}
			} else {
				
			}

	        //AccountGetInfo.getInfo(account);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
}
