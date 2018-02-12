package contracts.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
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
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class MyContract extends Contract {
    private static final String BINARY = "60606040908152600560005560018054600160a060020a03191633600160a060020a031617905580519081016040908152600182526005602080840191909152600160a060020a03331660009081526002909152208151815460ff19169015151781556020820151600190910155506101708061007d6000396000f3006060604052600436106100565763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416636ec9c7ae81146100585780639bbc888a1461006e578063ef55177014610093575b005b341561006357600080fd5b6100566004356100a6565b341561007957600080fd5b610081610123565b60405190815260200160405180910390f35b341561009e57600080fd5b61008161013e565b6001543373ffffffffffffffffffffffffffffffffffffffff9081169116141561011b57806000543373ffffffffffffffffffffffffffffffffffffffff167f1d24b596e52915c2caa611e9a8e5257b7cc1b186f2748623f5941b6acf12071f60405160405180910390a46000819055610120565b600080fd5b50565b73ffffffffffffffffffffffffffffffffffffffff30163190565b600054905600a165627a7a723058203506ce5e2a04fa1e8ebedb270586af462c6d514749fab5710809bc59a34463820029";

    protected MyContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MyContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<NumberIsIncreasedEventResponse> getNumberIsIncreasedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("NumberIsIncreased", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NumberIsIncreasedEventResponse> responses = new ArrayList<NumberIsIncreasedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NumberIsIncreasedEventResponse typedResponse = new NumberIsIncreasedEventResponse();
            typedResponse.whoIncreased = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.oldNumber = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newNumber = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NumberIsIncreasedEventResponse> numberIsIncreasedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("NumberIsIncreased", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NumberIsIncreasedEventResponse>() {
            @Override
            public NumberIsIncreasedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NumberIsIncreasedEventResponse typedResponse = new NumberIsIncreasedEventResponse();
                typedResponse.whoIncreased = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.oldNumber = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.newNumber = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> setMyVariable(BigInteger myNewVariable) {
        Function function = new Function(
                "setMyVariable", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(myNewVariable)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getMyContractBalance() {
        Function function = new Function("getMyContractBalance", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMyVariable() {
        Function function = new Function("getMyVariable", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<MyContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(MyContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<MyContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(MyContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static MyContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static MyContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class NumberIsIncreasedEventResponse {
        public String whoIncreased;

        public BigInteger oldNumber;

        public BigInteger newNumber;

		@Override
		public String toString() {
			return "NumberIsIncreasedEventResponse [whoIncreased="
					+ whoIncreased + ", oldNumber=" + oldNumber + ", newNumber="
					+ newNumber + "]";
		}
    }
}
