package ethereum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
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
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert.Unit;

import io.reactivex.disposables.Disposable;

@SuppressWarnings("deprecation")
public abstract class BaseWeb3 {

	protected Web3j web3j = null;
	protected Credentials credentials = null;
	protected BigInteger gasLimit = null;
	protected Contract currentContract = null;
	protected TransactionReceipt lastTransactionReceipt = null;
	protected Disposable txSubscription = null;
	protected Disposable ethLogSubscription = null;
	protected int optionCounter = 0;
	protected String previousContractAddress = null;

	public BaseWeb3() {
		try {
			init();
		} catch (IOException | CipherException | InterruptedException
				| ExecutionException e) {
			e.printStackTrace();
		}
	}

	protected void init() throws IOException, CipherException,
			InterruptedException, ExecutionException {
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

		gasLimit = web3j
				.ethGetBlockByNumber(DefaultBlockParameter.valueOf("latest"),
						true)
				.send().getBlock().getGasLimit();
		System.out.println("gasLimit=" + gasLimit);
		System.out.println(
				"ManagedTransaction.GAS_PRICE=" + ManagedTransaction.GAS_PRICE);
		String password = "test123";
		String walletFilePath = "/Users/vivb/ethereum-private-network/chaindata/keystore/UTC--2018-01-16T00-04-02.507332080Z--ca3669c106b5f527e519e1c9cc6404076401cfd3";
		credentials = WalletUtils.loadCredentials(password, walletFilePath);
	}

	protected int readValidInt(String message) {
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

	protected void watchEvents() {

		txSubscription = web3j.transactionFlowable().subscribe(tx -> {
			System.out.println("--transaction event--");
			System.out.println("Block number = " + tx.getBlockNumber());
			System.out.println("input = " + tx.getInput());
			System.out.println("hash = " + tx.getHash());
		});

		System.out.println(
				"transaction subscription=" + !txSubscription.isDisposed());

		EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.EARLIEST,
				DefaultBlockParameterName.LATEST,
				currentContract.getContractAddress());

		ethLogSubscription = web3j.ethLogFlowable(ethFilter).subscribe(log -> {
			System.out.println("--Eth log--");
			System.out.println("address = " + log.getAddress());
			System.out.println("data = " + log.getData());
			System.out.println("topics = " + log.getTopics());
		});

		System.out.println("subscribed to events");
	}

	protected void unsubscriveEvents() {
		txSubscription.dispose();
		System.out.println(
				"transaction subscription=" + !txSubscription.isDisposed());
		ethLogSubscription.dispose();
		System.out.println("unsubscribed from events");
	}

	public String getMessage() {
		return getBaseMessage() + getCustomMessage();
	}

	public String getBaseMessage() {
		return "Select one of the options:\n" + optionCounter++ + ": exit\n"
				+ optionCounter++ + ": deploy new contract\n" + optionCounter++
				+ ": connect to the previous contract\n" + optionCounter++
				+ ": watch events\n" + optionCounter++ + ": get last event\n"
				+ optionCounter++ + ": unsubscribe events\n";
	}

	protected void sendEthers2(String fromAddress, String toAddress,
			BigDecimal amount) throws InterruptedException, ExecutionException,
			IOException, TransactionException {
		TransactionReceipt receipt = Transfer
				.sendFunds(web3j, credentials, toAddress, amount, Unit.ETHER)
				.sendAsync().get();
		System.out.println("receipt=" + receipt);
	}

	protected void sendEthers(String fromAddress, String toAddress,
			BigInteger amount) throws InterruptedException, ExecutionException {
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(fromAddress,
						DefaultBlockParameterName.LATEST)
				.sendAsync().get();
		// get the next available nonce
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
		System.out.println("nonce=" + nonce);

		Transaction tx = Transaction.createEtherTransaction(fromAddress, nonce,
				ManagedTransaction.GAS_PRICE, gasLimit, toAddress, amount);
		EthSendTransaction receipt = web3j.ethSendTransaction(tx).sendAsync()
				.get();
		System.out.println("receipt=" + receipt);
	}

	protected void userOptions() throws IOException, CipherException,
			InterruptedException, ExecutionException, TransactionException {
		String message = getMessage();
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
				watchEvents();
				break;
			case 4:
				getLastEvent();
				break;
			case 5:
				unsubscriveEvents();
				break;
			default:
				customOptions(option);
			}
		}
	}

	protected void setPreviousContractAddress(String previousContractAddress) {
		this.previousContractAddress = previousContractAddress;
	}

	protected abstract String getCustomMessage();

	protected abstract void customOptions(int option)
			throws IOException, CipherException, InterruptedException,
			ExecutionException, TransactionException;

	protected abstract void deployNewContract() throws IOException,
			CipherException, InterruptedException, ExecutionException;

	protected abstract void getLastEvent();

	protected String getPreviousContractAddress() {
		return this.previousContractAddress;
	}

	protected abstract void connectToPreviousContract()
			throws InterruptedException, ExecutionException;
}
