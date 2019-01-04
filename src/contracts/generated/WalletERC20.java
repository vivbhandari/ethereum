package contracts.generated;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class WalletERC20 extends Contract {
    private static final String BINARY = "608060405260008054600160a060020a03191633179055610439806100256000396000f3fe608060405260043610610066577c01000000000000000000000000000000000000000000000000000000006000350463025abd5881146100685780631254e64d1461012d5780633d48ab811461017a5780638e3ed7d71461022f578063ea46193e14610268575b005b34801561007457600080fd5b5061011b6004803603602081101561008b57600080fd5b8101906020810181356401000000008111156100a657600080fd5b8201836020820111156100b857600080fd5b803590602001918460018302840111640100000000831117156100da57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061027d945050505050565b60408051918252519081900360200190f35b34801561013957600080fd5b506101666004803603604081101561015057600080fd5b50600160a060020a0381351690602001356102e5565b604080519115158252519081900360200190f35b34801561018657600080fd5b506101666004803603604081101561019d57600080fd5b8101906020810181356401000000008111156101b857600080fd5b8201836020820111156101ca57600080fd5b803590602001918460018302840111640100000000831117156101ec57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295505091359250610344915050565b34801561023b57600080fd5b506101666004803603604081101561025257600080fd5b50600160a060020a0381351690602001356103c6565b34801561027457600080fd5b5061011b610408565b60006001826040518082805190602001908083835b602083106102b15780518252601f199092019160209182019101610292565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054949350505050565b60008054600160a060020a031633141561033957604051600160a060020a0384169083156108fc029084906000818181858888f1935050505015801561032f573d6000803e3d6000fd5b506001905061033e565b600080fd5b92915050565b60008054600160a060020a031633141561033957816001846040518082805190602001908083835b6020831061038b5780518252601f19909201916020918201910161036c565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922092909255506001915061033e9050565b60008054600160a060020a031633141561033957604051600160a060020a0384169083156108fc029084906000818181858888f150600194505050505061033e565b30319056fea165627a7a723058202eb914d8afd90dcd18b9505efa2cb2a3d01fb7960f0d96a59f276878047e237b0029";

    public static final String FUNC_GETTOKENBALANCE = "getTokenBalance";

    public static final String FUNC_TRANSFERETHERS = "transferEthers";

    public static final String FUNC_SETTOKENBALANCE = "setTokenBalance";

    public static final String FUNC_SENDETHERS = "sendEthers";

    public static final String FUNC_GETETHERBALANCE = "getEtherBalance";

    @Deprecated
    protected WalletERC20(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected WalletERC20(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected WalletERC20(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected WalletERC20(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getTokenBalance(String _tokenName) {
        final Function function = new Function(FUNC_GETTOKENBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_tokenName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferEthers(String _receiver, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERETHERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_receiver), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setTokenBalance(String _tokenName, BigInteger _value) {
        final Function function = new Function(
                FUNC_SETTOKENBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_tokenName), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sendEthers(String _receiver, BigInteger _value) {
        final Function function = new Function(
                FUNC_SENDETHERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_receiver), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getEtherBalance() {
        final Function function = new Function(FUNC_GETETHERBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static WalletERC20 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new WalletERC20(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static WalletERC20 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new WalletERC20(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static WalletERC20 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new WalletERC20(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static WalletERC20 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new WalletERC20(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<WalletERC20> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(WalletERC20.class, web3j, credentials, contractGasProvider, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<WalletERC20> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(WalletERC20.class, web3j, transactionManager, contractGasProvider, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<WalletERC20> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(WalletERC20.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<WalletERC20> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(WalletERC20.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }
}
