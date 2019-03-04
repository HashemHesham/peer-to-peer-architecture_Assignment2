import java.util.ArrayList;

public class BlockChain {
    public ArrayList<Block> blockChain;
    public int difficulty=2;///Proof of work modification
    public boolean isValid()
    {
        Block currentBlock,prevBlock;
        for(int i= 1 ;i<blockChain.size();i++)
        {
            currentBlock = blockChain.get(i);
            prevBlock = blockChain.get(i - 1);
            if(!currentBlock.hash.equals(currentBlock.generateHash()) ){
                System.out.println("the data changed");
                return false;
            }

            if(!prevBlock.hash.equals(prevBlock.generateHash()) ){
                System.out.println("the data changed");
                return false;
            }
        }
        return true;
    }
}
