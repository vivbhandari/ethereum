package ethereum;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.ManagedTransaction;

import contracts.generated.Crowdsale;
import contracts.generated.TokenERC20;
import contracts.generated.WalletERC20;
import io.reactivex.disposables.Disposable;

@SuppressWarnings("deprecation")
public class CrowdsaleERC20Web3 extends BaseWeb3 {
	protected Disposable transferEventSubscription = null;
	protected String beneficiaryAddress = "0xc2ecb198cf835347193df2c3037481c683ac5f10";
	TokenERC20 tokenERC20 = null;
	WalletERC20 walletERC20 = null;

	public CrowdsaleERC20Web3() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		super();
		setPreviousContractAddress(
				"0x979e9b19aa91ffe3cb20ed264479c8f13a05de93");
	}

	protected void deployNewToken() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		tokenERC20 = TokenERC20.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, new BigInteger("99"),
				"MyToken", "MytokenSymbol").sendAsync().get();
		System.out.println(
				"token contract address = " + tokenERC20.getContractAddress());
	}

	protected void deployNewWallet() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		walletERC20 = WalletERC20.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, new BigInteger("3"))
				.sendAsync().get();
		System.out.println("wallet contract address = "
				+ walletERC20.getContractAddress());
	}

	@Override
	protected void deployNewContract() throws IOException, CipherException,
			InterruptedException, ExecutionException {
		deployNewToken();
		deployNewWallet();
		currentContract = Crowdsale.deploy(web3j, credentials,
				ManagedTransaction.GAS_PRICE, gasLimit, beneficiaryAddress,
				new BigInteger("6"), new BigInteger("10"), new BigInteger("1"),
				tokenERC20.getContractAddress()).sendAsync().get();
		System.out.println("crowdsale contract address = "
				+ currentContract.getContractAddress());
	}

	@Override
	protected void connectToPreviousContract()
			throws InterruptedException, ExecutionException {
		currentContract = Crowdsale.load(getPreviousContractAddress(), web3j,
				credentials, ManagedTransaction.GAS_PRICE, gasLimit);
	}

	@Override
	protected void getLastEvent() {
		System.out.println("Not implemented");
	}

	protected void setTokenBalance()
			throws InterruptedException, ExecutionException {
		lastTransactionReceipt = walletERC20
				.setTokenBalance(getTokenName(), getAllocatedBalance())
				.sendAsync().get();
		System.out.println(
				"tx hash = " + lastTransactionReceipt.getTransactionHash());
	}

	protected BigInteger getTokenBalance()
			throws InterruptedException, ExecutionException {
		BigInteger balance = walletERC20.getTokenBalance(getTokenName())
				.sendAsync().get();
		System.out.println("token balance=" + balance);
		return balance;
	}

	protected BigInteger getAllocatedBalance()
			throws InterruptedException, ExecutionException {
		return getAllocatedBalance(walletERC20.getContractAddress());
	}

	protected BigInteger getAllocatedBalance(String address)
			throws InterruptedException, ExecutionException {
		BigInteger balance = tokenERC20.balanceOf(address).sendAsync().get();
		System.out.println("allocated balance=" + balance);
		return balance;
	}

	protected BigInteger getInvestedEtherBalance(String address)
			throws InterruptedException, ExecutionException {
		BigInteger balance = ((Crowdsale) currentContract).balanceOf(address)
				.sendAsync().get();
		System.out.println("invested balance=" + balance);
		return balance;
	}

	// private BigInteger getTotalSupply()
	// throws InterruptedException, ExecutionException {
	// BigInteger totalSupply = tokenERC20.totalSupply().sendAsync().get();
	// System.out.println("total Supply=" + totalSupply);
	// return totalSupply;
	// }

	protected String getTokenName()
			throws InterruptedException, ExecutionException {
		String name = tokenERC20.name().sendAsync().get();
		System.out.println("name=" + name);
		return name;
	}

	protected void transfer() throws InterruptedException, ExecutionException,
			IOException, TransactionException {
		sendEthers(walletERC20.getContractAddress(),
				currentContract.getContractAddress(), new BigInteger("3"));
	}

	protected void transfer2() throws InterruptedException, ExecutionException,
			IOException, TransactionException {
		sendEthers2(walletERC20.getContractAddress(),
				currentContract.getContractAddress(), new BigDecimal("3"));
	}

	protected void transfer3() throws InterruptedException, ExecutionException,
			IOException, TransactionException {
		lastTransactionReceipt = walletERC20
				.sendEthers(currentContract.getContractAddress(),
						new BigInteger("3"))
				.sendAsync().get();
		System.out.println(
				"tx hash=" + lastTransactionReceipt.getTransactionHash());
	}

	protected void transfer4() throws InterruptedException, ExecutionException,
			IOException, TransactionException {
		lastTransactionReceipt = walletERC20
				.transferEthers(currentContract.getContractAddress(),
						new BigInteger("3"))
				.sendAsync().get();
		System.out.println(
				"tx hash=" + lastTransactionReceipt.getTransactionHash());
	}

	protected void printLastTransactionDetails() {
		System.out.println("lastTransactionReceipt=" + lastTransactionReceipt);
	}

	protected BigInteger getEtherBalance()
			throws InterruptedException, ExecutionException {
		BigInteger ethers = walletERC20.getEtherBalance().sendAsync().get();
		System.out.println("ether balance=" + ethers);
		return ethers;
	}

	@Override
	protected String getCustomMessage() {
		return optionCounter++ + ": transfer\n" + optionCounter++
				+ ": get wallet token balance\n" + optionCounter++
				+ ": get allocated token balance\n" + optionCounter++
				+ ": refresh wallet token balance\n" + optionCounter++
				+ ": get ether balance\n" + optionCounter++
				+ ": add ethers to wallet\n" + optionCounter++
				+ ": transfer my ethers to crowdsale\n" + optionCounter++
				+ ": my token balance\n" + optionCounter++
				+ ": crowdsale ether balance\n" + optionCounter++
				+ ": my invested ether balance\n" + optionCounter++
				+ ": transfer 2\n" + optionCounter++ + ": transfer 3\n"
				+ optionCounter++ + ": transfer 4\n" + optionCounter++
				+ ": last transaction details\n";
	}

	protected BigInteger getCrowdsaleEtherBalance()
			throws InterruptedException, ExecutionException {
		BigInteger balance = ((Crowdsale) currentContract).getEtherBalance()
				.sendAsync().get();
		System.out.println("crowdsale ether balance=" + balance);
		return balance;
	}

	@Override
	protected void customOptions(int option)
			throws IOException, CipherException, InterruptedException,
			ExecutionException, TransactionException {
		switch (option) {
		case 6:
			transfer();
			break;
		case 7:
			getTokenBalance();
			break;
		case 8:
			getAllocatedBalance();
			break;
		case 9:
			setTokenBalance();
			break;
		case 10:
			getEtherBalance();
			break;
		case 11:
			sendEthers(credentials.getAddress(),
					walletERC20.getContractAddress(), new BigInteger("9"));
			break;
		case 12:
			sendEthers(credentials.getAddress(),
					currentContract.getContractAddress(), new BigInteger("3"));
			break;
		case 13:
			getAllocatedBalance(credentials.getAddress());
			break;
		case 14:
			getCrowdsaleEtherBalance();
			break;
		case 15:
			getInvestedEtherBalance(credentials.getAddress());
			break;
		case 16:
			transfer2();
			break;
		case 17:
			transfer3();
			break;
		case 18:
			transfer4();
			break;
		case 19:
			printLastTransactionDetails();
			break;
		}
	}

	public static void main(String args[]) throws Exception {
		new CrowdsaleERC20Web3().userOptions();
	}
}
