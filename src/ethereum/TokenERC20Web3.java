package ethereum;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.ManagedTransaction;

import contracts.generated.TokenERC20;
import contracts.generated.TokenERC20.TransferEventResponse;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("deprecation")
public class TokenERC20Web3 extends BaseWeb3 {
	protected Disposable transferEventSubscription = null;

	@Override
	protected void init() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		super.init();
		previousContractAddress = "0x6a5faeceeb27f2122b584e391bc601e51fb34937";
	}

	@Override
	protected void deployNewContract() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		currentContract = TokenERC20.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, new BigInteger("100"),
				"MyToken", "MytokenSymbol").sendAsync().get();
		System.out.println(
				"contract address = " + currentContract.getContractAddress());
	}

	@Override
	protected void connectToPreviousContract() {
		currentContract = TokenERC20.load(getPreviousContractAddress(), web3j,
				credentials, ManagedTransaction.GAS_PRICE, gasLimit);
	}

	protected void watchEvents() {
		super.watchEvents();

		Flowable<TransferEventResponse> transferEventObservable = ((TokenERC20) currentContract)
				.transferEventFlowable(DefaultBlockParameterName.LATEST,
						DefaultBlockParameterName.LATEST);

		transferEventSubscription = transferEventObservable.subscribe(event -> {
			System.out.println("--EVM event--");
			System.out.println(transferEventResponseToString(event));
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
		return optionCounter++ + ": get total supply\n" + optionCounter++
				+ ": get name\n" + optionCounter++ + ": transfer\n"
				+ optionCounter++ + ": get balance\n";
	}

	private BigInteger getTotalSupply()
			throws InterruptedException, ExecutionException {
		BigInteger totalSupply = ((TokenERC20) currentContract).totalSupply()
				.sendAsync().get();
		System.out.println("total Supply=" + totalSupply);
		return totalSupply;
	}

	protected String getTokenName()
			throws InterruptedException, ExecutionException {
		String name = ((TokenERC20) currentContract).name().sendAsync().get();
		System.out.println("name=" + name);
		return name;
	}

	private void transfer() throws InterruptedException, ExecutionException {
		this.transfer("0x1baeb01683c6f91c4140d9ec114e2b24d8450883", "1");
	}

	protected void transfer(String address, String value)
			throws InterruptedException, ExecutionException {
		lastTransactionReceipt = ((TokenERC20) currentContract)
				.transfer(address, new BigInteger(value)).sendAsync().get();
		System.out.println(
				"tx hash = " + lastTransactionReceipt.getTransactionHash());
	}

	protected BigInteger getBalances()
			throws InterruptedException, ExecutionException {
		return this.getBalances("0x1baeb01683c6f91c4140d9ec114e2b24d8450883");
	}

	protected BigInteger getBalances(String address)
			throws InterruptedException, ExecutionException {
		BigInteger balance = ((TokenERC20) currentContract).balanceOf(address)
				.sendAsync().get();
		System.out.println("balance=" + balance);
		return balance;
	}

	@Override
	protected void customOptions(int option) throws IOException,
			CipherException, InterruptedException, ExecutionException {
		switch (option) {
		case 6:
			getTotalSupply();
			break;
		case 7:
			getTokenName();
			break;
		case 8:
			transfer();
			break;
		case 9:
			getBalances();
			break;
		}
	}

	public static void main(String args[]) throws Exception {
		new TokenERC20Web3().userOptions();
	}

}
