package ethereum;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ManagedTransaction;

import contracts.generated.MyContract;

public class TestWeb3 {

	public static void main(String args[]) throws IOException,
			InterruptedException, ExecutionException, CipherException {
		Web3j web3j = Web3j.build(new HttpService()); // defaults to
														// http://localhost:8545/
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		System.out.println(clientVersion);

		List<String> ethAccounts = web3j.ethAccounts().sendAsync().get()
				.getAccounts();
		System.out.println(ethAccounts);

		EthGetBalance ethGetBalance = web3j.ethGetBalance(ethAccounts.get(0),
				DefaultBlockParameterName.LATEST).sendAsync().get();

		BigInteger wei = ethGetBalance.getBalance();
		System.out.println(wei);

		BigInteger gasLimit = web3j
				.ethGetBlockByNumber(DefaultBlockParameter.valueOf("latest"),
						true)
				.send().getBlock().getGasLimit();
		System.out.println(gasLimit);

		// String contractAddress =
		// "0x828317A4340EE1b5D3aB954638531C8D6DBc8545";
		String password = "test123";
		String walletFilePath = "/Users/vivb/ethereum-private-network/chaindata/keystore/UTC--2018-01-16T00-04-02.507332080Z--ca3669c106b5f527e519e1c9cc6404076401cfd3";
		Credentials credentials = WalletUtils.loadCredentials(password,
				walletFilePath);
		MyContract myContract = MyContract.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, new BigInteger("0"))
				.sendAsync().get();
		System.out.println(myContract.getMyVariable().sendAsync().get());
		myContract.setMyVariable(new BigInteger("6")).sendAsync().get();
		System.out.println(myContract.getMyVariable().sendAsync().get());
	}
}
