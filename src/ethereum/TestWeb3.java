package ethereum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ManagedTransaction;

import contracts.generated.MyContract;
import contracts.generated.MyContract.NumberIsIncreasedEventResponse;
import rx.Observable;
import rx.Subscription;

public class TestWeb3 {

	private static Web3j web3j = null;
	// private static String previousContractAddress =
	// "0xa846eabc6ea0cf2f9fc88cc04c1a1475a23bf386";
	private static String previousContractAddress = "0x6F9388Ed8e7BC574bb00cd46061F5A793015798d";
	private static Credentials credentials = null;
	private static BigInteger gasLimit = null;
	private static MyContract currentContract = null;
	private static TransactionReceipt lastTransactionReceipt = null;
	private static Subscription txSubscription = null;
	private static Subscription ethLogSubscription = null;
	private static Subscription numberIsIncreasedEventSubscription = null;
	private static int optionCounter = 0;

	public static void main(String args[]) throws IOException,
			InterruptedException, ExecutionException, CipherException {
		// defaults to http://localhost:8545/
		web3j = Web3j.build(new HttpService());
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

		init();
		userOptions();
	}

	private static void init() throws IOException, CipherException {
		gasLimit = web3j
				.ethGetBlockByNumber(DefaultBlockParameter.valueOf("latest"),
						true)
				.send().getBlock().getGasLimit();
		System.out.println(gasLimit);
		String password = "test123";
		String walletFilePath = "/Users/vivb/ethereum-private-network/chaindata/keystore/UTC--2018-01-16T00-04-02.507332080Z--ca3669c106b5f527e519e1c9cc6404076401cfd3";
		credentials = WalletUtils.loadCredentials(password, walletFilePath);
	}

	private static int readValidInt(String message) {
		int input = -1;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.println(message);

		while (true) {
			try {
				input = Integer.parseInt(br.readLine());
				if (input >= 0 && input < optionCounter) {
					break;
				} else {
					System.out.println("Enter a number between 0 and "
							+ (optionCounter - 1));
				}
			} catch (NumberFormatException nEx) {
				System.out.println(
						"Enter a number between 0 and " + (optionCounter - 1));
			} catch (IOException e) {
				System.out.println("IO Exception, try again...");
			}
		}

		return input;
	}

	private static void userOptions() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		String message = "Select one of the options:\n" + optionCounter++
				+ ": exit\n" + optionCounter++ + ": deploy new contract\n"
				+ optionCounter++ + ": connect to the previous contract\n"
				+ optionCounter++ + ": get number\n" + optionCounter++
				+ ": increase number\n" + optionCounter++ + ": watch events\n"
				+ optionCounter++ + ": get last event\n" + optionCounter++
				+ ": unsubscribe events\n";
		loop: while (true) {
			int option = readValidInt(message);

			switch (option) {
			case 0:
				System.out.println("Good Bye!");
				break loop;
			case 1:
				deployNewContract();
				break;
			case 2:
				connectToPreviousContract();
				break;
			case 3:
				getNumber();
				break;
			case 4:
				increaseNumber();
				break;
			case 5:
				watchEvents();
				break;
			case 6:
				getLastEvent();
				break;
			case 7:
				unsubscriveEvents();
				break;
			}
		}
	}

	private static void deployNewContract() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		currentContract = MyContract.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, new BigInteger("0"))
				.sendAsync().get();
		System.out.println(
				"contract address = " + currentContract.getContractAddress());
	}

	private static BigInteger getNumber()
			throws InterruptedException, ExecutionException {
		BigInteger number = currentContract.getMyVariable().sendAsync().get();
		System.out.println("current number=" + number);
		return number;
	}

	private static void increaseNumber()
			throws InterruptedException, ExecutionException {
		BigInteger number = getNumber();
		System.out.println("submitting transaction");
		lastTransactionReceipt = currentContract
				.setMyVariable(number.add(new BigInteger("1"))).sendAsync()
				.get();
		System.out.println(
				"tx hash = " + lastTransactionReceipt.getTransactionHash());
		getNumber();
	}

	private static void connectToPreviousContract() {
		currentContract = MyContract.load(previousContractAddress, web3j,
				credentials, ManagedTransaction.GAS_PRICE, gasLimit);
	}

	private static void watchEvents() {

		txSubscription = web3j.transactionObservable().subscribe(tx -> {
			System.out.println("--transaction event--");
			System.out.println("Block number = " + tx.getBlockNumber());
			System.out.println("input = " + tx.getInput());
			System.out.println("hash = " + tx.getHash());
		});

		System.out.println(
				"transaction subscription=" + !txSubscription.isUnsubscribed());

		EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.EARLIEST,
				DefaultBlockParameterName.LATEST,
				currentContract.getContractAddress());

		ethLogSubscription = web3j.ethLogObservable(ethFilter)
				.subscribe(log -> {
					System.out.println("--Eth log--");
					System.out.println("address = " + log.getAddress());
					System.out.println("data = " + log.getData());
					System.out.println("topics = " + log.getTopics());
				});

		Observable<NumberIsIncreasedEventResponse> numberIsIncreasedEventObservable = currentContract
				.numberIsIncreasedEventObservable(
						DefaultBlockParameterName.LATEST,
						DefaultBlockParameterName.LATEST);
		numberIsIncreasedEventSubscription = numberIsIncreasedEventObservable
				.subscribe(event -> {
					System.out.println("--EVM event--");
					System.out.println(event);
				});
		System.out.println("subscribed to events");
	}

	private static void unsubscriveEvents() {
		txSubscription.unsubscribe();
		System.out.println(
				"transaction subscription=" + !txSubscription.isUnsubscribed());
		ethLogSubscription.unsubscribe();
		numberIsIncreasedEventSubscription.unsubscribe();
		System.out.println("unsubscribed from events");
	}

	private static void getLastEvent() {
		List<NumberIsIncreasedEventResponse> events = currentContract
				.getNumberIsIncreasedEvents(lastTransactionReceipt);
		for (NumberIsIncreasedEventResponse event : events) {
			System.out.println(event);
		}
	}
}
