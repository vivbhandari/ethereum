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
    private static final String BINARY = "608060405260008054600160a060020a0319163317905561040b806100256000396000f3fe60806040526004361061005b577c01000000000000000000000000000000000000000000000000000000006000350463025abd58811461005d5780632acb04de146101225780633d48ab8114610182578063ea46193e14610237575b005b34801561006957600080fd5b506101106004803603602081101561008057600080fd5b81019060208101813564010000000081111561009b57600080fd5b8201836020820111156100ad57600080fd5b803590602001918460018302840111640100000000831117156100cf57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061024c945050505050565b60408051918252519081900360200190f35b34801561012e57600080fd5b5061016e6004803603606081101561014557600080fd5b5073ffffffffffffffffffffffffffffffffffffffff81351690602081013590604001356102b4565b604080519115158252519081900360200190f35b34801561018e57600080fd5b5061016e600480360360408110156101a557600080fd5b8101906020810181356401000000008111156101c057600080fd5b8201836020820111156101d257600080fd5b803590602001918460018302840111640100000000831117156101f457600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550509135925061034b915050565b34801561024357600080fd5b506101106103da565b60006001826040518082805190602001908083835b602083106102805780518252601f199092019160209182019101610261565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054949350505050565b6000805473ffffffffffffffffffffffffffffffffffffffff1633141561033f5760405173ffffffffffffffffffffffffffffffffffffffff851690839085906000818181858888f193505050503d806000811461032e576040519150601f19603f3d011682016040523d82523d6000602084013e610333565b606091505b50505060019050610344565b600080fd5b9392505050565b6000805473ffffffffffffffffffffffffffffffffffffffff1633141561033f57816001846040518082805190602001908083835b6020831061039f5780518252601f199092019160209182019101610380565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092209290925550600191505092915050565b30319056fea165627a7a7230582036d21aa7ac63503cb94d85d507d9febf0bbaa0082a4acdb620f746ecda4c62540029";

    public static final String FUNC_GETTOKENBALANCE = "getTokenBalance";

    public static final String FUNC_TRANSFERETHERS = "transferEthers";

    public static final String FUNC_SETTOKENBALANCE = "setTokenBalance";

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

    public RemoteCall<TransactionReceipt> transferEthers(String _receiver, BigInteger _value, BigInteger _gas) {
        final Function function = new Function(
                FUNC_TRANSFERETHERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_receiver), 
                new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.generated.Uint256(_gas)), 
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
