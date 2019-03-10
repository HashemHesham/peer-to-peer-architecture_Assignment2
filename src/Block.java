import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.lang.Math;

public class Block extends BlockChain {
    public String hash , prevHash;
    private String data ;
    private long timeStamp;
    public int nonce; ///Proof of work modification
    
    public Block(String prevHash, String data) 
    {
    	mineBlock(difficulty, prevHash, data);
     
        
    }

    public void mineBlock(int diff,String prevHash,String data)
    {
        ///Proof of work modification
    	hash=generateHash();
    	while(!this.hash.substring(0, diff).equals(this.generatezeros(diff)))
    	{
    		this.prevHash = prevHash;
            this.data = data;
            timeStamp = new Date().getTime();
            hash = generateHash();
          ///Proof of work modification
            nonce= (int) (Math.random()*100);
    	}
    		
    }
    public String generatezeros(int n)
    {
    	String s="";
    	for (int i = 0; i < n; i++) 
    	{
			s+="0";
		}
    	return s;
    }
    public String calculateHash(String input)
    {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]hash = messageDigest .digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString() ;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "error";
    }
    public String generateHash()
    {
        String hashing = calculateHash(prevHash+Long.toString(timeStamp)+data);
        return hashing ;
    }

}
