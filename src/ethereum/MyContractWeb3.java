package ethereum;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.ManagedTransaction;

import contracts.generated.MyContract;
import contracts.generated.MyContract.NumberIsIncreasedEventResponse;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("deprecation")
public class MyContractWeb3 extends BaseWeb3 {
	protected Disposable numberIsIncreasedEventSubscription = null;

	@Override
	protected void deployNewContract() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		currentContract = MyContract.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, new BigInteger("0"))
				.sendAsync().get();
		System.out.println(
				"contract address = " + currentContract.getContractAddress());
	}

	private BigInteger getNumber()
			throws InterruptedException, ExecutionException {
		BigInteger number = ((MyContract) currentContract).getMyVariable()
				.sendAsync().get();
		System.out.println("current number=" + number);
		return number;
	}

	private void increaseNumber()
			throws InterruptedException, ExecutionException {
		BigInteger number = getNumber();
		System.out.println("submitting transaction");
		lastTransactionReceipt = ((MyContract) currentContract)
				.setMyVariable(number.add(new BigInteger("1"))).sendAsync()
				.get();
		System.out.println(
				"tx hash = " + lastTransactionReceipt.getTransactionHash());
		getNumber();
	}

	protected void connectToPreviousContract() {
		currentContract = MyContract.load(getPreviousContractAddress(), web3j,
				credentials, ManagedTransaction.GAS_PRICE, gasLimit);
	}

	protected void watchEvents() {
		super.watchEvents();

		Flowable<NumberIsIncreasedEventResponse> numberIsIncreasedEventObservable = ((MyContract) currentContract)
				.numberIsIncreasedEventFlowable(
						DefaultBlockParameterName.LATEST,
						DefaultBlockParameterName.LATEST);

		numberIsIncreasedEventSubscription = numberIsIncreasedEventObservable
				.subscribe(event -> {
					System.out.println("--EVM event--");
					System.out.println(event);
				});
		System.out.println("subscribed to numberIsIncreased events");
	}

	protected void unsubscriveEvents() {
		super.unsubscriveEvents();
		numberIsIncreasedEventSubscription.dispose();
	}

	@Override
	protected void getLastEvent() {
		List<NumberIsIncreasedEventResponse> events = ((MyContract) currentContract)
				.getNumberIsIncreasedEvents(lastTransactionReceipt);
		for (NumberIsIncreasedEventResponse event : events) {
			System.out.println(event);
		}
	}

	@Override
	protected String getPreviousContractAddress() {
		// "0x3e36f150072134D6F859de7DefD6fd83AEd62967";
		return "0x7b9e758924dbbc6d22e4fd6132a9470cfc538913";
	}

	@Override
	protected String getCustomMessage() {
		return optionCounter++ + ": get number\n" + optionCounter++
				+ ": increase number\n";
	}

	@Override
	protected void customOptions(int option) throws IOException,
			CipherException, InterruptedException, ExecutionException {
		switch (option) {
		case 6:
			getNumber();
			break;
		case 7:
			increaseNumber();
			break;
		}
	}

	public static void main(String args[]) throws Exception {
		new MyContractWeb3().userOptions();;
	}
}
