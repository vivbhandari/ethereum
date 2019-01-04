package ethereum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;

public class TestMain {
	public static void main(String args[]) throws IOException,
			InterruptedException, ExecutionException, CipherException {
		// defaults to http://localhost:8545/

		// String id = web3j.web3Sha3("NumberIsIncreased(address, uint256,
		// uint256)");
		// System.out.println("id=" + id);

//		 new MyContractWeb3();
		 new TokenERC20Web3();
	}
}
