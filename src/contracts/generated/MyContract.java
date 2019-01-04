package contracts.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
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
public class MyContract extends Contract {
    private static final String BINARY = "6005600081815560018054600160a060020a03191633908117825560c06040908152608083905260a08590529083526002602052909120805460ff1916821781550155610150806100516000396000f3fe608060405260043610610050577c010000000000000000000000000000000000000000000000000000000060003504636ec9c7ae81146100525780639bbc888a1461007c578063ef551770146100a3575b005b34801561005e57600080fd5b506100506004803603602081101561007557600080fd5b50356100b8565b34801561008857600080fd5b50610091610119565b60408051918252519081900360200190f35b3480156100af57600080fd5b5061009161011e565b60015473ffffffffffffffffffffffffffffffffffffffff163314156101115760008054604051839233917f1d24b596e52915c2caa611e9a8e5257b7cc1b186f2748623f5941b6acf12071f9190a46000819055610116565b600080fd5b50565b303190565b6000549056fea165627a7a72305820c1c157c535c5321d223366fc7ff7c36243602f4c6859f17e9b170a79bf8b1fd30029";

    public static final String FUNC_SETMYVARIABLE = "setMyVariable";

    public static final String FUNC_GETMYCONTRACTBALANCE = "getMyContractBalance";

    public static final String FUNC_GETMYVARIABLE = "getMyVariable";

    public static final Event NUMBERISINCREASED_EVENT = new Event("NumberIsIncreased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected MyContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MyContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MyContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MyContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setMyVariable(BigInteger myNewVariable) {
        final Function function = new Function(
                FUNC_SETMYVARIABLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(myNewVariable)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getMyContractBalance() {
        final Function function = new Function(FUNC_GETMYCONTRACTBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMyVariable() {
        final Function function = new Function(FUNC_GETMYVARIABLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<NumberIsIncreasedEventResponse> getNumberIsIncreasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NUMBERISINCREASED_EVENT, transactionReceipt);
        ArrayList<NumberIsIncreasedEventResponse> responses = new ArrayList<NumberIsIncreasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NumberIsIncreasedEventResponse typedResponse = new NumberIsIncreasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.whoIncreased = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.oldNumber = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newNumber = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NumberIsIncreasedEventResponse> numberIsIncreasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, NumberIsIncreasedEventResponse>() {
            @Override
            public NumberIsIncreasedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NUMBERISINCREASED_EVENT, log);
                NumberIsIncreasedEventResponse typedResponse = new NumberIsIncreasedEventResponse();
                typedResponse.log = log;
                typedResponse.whoIncreased = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.oldNumber = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.newNumber = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NumberIsIncreasedEventResponse> numberIsIncreasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NUMBERISINCREASED_EVENT));
        return numberIsIncreasedEventFlowable(filter);
    }

    @Deprecated
    public static MyContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MyContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MyContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MyContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MyContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MyContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MyContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(MyContract.class, web3j, credentials, contractGasProvider, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<MyContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue) {
        return deployRemoteCall(MyContract.class, web3j, transactionManager, contractGasProvider, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<MyContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(MyContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<MyContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(MyContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static class NumberIsIncreasedEventResponse {
        public Log log;

        public String whoIncreased;

        public BigInteger oldNumber;

        public BigInteger newNumber;
    }
}
