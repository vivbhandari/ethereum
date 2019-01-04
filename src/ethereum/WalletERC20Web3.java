package ethereum;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.ManagedTransaction;

import contracts.generated.TokenERC20;
import contracts.generated.TokenERC20.TransferEventResponse;
import contracts.generated.WalletERC20;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("deprecation")
public class WalletERC20Web3 extends BaseWeb3 {
	protected Disposable transferEventSubscription = null;
	protected TokenERC20Web3 tokenERC20Web3 = null;

	public WalletERC20Web3() {
		this(null);
	}

	public WalletERC20Web3(String tokenAddress) {
		super();
		setPreviousContractAddress(
				"0xb39d003bbc6ec71276bec63de5e929b5632712ce");
		tokenERC20Web3 = new TokenERC20Web3();
		if (tokenAddress != null)
			tokenERC20Web3.setPreviousContractAddress(tokenAddress);
		tokenERC20Web3.connectToPreviousContract();
	}

	@Override
	protected void deployNewContract() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		currentContract = WalletERC20.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, new BigInteger("0"))
				.sendAsync().get();
		System.out.println(
				"contract address = " + currentContract.getContractAddress());

		sendEthers();
	}

	@Override
	protected void connectToPreviousContract()
			throws InterruptedException, ExecutionException {
		currentContract = WalletERC20.load(getPreviousContractAddress(), web3j,
				credentials, ManagedTransaction.GAS_PRICE, gasLimit);
		setTokenBalance();
	}

	protected void watchEvents() {
		super.watchEvents();

		Flowable<TransferEventResponse> transferEventObservable = ((TokenERC20) tokenERC20Web3.currentContract)
				.transferEventFlowable(DefaultBlockParameterName.LATEST,
						DefaultBlockParameterName.LATEST);

		transferEventSubscription = transferEventObservable.subscribe(event -> {
			System.out.println("--EVM event--");
			System.out.println(transferEventResponseToString(event));
			this.setTokenBalance();
		});
		System.out.println("subscribed to transfer events");
	}

	private String transferEventResponseToString(TransferEventResponse event) {
		return "TransferEvent [from=" + event.from + ", to=" + event.to
				+ ", name=" + event.name + ", value=" + event.value + "]";
	}

	protected void unsubscriveEvents() {
		super.unsubscriveEvents();
		transferEventSubscription.dispose();
	}

	@Override
	protected void getLastEvent() {
		System.out.println("Not implemented");
	}

	@Override
	protected String getCustomMessage() {
		return optionCounter++ + ": transfer\n" + optionCounter++
				+ ": get wallet token balance\n" + optionCounter++
				+ ": get allocated token balance\n" + optionCounter++
				+ ": refresh wallet token balance\n" + optionCounter++
				+ ": get ether balance\n" + optionCounter++ + ": send ethers\n";
	}

	protected BigInteger getTokenBalance()
			throws InterruptedException, ExecutionException {
		BigInteger balance = ((WalletERC20) currentContract)
				.getTokenBalance(tokenERC20Web3.getTokenName()).sendAsync()
				.get();
		System.out.println("token balance=" + balance);
		return balance;
	}

	protected BigInteger getAllocatedBalance()
			throws InterruptedException, ExecutionException {
		return tokenERC20Web3.getBalances(currentContract.getContractAddress());
	}

	protected void setTokenBalance()
			throws InterruptedException, ExecutionException {
		lastTransactionReceipt = ((WalletERC20) currentContract)
				.setTokenBalance(tokenERC20Web3.getTokenName(),
						getAllocatedBalance())
				.sendAsync().get();
		System.out.println(
				"tx hash = " + lastTransactionReceipt.getTransactionHash());
	}

	protected BigInteger getEtherBalance()
			throws InterruptedException, ExecutionException {
		BigInteger ethers = ((WalletERC20) currentContract).getEtherBalance()
				.sendAsync().get();
		System.out.println("ether balance=" + ethers);
		return ethers;
	}

	protected void sendEthers()
			throws InterruptedException, ExecutionException {
		this.sendEthers(credentials.getAddress(),
				currentContract.getContractAddress(), new BigInteger("10"));
	}

	@Override
	protected void customOptions(int option) throws IOException,
			CipherException, InterruptedException, ExecutionException {
		switch (option) {
		case 6:
			tokenERC20Web3.transfer(currentContract.getContractAddress(), "1");
			break;
		case 7:
			getTokenBalance();
			break;
		case 8:
			getAllocatedBalance();
			break;
		case 9:
			setTokenBalance();
		case 10:
			getEtherBalance();
			break;
		case 11:
			sendEthers();
		}
	}

	public static void main(String args[]) throws Exception {
		new WalletERC20Web3().userOptions();
	}

}
