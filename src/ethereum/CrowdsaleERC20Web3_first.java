package ethereum;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.tx.ManagedTransaction;

import contracts.generated.Crowdsale;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("deprecation")
public class CrowdsaleERC20Web3_first extends BaseWeb3 {
	protected Disposable transferEventSubscription = null;
	protected TokenERC20Web3 tokenERC20Web3 = null;
	protected WalletERC20Web3 walletERC20Web3 = null;

	public CrowdsaleERC20Web3_first() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		super();
		setPreviousContractAddress(
				"0xb39d003bbc6ec71276bec63de5e929b5632712ce");
		tokenERC20Web3 = new TokenERC20Web3();
		tokenERC20Web3.deployNewContract();
		walletERC20Web3 = new WalletERC20Web3(
				tokenERC20Web3.currentContract.getContractAddress());
		walletERC20Web3.deployNewContract();
	}

	@Override
	protected void deployNewContract() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		currentContract = Crowdsale
				.deploy(web3j, credentials, ManagedTransaction.GAS_PRICE,
						gasLimit, walletERC20Web3.getPreviousContractAddress(),
						new BigInteger("6"), new BigInteger("2"),
						new BigInteger("2"),
						tokenERC20Web3.currentContract.getContractAddress())
				.sendAsync().get();
		System.out.println(
				"contract address = " + currentContract.getContractAddress());
	}

	@Override
	protected void connectToPreviousContract()
			throws InterruptedException, ExecutionException {
		currentContract = Crowdsale.load(getPreviousContractAddress(), web3j,
				credentials, ManagedTransaction.GAS_PRICE, gasLimit);
	}

	protected void watchEvents() {
//		super.watchEvents();
		walletERC20Web3.watchEvents();
	}

	protected void unsubscriveEvents() {
//		super.unsubscriveEvents();
		walletERC20Web3.unsubscriveEvents();
	}

	@Override
	protected void getLastEvent() {
		System.out.println("Not implemented");
	}

	private BigInteger getTokenBalances()
			throws InterruptedException, ExecutionException {
		return walletERC20Web3.getTokenBalance();
	}

	private BigInteger getAllocatedBalance()
			throws InterruptedException, ExecutionException {
		return tokenERC20Web3.getBalances(
				walletERC20Web3.currentContract.getContractAddress());
	}

	private void setBalance() throws InterruptedException, ExecutionException {
		walletERC20Web3.setTokenBalance();
	}

	private void transfer() throws InterruptedException, ExecutionException {
		sendEthers(walletERC20Web3.currentContract.getContractAddress(),
				currentContract.getContractAddress(), new BigInteger("3"));
	}

	@Override
	protected String getCustomMessage() {
		return optionCounter++ + ": transfer\n" + optionCounter++
				+ ": get wallet token balance\n" + optionCounter++
				+ ": get allocated token balance\n" + optionCounter++
				+ ": refresh wallet token balance\n" + optionCounter++
				+ ": get ether balance\n";
	}

	@Override
	protected void customOptions(int option) throws IOException,
			CipherException, InterruptedException, ExecutionException {
		switch (option) {
		case 6:
			transfer();
			break;
		case 7:
			getTokenBalances();
			break;
		case 8:
			getAllocatedBalance();
			break;
		case 9:
			setBalance();
			break;
		case 10:
			walletERC20Web3.getEtherBalance();
		}
	}

	public static void main(String args[]) throws Exception {
		new CrowdsaleERC20Web3_first().userOptions();
	}
}
